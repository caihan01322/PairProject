const bodyParser = require('body-parser');
var express = require('express');
var router = express.Router();
let mysql = require("mysql");

let db = require('./util/dbUtil.js');
let sh = require('./stringHandle.js');
const {
    parse
} = require('content-type');

var userid = 0;

const ERRORACCOUNT = "Account does not exist"
const ERRORPWD = "Password is wrong";
const DELETESECCUESS = "Article deleted";
const DELETSFAIL = "Article dose not delete"
const UERDOESEXIT = "usr exit"
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
                errSend(err, res);
            }
            let str = JSON.stringify(result);
            let js = JSON.parse(str);
            let t = js.length;
            //找不到用户，说明用户未注册
            if (t == 0) errText = ERRORACCOUNT;
            //找到用户后判断密码是否正确
            else if (pass != result[0].password) errText = ERRORPWD;
            //密码正确，则返回用户信息
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
        var username = req.body.username;
        var password = req.body.password;
        let userPackage = "paperlists";
        //20200328 如果有时间 这部分改成，每次注册一个用户为他建立一个表格
        //let userPackage = "papers"+username
        let errText = '';
        let flag = true;
        let sql = "select * from users where username = '" + username + "'";
        db.query(sql, function (err1, result1, field) {
            if (err1) {
                errSend(err1, res);
                return;
            }
            if (result1.length > 0) {
                canNewUser(false, false, UERDOESEXIT);
            } else {
                let newsql = "insert into users(username, password, userpackage) values (?,?,?)";
                let params = [username, password, userPackage];
                db.query(newsql, params, function (err2, result2, field) {
                    if (err2) {
                        errSend(res, err2);
                        return;
                    }
                    let sqlQuery = ""
                    let str = JSON.stringify(result2);
                    let js = JSON.parse(str);
                    canNewUser(res, true, true, '', js);
                })
            }
        })
    })
}


