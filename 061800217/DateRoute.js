const bodyParser = require('body-parser');
var express = require('express');
var router = express.Router();
let mysql = require("mysql");

let db = require('./util/dbUtil.js');
let sh = require('./stringHandle.js');

// var db_config=sqlInf.db_config
// var connect=mysql.createConnection(db_config);//开始链接数据库
var userid = 0;

const ERRORACCOUNT = "Account does not exist"
const ERRORPWD = "Password is wrong";

newUser();
loginIn();
dateAnalWords();
userPaperList();
userInput();
keywordsAnal();
delPaper();
//用户登录
function loginIn() {
    router.post('/login', function (req, res) {
        let name = req.body.username;
        let pass = req.body.password;
        let errText = '',
            resultData = '';

        let sql = "select * from users where username = '" + name + "'";
        db.query(sql, function (err, result, field) {
            if (err) {
                res.status(200).json({
                    "status": false,
                    'msg': err,
                });
            }
            let str = JSON.stringify(result);
            let js = JSON.parse(str);
            let t = js.length;
            if (t == 0) errText = ERRORACCOUNT;
            else if (pass != result[0].password) errText = ERRORPWD;
            else {
                resultData = result[0];
            }
            res.status(200).json({
                'status': true,
                'msg': errText,
                'data': resultData
            });
        })
    });
}
//用户注册 判断用户名是否已经存在
function newUser() {
    var urlencodedParser = bodyParser.urlencoded({
        extended: false
    });
    router.post('/register', urlencodedParser, function (req, res) {
        let username = req.body.username;
        let password = req.body.password;
        let userPackage = "papers"; //20200328 如果有时间 这部分改成，每次注册一个用户为他建立一个表格
        let errText = '';
        let flag = true;
        let sql = "select * from users where username = '" + username + "'";
        db.query(sql, function (err1, result1, field) {
            if (err1) {
                res.status(200).json({
                    "status": false,
                    'flag': false,
                    'msg': err,
                });
                return;
            }
            if (result1.length > 0) {
                res.status(200).json({
                    'status': true,
                    'flag': false,
                });
            } else {
                let newsql = "insert into users(username, password, userpackage) values (?,?,?)";
                let params = [username, password, userPackage];
                db.query(newsql, params, function (err2, result2, field) {
                    if (err2) {
                        res.status(200).json({
                            "status": false,
                            'flag': false,
                            'msg': err,
                        });
                    }
                    let str = JSON.stringify(result2);
                    let js = JSON.parse(str);
                    res.status(200).json({
                        'status': true,
                        'flag': true,
                        'data': js
                    });
                })
            }
        })
    })
}
//把热词Top10和出现次数返回给前端
//把某某一年热词Top10和出现次数返回给前端
//把某一个会议热词Top10和出现次数返回给前端
//把热词全部返回用于制作词云
function dateAnalWords() {
    var queryResult = [];
    var urlencodedParser = bodyParser.urlencoded({
        extended: false
    });
    router.post('/anal', urlencodedParser, function (req, res) {
        let year = req.body.year;
        let meeting = req.body.meeting;
        let sqlQuery = "SELECT * FROM keywords order by times limit 0,10";
        db.query(sqlQuery, function (err, result) {
            if (err) {
                errSend(res, err);
                return;
            }
            var tb = JSON.parse(JSON.stringify(result));
            queryResult[0] = tb;
        });
        sqlQuery = "SELECT * FROM keywords where meeting = '" +
            meeting + "'order by times limit 0,10 ";
        db.query(sqlQuery, function (err, result) {
            if (err) {
                errSend(res, err);
                return;
            }
            var tb = JSON.parse(JSON.stringify(result));
            queryResult[1] = tb;
        });
        sqlQuery = "SELECT * FROM keywords where year = '" +
            year + "'order by times limit 0,10 ";
        db.query(sqlQuery, function (err, result) {
            if (err) {
                errSend(res, err);
                return;
            }
            var tb = JSON.parse(JSON.stringify(result));
            queryResult[2] = tb;
        });
        sqlQuery = "SELECT * FROM keywords ";
        db.query(sqlQuery, function (err, result) {
            if (err) {
                errSend(res, err);
                return;
            }
            var tb = JSON.parse(JSON.stringify(result));
            queryResult[3] = tb;
        });
        res.status(200).json({
            'status': true,
            "hotWords": queryResult[0],
            "hotMeetingWords": queryResult[1],
            "hotYearWords": queryResult[2],
            "keywords": queryResult[3],
        })
    })
}
//初始化用户论文列表

