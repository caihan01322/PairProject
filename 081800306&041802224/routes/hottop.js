const Router = require('koa-router');

const router = new Router();
router.get('/hot', async (ctx) => {
  await ctx.render('hottop');
});
module.exports = router;
