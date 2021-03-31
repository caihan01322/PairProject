const bodyParser = require('body-parser');
var express = require('express');
var router = express.Router();
let mysql = require("mysql");

let db = require('./util/dbUtil.js');
let sh = require('./stringHandle.js');

const {
    parse
} = require('content-type');

var myMap = new Map();
// myMap.set(0, "zero");
// myMap.set(1, "one");
// for (var [key, value] of myMap) {
//   console.log(key + " = " + value);
// }
function toPaperLists(meeting, year) {
    let sql = `select * from papers where meeting = '${meeting}' and year = ${year} limit 0,10`
    db.query(sql, function (err, result, field) {
        if (err) {
            throw err;
        } else {
            let wordList = []
            var js = JSON.parse(JSON.stringify(result)); //把json字符串转化成 json对象
            //
            for (let i = 0; i < js.length; i++) {
                let sqlQuery = "insert into paperlists(name,year," +
                    "meeting,keyword,link,digest) values (?,?,?,?,?,?)";
                let params = [js[i].name, year, meeting, js[i].keyword, js[i].link, js[i].digest]
                db.query(sqlQuery, params, function (err, result, field) {
                    if (err) throw err;
                    else {
                        console.log(result.affectedRows);
                    }
                })
                wordList = sh.stringSplitByDot(js[i].keyword);
                console.log(sh.stringSplitByDot(js[i].keyword));
                for (let j = 0; j < wordList.length; j++) {
                    let word = wordList[j];
                    if (!myMap.has(word)) {
                        myMap.set(word, 1);
                    } else {
                        let times = myMap.get(word) + 1;
                        myMap.set(word, times);
                    }
                }
                for (var [key, value] of myMap) {
                    if (value > 1) {
                        let sqlinsert = "insert into paperlistkeyword(keyword," +
                            "times,meeting,year) values (?,?,?,?) ";
                        let paramsin = [key , value, meeting, year];
                        db.query(sqlinsert, paramsin, function (err, result, filed) {
                            if (err) {
                                throw err;
                            } else {
                                console.log(key + " = " + value);
                                //console.log(word + " = " + times);
                            }
                        })
                    }
                    
                }
            }
        }

    })
}

function sel(meeting, year) {
    let sql = `select * from papers where meeting = '${meeting}' and year = '${year}' limit 0,400`
    db.query(sql, function (err, result, field) {
        if (err) {
            throw err;
        } else {
            let wordList = []
            var js = JSON.parse(JSON.stringify(result)); //把json字符串转化成 json对象
            //
            for (let i = 0; i < js.length; i++) {
                wordList = sh.stringSplitByDot(js[i].keyword);
                //console.log(sh.stringSplitByDot(js[i].keyword));
                for (let j = 0; j < wordList.length; j++) {
                    let word = wordList[j];
                    if (!myMap.has(word)) {
                        myMap.set(word, 1);
                    } else {
                        myMap.set(word, (myMap.get(word) + 1));
                    }
                }
            }
            for (var [key, value] of myMap) {
                if (value > 2) {
                    let sqlQuery = "insert into keywords (keyword,times,meeting,year) values (?,?,?,?)";
                    let params = [key, value, meeting, year];
                    db.query(sqlQuery, params, function (err, result, filed) {
                        if (err) {
                            throw err;
                        } else {
                            console.log(key + " = " + value);
                        }
                    })
                }
                // console.log(key + " = " + value);

            }
        }

    })
}

function selMeeting(meeting, meetingpackage) {
    let sql = `select * from papers where meeting = '${meeting}' limit 0,500`
    db.query(sql, function (err, result, field) {
        if (err) {
            throw err;
        } else {
            let wordList = []
            var js = JSON.parse(JSON.stringify(result)); //把json字符串转化成 json对象
            //
            for (let i = 0; i < js.length; i++) {
                wordList = sh.stringSplitByDot(js[i].keyword);
                //console.log(sh.stringSplitByDot(js[i].keyword));
                for (let j = 0; j < wordList.length; j++) {
                    let word = wordList[j];
                    if (!myMap.has(word)) {
                        myMap.set(word, 1);
                    } else {
                        myMap.set(word, (myMap.get(word) + 1));
                    }
                }
            }
            for (var [key, value] of myMap) {
                if (value > 2) {
                    let sqlQuery = `insert into ${meetingpackage}(keyword,times) values (?,?)`;
                    let params = [key, value];
                    db.query(sqlQuery, params, function (err, result, filed) {
                        if (err) {
                            throw err;
                        } else {
                            console.log(key + " = " + value);
                        }
                    })
                }
                // console.log(key + " = " + value);

            }
        }

    })
}

function inse(meeting, year) {
    let sql = `select * FROM keywords where meeting='${meeting}' and year=${year} ORDER BY times desc  limit 0,10`
    db.query(sql, function (err, result, field) {
        if (err) throw err
        else {
            //let wordList = []
            var js = JSON.parse(JSON.stringify(result)); //把json字符串转化成 json对象
            for (let i = 0; i < js.length; i++) {
                let keyword = js[i].keyword;
                let time = js[i].times;
                sql = "insert into top10keyword(keyword,meeting,time,year) value(?,?,?,?)"
                let params = [keyword, meeting, time, year]
                db.query(sql, params, function (err, result, field) {
                    if (err) throw err;
                    else {
                        console.log(keyword);
                    }
                })
            }
        }
    })
}


function selTraining() {
    let sql = "select * FROM keywords where keyword='Training' "
    db.query(sql, function (err, result, field) {
        if (err) throw err
        else {
            //let wordList = []
            var js = JSON.parse(JSON.stringify(result)); //把json字符串转化成 json对象
            for (let i = 0; i < js.length; i++) {
                sql = "insert into tran(meeting,year,times) value(?,?,?)"
                let params = [js[i].meeting, js[i].year, js[i].times]
                db.query(sql, params, function (err, result, field) {
                    if (err) throw err;
                    else {
                        // console.log(keyword);
                    }
                })
            }
        }
    })
}



const I = "ICCV"
const E = "ECCV"
const C = "CVPR"
//sel(E,2015)
//inse(I, 2015)
//selMeeting("ECCV","eccvtopkeyword")
//selTraining();
toPaperLists(E, 2016);