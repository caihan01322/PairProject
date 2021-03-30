const bodyParser = require('body-parser');
var express = require('express');
var router = express.Router();
let mysql = require("mysql");

let db = require('./util/dbUtil.js');
let sh = require('./stringHandle.js');

var userid = 0;

const ERRORACCOUNT = "Account does not exist"
const ERRORPWD = "Password is wrong";
const DELETESECCUESS = "Article deleted";
const DELETSFAIL = "Article dose not delete"
newUser();
loginIn();
dateAnalWords();
userPaperList();
userInput();
keywordsAnal();
//delPaper();
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
        let pageNo = Number(req.body.page);
        let pageCount = 10;
        //Number(req.body.pageSize);
        let pageSize = pageCount;
        let page = (pageNo - 1) * pageCount; //每页显示多少条内容是固定的
        let paperPackage = req.body.package;
        //limit i,j   i为查询结果的索引值  j查询结果返回的数量
        let sql = `select * from ${paperPackage} limit ${page}, ${pageSize}`;
        db.query(sql, function (err, result, field) {
            loadUserPaperList(res,result, err,'');
        })
    })
}
//处理用户输入
function userInput() {
    var urlencodedParser = bodyParser.urlencoded({
        extended: false //表示使用系统模块querystring来处理，也是官方推荐的
    });
    router.get('/manager', urlencodedParser, function (req, res) {
        let input = req.query.input;
        let userPackage = req.query.package;
        let pageNo = Number(req.query.page);
        let delPaperId = req.query.delid; //处理用户删除论文
        let pageCount = 10;
        let pageSize = pageCount;
        let page = (pageNo - 1) * pageCount;
        var keywordsList = [];
        var wordList = [];
        var paperIdList = [];
        var resultList = [];
        let flag = true;
        let affectRows = 0;
        if (delPaperId != undefined) {
            let delSql = `delete from ${userPackage} where id = ${delPaperId}`;
            db.query(delSql, {}, function (err, result, filed) {
                if (err) {
                    errSend(result, err)
                    return;
                }
                affectRows = result.affectedRows;  
                if (affectRows) {
                    let sql = `select * from ${userPackage} limit ${page}, ${pageSize}`;
                    db.query(sql, function (err2, result2, field) {
                        loadUserPaperList(res,result2, err2,DELETESECCUESS);
                    })
                }else{
                    let sql = `select * from ${userPackage} limit ${page}, ${pageSize}`;
                    db.query(sql, function (err2, result2, field) {
                        loadUserPaperList(res,result2, err2,DELETSFAIL);
                    })
                }              
            })
           
        } //20210320 编写测试功能，未检测
        if (input != undefined) {
            keywordsList = sh.stringSplit(input);
            keywordsList.forEach((item, index) => {
                let sql = "select * from " + userPackage + " where name like '%" +
                    item + "%' or keyword like '%" + item + "%'";
                db.query(sql, function (err, result, field) {
                    findPaperFromInput(err, resultList, result, flag,
                        paperIdList, wordList, res, page, pageSize);
                })
            });
        }

    })
}

//20210328这部分要处理论文列表的删除，并且当keywordcontent和delpaperid都传来内容时该怎么办
function paperInf() {

}
//点击图标中的keyword展示keyword内容
function keywordsAnal() {
    var urlencodedParser = bodyParser.urlencoded({
        extended: false //表示使用系统模块querystring来处理，也是官方推荐的
    });
    router.get('/anal', urlencodedParser, function (req, res) {
        let keywordContent = req.query.keyword;
        //console.log(delPaperId);
        if (keywordContent != undefined) {
            selKeywords(res, keywordContent);
        }
    })
}
//**当数据库连接错误时调用 */
function errSend(res, err) {
    res.status(200).json({
        "status": false,
        'msg': err,
    });
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
//**判断返回的列表内容*/
function returnPaperList(page, pageSize, wordList) {
    if (wordList.length >= page + pageSize) {
        return wordList.splice(page, pageSize);
    } else if (wordList.length >= page && wordList.length < page + pageSize) {
        return wordList.splice(page, wordList.length);
    } else {
        return wordList.splice(0, wordList.length);
    }
}
//**判断paper是否已经在查找到的列表中*/
function isPaperAlreadyExist(paperIdList, result) {
    for (let j = 0; j < paperIdList.length; j++) {
        if (result[i].id === paperIdList[j]) return false;
    }
    return true;
}
//**加载用户论文列表*/
function loadUserPaperList(res,result,err,msg) {
    if (err) {
        errSend(res, err);
    }
    let str = JSON.stringify(result);
    let js = JSON.parse(str);
    console.log("test paper data:" + js[0].meeting);
    res.status(200).json({
        'status': true,
        'msg': msg,
        'data': js
    });
    return;
}
//**删除论文*/
function delPaper(res, err, result) {
    if (err) {
        errSend(res, err);
        return;
    }
    // console.log(result.affectedRows);
    // res.status(200).json({
    //     "status": true,
    //     'msg': '',
    //     'data': result
    // });
    return;
}
//**根据用户输入查找论文*/
function findPaperFromInput(err, resultList, result, flag, paperIdList, wordList, res, page, pageSize) {
    if (err) {
        errSend(res, err);
        return;
    }
    for (let i = 0; i < result.length && flag; i++) {
        //console.log("  " + result[i].id);
        flag = isPaperAlreadyExist(paperIdList, result);
        if (flag) {
            paperIdList.push(result[i].id);
            wordList.push(result[i]);
        }
    }
    //根据分页返回论文列表
    resultList = returnPaperList(page, pageSize, wordList);
    res.send({
        "status": true,
        "msg": "",
        "data": resultList
    });
}

// function delPaper() {
//     var urlencodedParser = bodyParser.urlencoded({
//         extended: false //表示使用系统模块querystring来处理，也是官方推荐的
//     });
//     router.get('/anal', urlencodedParser, function (req, res) {

//     })
// }

module.exports = router