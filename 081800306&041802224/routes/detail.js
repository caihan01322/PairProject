const Router = require('koa-router');

const router = new Router();
router.get('/detail', async (ctx) => {
  await ctx.render('detail');
});
module.exports = router;
