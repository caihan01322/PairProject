const Koa = require('koa');
const Router = require('koa-router');
const views = require('koa-views');
const path = require('path');
const RequireDirectory = require('require-directory');
const serve = require('koa-static');

const app = new Koa();
app.use(serve(path.join(__dirname, '/public')));
app.use(views('view', {
  root: path.join(__dirname, '/view'),
  map: { html: 'ejs' },
}));
function registerRouters(item) {
  if (item instanceof Router) {
    app.use(item.routes());
  }
}
const modules = new RequireDirectory(module, './routes', { visit: registerRouters }); // eslint-disable-line
app.listen(3000);
