const koa = require('koa')
const app = new koa()
app.use(async (ctx, next) => {
  if (ctx.request.path === '/') { // 首页
    ctx.response.status = 200
    ctx.response.body = 'index'
  } else if (ctx.request.path === '/content') { 
    ctx.response.status = 200
    ctx.response.body = 'list'
  } else {
    ctx.throw(404, 'Not found') // 404
  }
await next()
})

app.listen(3000)