//把热词Top10和出现次数返回给前端(数据写死)
//把某某一年热词Top10和出现次数返回给前端（数据写死）
//把某一个会议热词Top10和出现次数返回给前端（数据写死）
//把热词全部返回用于制作词云,更改为把用户数据库前十条返回
function dateAnalWords() {
    //var queryResult = JSON;
    var urlencodedParser = bodyParser.urlencoded({
        extended: false
    });
    router.post('/anal', urlencodedParser, function (req, res) {
        let sqlQuery = "SELECT * FROM paperlistkeyword order by times limit 0,10";
        db.query(sqlQuery, function (err, result) {
            if (err) {
                errSend(res, err);
                return;
            }
            var tb = JSON.parse(JSON.stringify(result));
            res.status(200).json({
                'status': true,
                'msg': '',
                'data': tb
            })
        });
              //let year = req.body.year;
        //let meeting = req.body.meeting;
        // let sqlQuery = "SELECT * FROM keywords order by times limit 0,10";
        // db.query(sqlQuery, function (err, result) {
        //     if (err) {
        //         errSend(res, err);
        //         return;
        //     }
        //     var tb = JSON.parse(JSON.stringify(result));
        //     queryResult[0] = result;
        // });
        // sqlQuery = "SELECT * FROM keywords where meeting = '" +
        //     meeting + "'order by times limit 0,10 ";
        // db.query(sqlQuery, function (err, result) {
        //     if (err) {
        //         errSend(res, err);
        //         return;
        //     }
        //     var tb = JSON.parse(JSON.stringify(result));
        //     queryResult[1] = tb;
        // });
        // sqlQuery = "SELECT * FROM keywords where year = '" +
        //     year + "'order by times limit 0,10 ";
        // db.query(sqlQuery, function (err, result) {
        //     if (err) {
        //         errSend(res, err);
        //         return;
        //     }
        //     var tb = JSON.parse(JSON.stringify(result));
        //     queryResult[2] = tb;
        // });
        // sqlQuery = "SELECT * FROM keywords ";
        // db.query(sqlQuery, function (err, result) {
        //     if (err) {
        //         errSend(res, err);
        //         return;
        //     }
        //     var tb = JSON.parse(JSON.stringify(result));
        //     queryResult[3] = tb;
        // });

        // res.status(200).json({
        //     'status': true,
        //     "hotWords": queryResult[0],
        //     "hotMeetingWords": queryResult[1],
        //     "hotYearWords": queryResult[2],
        //     "keywords": queryResult[3],
        // })
        // console.log(queryResult[0].id);
    })
}
//初始化用户论文列表
function userPaperList() {
    var urlencodedParser = bodyParser.urlencoded({
        extended: false //表示使用系统模块querystring来处理，也是官方推荐的
    });
    router.post('/manager', urlencodedParser, function (req, res) {
        let pageNo = Number(req.body.page);
        let pageCount = 6;
        //Number(req.body.pageSize);
        let pageSize = pageCount;
        let page = (pageNo - 1) * pageCount; //每页显示多少条内容是固定的
        let paperPackage = req.body.package;
        //limit i,j   i为查询结果的索引值  j查询结果返回的数量
        let sql = `select * from ${paperPackage} limit ${page}, ${pageSize}`;
        db.query(sql, function (err, result, field) {
            loadUserPaperList(res, result, err, '');
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
        let pageCount = 6;
        let pageSize = pageCount;
        let page = (pageNo - 1) * pageCount;
        var keywordsList = [];
        var wordList = [];
        var paperIdList = [];
        var resultList = [];
        var zanshi = [];
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
                //当判断该论文删除后，更新论文列表
                if (affectRows) {
                    let sql = `select * from ${userPackage} limit ${page}, ${pageSize}`;
                    db.query(sql, function (err2, result2, field) {
                        loadUserPaperList(res, result2, err2, DELETESECCUESS);
                        return;
                    })
                } //当判断没有论文被删除后，发送错误消息让前端知道，并返回原页面
                else {
                    let sql = `select * from ${userPackage} limit ${page}, ${pageSize}`;
                    db.query(sql, function (err2, result2, field) {
                        loadUserPaperList(res, result2, err2, DELETSFAIL);
                        return;
                    })
                }
            });

        } //20210320 编写测试功能，未检测
        if (input != undefined) {
            keywordsList = sh.stringSplit(input);
            var promise = new Promise(function (resolve, reject) {
                keywordsList.forEach((item, index) => {
                    let sql = "select * from " + userPackage + " where name like '%" +
                        item + "%' or keyword like '%" + item + "%'";
                    db.query(sql, function (err, result, field) {
                        if (err) {
                            errSend(res, err); //发送查询错误的信息
                            return;
                        }
                        //记录论文id，同时，如果找到同一篇文章，则跳过它
                        for (let i = 0; i < result.length && flag; i++) {

                            flag = isPaperAlreadyExist(paperIdList, result, i);
                            if (flag) {
                                paperIdList.push(result[i].id);
                                wordList.push(result[i]);
                                resolve(wordList);
                            }
                        }
                    })
                });
            })
            promise.then(function (value) {
                //根据页号page和页面展示论文条数pageSize 以及查询到的论文数，返回展示的论文列表
                resultList = returnPaperList(page, pageSize, value);
                //发送数据给前端
                res.send({
                    "status": true,
                    "msg": "",
                    "data": resultList
                });
            })
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
//20200331更新
//**查找关键字并发送请求*/
function selKeywords(res, keywordContent) {
    let sql = `select * from papers where keyword like '%${keywordContent}%' limit 0,3`;
        console.log(sql);
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
function isPaperAlreadyExist(paperIdList, result, i) {
    for (let j = 0; j < paperIdList.length; j++) {
        if (result[i].id === paperIdList[j]) return false;
    }
    return true;
}
/**发送用户注册信息是否已经存在 */
function canNewUser(res, status, flag, msg, data) {
    res.status(200).json({
        "status": status,
        'flag': flag,
        'msg': msg,
        'data': data
    });
}
//**加载用户论文列表*/
function loadUserPaperList(res, result, err, msg) {
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
//**删除论文*/（不使用）
// function delPaper(res, err, result) {
//     if (err) {
//         errSend(res, err);
//         return;
//     }
//     // console.log(result.affectedRows);
//     // res.status(200).json({
//     //     "status": true,
//     //     'msg': '',
//     //     'data': result
//     // });
//     return;
// }
//**根据用户输入查找论文*/(不再使用)
// function findPaperFromInput(err, resultList, result, flag, paperIdList, wordList, res, page, pageSize) {

//     //根据分页返回论文列表

// }

// function delPaper() {
//     var urlencodedParser = bodyParser.urlencoded({
//         extended: false //表示使用系统模块querystring来处理，也是官方推荐的
//     });
//     router.get('/anal', urlencodedParser, function (req, res) {

//     })
// }

module.exports = router