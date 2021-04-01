const Router = require('koa-router');
const { keywords } = require('../model/keywords');

const router = new Router();
router.get('/hot', async (ctx) => {
  await ctx.render('hottop');
});
router.post('/hot/gettop', async (ctx) => {
  let words = await keywords.getTopKeys();
  ctx.body = words;
});
router.post('/hot/getcloud', async (ctx) => {
  let words = await keywords.getcloud();
  ctx.body = words;
});
router.post('/hot/getcvpr', async (ctx) => {
  let res1 = await keywords.getCVPR(2017);
  let res2 = await keywords.getCVPR(2018);
  let res3 = await keywords.getCVPR(2019);
  ctx.body = {
    res1,
    res2,
    res3,
  };
});
router.post('/hot/geticcv', async (ctx) => {
  let res1 = await keywords.getICCV(2017);
  let res2 = await keywords.getICCV(2018);
  let res3 = await keywords.getICCV(2019);
  ctx.body = {
    res1,
    res2,
    res3,
  };
});
router.post('/hot/geteccv', async (ctx) => {
  let res1 = await keywords.getECCV(2016);
  let res2 = await keywords.getECCV(2018);
  let res3 = await keywords.getECCV(2020);
  ctx.body = {
    res1,
    res2,
    res3,
  };
});
module.exports = router;
