const express  = require("D:/nodeJS/node_global/node_modules/express");
const app = express();

const bodyParser = require('body-parser')

// parse application/x-www-form-urlencoded
app.use(bodyParser.urlencoded({ extended: false }))
// parse application/json
app.use(bodyParser.json())
// 全局 中间件  解决所有路由的 跨域问题
app.all('*',function (req,res,next) {
    res.header('Access-Control-Allow-Origin','*');
    res.header('Access-Control-Allow-Headers','X-Requested-With,Content-Type');
    res.header('Access-Control-Allow-Methods','GET,POST,OPTIONS');
    next();
})
app.use('/test',require('./routeTest.js'));
app.listen(3000,()=>{
    console.log('Hello World');
})