module.exports = {
    lintOnSave: false,
    publicPath: '/',
    devServer: {
      proxy: {
          '/xjbs': {
              target: 'http://47.98.152.179:5000',
              changeOrigin: true,
              ws: true,
              secure: false
          },
      }
  }
  }