const Router = require('koa-router');
const { Article } = require('../model/article');

const router = new Router();
router.get('/detail', async (ctx) => {
  let id = ctx.query.id; // eslint-disable-line
  let article = await Article.getArticleByid(id);
  await ctx.render('detail', { article });
});
router.post('/detail/update', async (ctx) => {
  // console.log(ctx.request.body.title);
  let obj = ctx.request.body;
  await Article.updateArticle(obj);
  ctx.body = {msg: "修改成功", code: 0} //eslint-disable-line
});
router.post('/detail/del', async (ctx) => {
  let obj = ctx.request.body;
  await Article.deleteArticle(obj);
  ctx.body = {msg: '删除成功', code: 0} // eslint-disable-line
});
module.exports = router;
