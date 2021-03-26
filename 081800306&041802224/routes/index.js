const Router = require('koa-router');

const router = new Router();
router.get('/', async (ctx) => {
<<<<<<< HEAD
=======
  // console.log(ctx.query);
>>>>>>> a5ec4632ae7dee20118599a1f7207a6160086879
  await ctx.render('index');
});
module.exports = router;
