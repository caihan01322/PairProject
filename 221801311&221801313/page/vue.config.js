module.exports = {
  lintOnSave: false,
  publicPath: './',
  devServer: {
    proxy: {
        '/api': {
            target: 'http://106.15.170.116:8000',
            changeOrigin: true,
            ws: true,
            secure: false
        },
    }
}
}
