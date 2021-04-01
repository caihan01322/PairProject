var express = require('D:/node/hot/hot_word/node_modules/express');
var app = express.Router();
var sql = require('D:/node/hot/hot_word/node_modules/mysql');
var sqlinf = {
    host: "localhost",
    user: "root",
    password: "",
    database: "java2e"
};
/* 
router.all('*',function (req, res, next) {
    var connection = sql.createConnection(sqlinf);
    connection.connect();

    connection.query("select * from papers", function (err, result, field) {


        res.header("Access-Control-Allow-Credentials", "true");
        // res.header('Access-Control-Allow-Origin', '*');
        res.header('Access-Control-Allow-Origin', 'http://localhost:8080');
        res.header('Access-Control-Allow-Headers', 'Content-Type, Content-Length, Authorization, Accept, X-Requested-With , yourHeaderFeild');
        res.header('Access-Control-Allow-Methods', 'PUT, POST, GET, DELETE, OPTIONS');

        console.log("读取一次数据库");

        res.send(result);
    }); */

/* router.get('/', function (req, res, next) {
    var connection = sql.createConnection(sqlinf);
    connection.connect();

    connection.query("select * from papers", function (err, result, field) {


        res.header("Access-Control-Allow-Credentials", "true");
        // res.header('Access-Control-Allow-Origin', '*');
        res.header('Access-Control-Allow-Origin', 'http://localhost:8080');
        res.header('Access-Control-Allow-Headers', 'Content-Type, Content-Length, Authorization, Accept, X-Requested-With , yourHeaderFeild');
        res.header('Access-Control-Allow-Methods', 'PUT, POST, GET, DELETE, OPTIONS');

        console.log("读取一次数据库");

        res.send(result);

    });
    connection.end();
});  */

app.all("*", function(request, response, next) {
    response.writeHead(200, { "Content-Type": "text/html;charset=utf-8" });      //设置响应头属性值
    next();
});

app.get("/", function(request, response) {
    response.end("欢迎来到首页!");
});

app.get("/about", function(request, response) {
    response.end("欢迎来到about页面!");
});

app.get("*", function(request, response) {
    response.end("404 - 未找到!");
});

app.listen(80);


module.exports = router;
