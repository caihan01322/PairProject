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



