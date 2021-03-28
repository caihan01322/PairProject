const Router = require('koa-router');

const router = new Router();
router.get('/', async(ctx) => {
    // console.log(ctx.query);
    await ctx.render('index');
});
module.exports = router;