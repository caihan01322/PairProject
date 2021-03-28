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
router.get('/getArticles', async (ctx) => {
  let key = ctx.query.key; // eslint-disable-line
  let page = ctx.query.page || 0;
  let articles;
  let list;
  if (key) {
    console.log(key);
    list = await Article.getIdsByKey(key);
    articles = await Article.getArticles(list, page);
  }
  ctx.body = articles;
});
module.exports = router;
