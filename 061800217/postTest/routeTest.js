const {
    json
} = require('body-parser');
const bodyParser = require('body-parser');
var express = require('express');
var router = express.Router();
let mysql = require("mysql");
let sqlInf = require('../util/dbUtil.js')
let strHan = require('../stringHandle.js');
const e = require('express');
var username = "";
var db_config = sqlInf.db_config
var Pool = require('../util/pool.js')

//有连接时调用
Pool.on('connection', function (connection, err) {
    if (err) {
        console.log(err);
        return;
    }
    console.log("success connection");

});

//一个连接上的活动全部执行完时调用
Pool.on('acquire', function (connection) {
    console.log('Connection %d acquired', connection.threadId);
});
//等待一个有效队列
Pool.on('enqueue', function () {
    console.log('Waiting for available connection slot');
});
//一个连接释放了
Pool.on('release', function (connection) {
    console.log(username);
    console.log('Connection %d released', connection.threadId);
});
Pool.on('end', function () {
    console.log("close");
})
// //结束所有连接
// Pool.end(function (err) {
//   if (err){
//     console.log(err);
//   }else {
//     console.log("连接全部关闭");
//   }
// });
router.get('/users',function(req,res){
    fs.readFile('../postTest.html', function(error, data) {
        res.setHeader('Content-Type', 'text/html;charset="utf-8')
        res.end(data)
    })
})
router.post('/users', function (req, res) {
    
    let sqlQuery = "select * from users"
    // 执行sql语句
    Pool.getConnection(function (err, conn) {
        if (err) throw err;
        conn.query(sqlQuery, function (err, result) {
            if (err) {
                console.log('[SELECT ERROR] - ', err.message);
                return;
            }
            res.send(result);
            conn.release();
        });
    });
})
// function connectSql() { //连接数据库
//     connect.connect(function (err) {
//         if (err) {
//             console.log('error: ${err}!')
//             return false;
//         } else {
//             return true;
//         }
//     });
// }

// function closeMysql() {
//     connect.end((err) => {
//         if (err) {
//             console.log('error: ${err}!')
//         }
//     });
// }

// router.post('/singin', function(req,res){
//     let sqlQuery = "select * from papers where 1";
//     Pool.getConnection(function (err, conn) {
//         if (err) throw err;
// conn.query(sqlQuery, function (err, result) {
//     if (err) {
//         console.log('SQL error: ${err}!');
//     }
//     // var tb = JSON.parse(JSON.stringify(result));
//     // var urlencodedParser = bodyParser.urlencoded({
//     //     extended: false
//     // });
//     // var jsonParser = bodyParser.json();
//     // router.post('/login', urlencodedParser, function (req, res) {
//     //     res.send(tb[0]);
//     // })
// });})
// })


//connectSql();
//selTest();

// router.post('/login', function (req, res) {
//     let name = req.body.username;
//     let pass = req.body.password;
//     let resData = {
//         "name": name,
//         "pass": pass
//     };
//     let errText = '',
//         resultData = '';

//     let sql = "select * from users where username = '" +
//         name + "'";
//     Pool.getConnection(function (err, conn) {
//         if (err) throw err;
//         conn.query(sql, function (err, result) {
//             if (err) {
//                 res.status(200).json({
//                     "status": false,
//                     'msg': err,
//                     "data": resData
//                 });
//                 conn.release();
//                 return;
//             }
//             let str = JSON.stringify(result);
//             let js = JSON.parse(str);
//             let t = js.length;
//             if (t == 0) {
//                 errText = "Account does not exist"
//                 //   conn.release();
//                 //  return ;
//             } else if (pass != result[0].password) {
//                 errText = "Password is wrong";
//             } else {
//                 resultData = result[0];
//             }
//             username = name;
//             res.status(200).json({
//                 'status': true,
//                 'msg': errText,
//                 'data': resultData
//             });
//             conn.release();
//         });
//         //  conn.end();
//     })
//     //conn.end();
//     //closeMysql();
// });

// closeMysql();


module.exports = router

// 模块引入开始


//查询数据