function userPaperList() {
    var urlencodedParser = bodyParser.urlencoded({
        extended: false //表示使用系统模块querystring来处理，也是官方推荐的
    });
    router.post('/manager', urlencodedParser, function (req, res) {
        let paperPackage = req.body.package;
        let sql = "select * from " + paperPackage;
        db.query(sql, function (err, result, field) {
            if (err) {
                errSend(res, err);
            }
            let str = JSON.stringify(result);
            let js = JSON.parse(str);
            res.status(200).json({
                'status': true,
                'data': js
            });
        })
    })
}
//处理用户输入
function userInput() {
    var urlencodedParser = bodyParser.urlencoded({
        extended: false //表示使用系统模块querystring来处理，也是官方推荐的
    });
    var keywordsList = [];
    var wordList = [];
    var paperIdList = [];
    let flag = true;
    router.get('/manager', urlencodedParser, function (req, res) {
        let input = req.query.input;
        let userPackage = req.query.package;
        keywordsList = sh.stringSplit(input);
        keywordsList.forEach((item, index) => {
            let sql = "select * from " + userPackage + " where name like '%" +
                item + "%' or keyword like '%" + item + "%'";
            db.query(sql, function (err, result, field) {
                if (err) {
                    errSend(res, err);
                }
                let str = JSON.stringify(result);
                let js = JSON.parse(str);
                for (let i = 0; i < result.length && flag; i++) {
                    console.log("  " + result[i].id);
                    for (let j = 0; j < paperIdList.length; j++) {
                        if (result[i].id === paperIdList[j]) {
                            flag = false;
                        }
                    }
                    if (flag) {
                        paperIdList.push(result[i].id);
                        wordList.push(result[i]);
                    }
                }
            })
        });
        if (wordList.length == 0) {
            res.send({
                "status": true,
                "msg": "none",
                "data": "[]"
            });
            return;
        }
        res.send({
            "status": true,
            "msg": "",
            "data": wordList
        });
    })
}
//**当数据库连接错误时调用 */
function errSend(res, err) {
    res.status(200).json({
        "status": false,
        'msg': err,
    });
}

function paperInf() {

}
//点击图标中的keyword展示keyword内容
function keywordsAnal() {
    var urlencodedParser = bodyParser.urlencoded({
        extended: false //表示使用系统模块querystring来处理，也是官方推荐的
    });
    router.get('/anal', urlencodedParser, function (req, res) {
        let keywordContent = req.query.keyword;
        let delPaperId = req.query.delid;
        //console.log(delPaperId);
        if (keywordContent != undefined) {
            selKeywords(res, keywordContent);
        }//20210328这部分要处理论文列表的删除，并且当keywordcontent和delpaperid都传来内容时该怎么办
    })
}
//**查找关键字并发送请求*/
function selKeywords(res, keywordContent) {
    let sql = "select * from keywords where keyword like '%" +
        keywordContent + "%'";
    db.query(sql, function (err, result, field) {
        if (err) {
            errSend(res, err);
        }
        let str = JSON.stringify(result);
        let js = JSON.parse(str);
        res.send({
            "status": true,
            "msg": "",
            "data": js
        });
    })
}

function delPaper() {
    var urlencodedParser = bodyParser.urlencoded({
        extended: false //表示使用系统模块querystring来处理，也是官方推荐的
    });
    router.get('/anal', urlencodedParser, function (req, res) {

    })
}

module.exports = router