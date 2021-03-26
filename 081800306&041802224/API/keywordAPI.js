const model = require("../models/Keywords");
const Router = require("koa-router");
let router = new Router();

let Keywords = model.Keywords; //获取User模型

router.get('/hot', async(ctx) => {
    ctx.body = '欢迎来到hottop页！'
});
//注册
router.post('/xxx', async(ctx) => {
    let registerUser = ctx.request.body;
    await Keywords.create({
            username: registerUser.username,
            password: registerUser.password
        })
        .then((result) => {
            ctx.body = {
                code: 200,
                msg: '注册成功!',
                message: result
            }
        })
        .catch(err => {
            ctx.body = {
                code: 500,
                msg: '注册失败！',
                message: err
            }
        })
});
//查找所有
router.get('/list', async(ctx) => {
    try {
        let result = await Keywords.findAll();
        if (result) {
            console.log(result);
            ctx.body = {
                code: 200,
                message: result
            }
        } else {
            console.log('表中没有数据!');
            ctx.body = {
                code: 500,
                message: '表中没有数据!',
            };
        }
    } catch (error) {
        ctx.body = {
            code: 500,
            message: '错误',
            data: err
        };
    }

})


module.exports = router;