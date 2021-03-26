<<<<<<< HEAD
const Router = require('koa-router');

const router = new Router();
router.get('/list', async (ctx) => {
  await ctx.render('list');

  /* 测试-start */
const mysql = require('mysql');
let pool = mysql.createConnection({
  host: 'localhost',
  user: 'root',
  password: '123123',
  database: 'pairwork',
});
var str;
pool.query('SELECT * from authors', (err, data, fields) => {
  if (err) return;
  str = JSON.stringify(fields);
  console.log(str);
  // $('.piece').html(str);
});

pool.end();
/* 测试-end */

});
module.exports = router;



=======
const Router = require('koa-router');
const { Article } = require('../model/article');

const router = new Router();
router.get('/list', async (ctx) => {
  // console.log(ctx.query.key);
  let key = ctx.query.key; // eslint-disable-line
  let page = ctx.query.page || 0;
  let articles;
  let list;
  if (key) {
    console.log(key);
    list = await Article.getIdsByKey(key);
    articles = await Article.getArticles(list, page);
  }
  // ctx.body = articles;
  await ctx.render('list', { articles });
});
module.exports = router;
>>>>>>> a5ec4632ae7dee20118599a1f7207a6160086879
