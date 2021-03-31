const Router = require('koa-router');
const ejs = require('ejs');
const { Article } = require('../model/article');

const router = new Router();
router.get('/list', async(ctx) => {
    // console.log(ctx.query.key);
    ejs.clearCache();
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
router.get('/getArticles', async(ctx) => {
    let key = ctx.query.key; // eslint-disable-line
    let page = ctx.query.page || 0;
    let kind = ctx.query.kind; // eslint-disable-line
    let articles;
    let list;
    if (key && (kind == 1)) { // eslint-disable-line
        console.log(key);
        list = await Article.getIdsByKey(key);
        if (!list) {
            ctx.body = {
                msg: '未找到该内容，请搜索其他的',
                code: 1,
            };
        }
        articles = await Article.getArticles(list, page);
    }
    if (key && (kind == 0)) { // eslint-disable-line
        articles = await Article.getArticleByid(key);
    }
    if (key && (kind == 2)) { // eslint-disable-line
        articles = await Article.getArticlesByContent(key, page);
    }
    if (key && (kind == 3)) { // eslint-disable-line
        articles = await Article.getArticlesByTitle(key, page);
    }
    ctx.body = articles;
});
module.exports = router;