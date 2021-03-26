const Koa = require('koa');
const Router = require('koa-router');
const views = require('koa-views');
const path = require('path');
const RequireDirectory = require('require-directory');
const serve = require('koa-static');

const app = new Koa();
app.use(serve(path.join(__dirname, '/public')));
app.use(views('view', {
    root: path.join(__dirname, '/view'),
    map: { html: 'ejs' },
}));

function registerRouters(item) {
    if (item instanceof Router) {
        app.use(item.routes());
    }
}
const modules = new RequireDirectory(module, './routes', { visit: registerRouters }); // eslint-disable-line
// app.listen(3000);


const bodyParser = require("koa-bodyparser"); //对传入的请求体进行解析
// const cors = require("koa2-cors");//跨域

app.use(bodyParser());

//引入路由
let keywodes = require("./API/keywordAPI.js");

//装载所有子路由
let router = new Router();
router.use('/keywords', keywodes.routes());

//加载路由中间件
app.use(router.routes());
app.use(router.allowedMethods());


app.use(async(ctx) => {
    ctx.body = '<h1>Hello Bruce1G</h1>'
});

app.listen(5000, () => {
    console.log('*********[Service] starting at port 5000 ***********');
})