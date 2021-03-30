module.exports = {
    lintOnSave: false,
    publicPath: '/',
    devServer: {
      proxy: {
          '/xjbs': {
              target: 'http://127.0.0.1:5000',
              changeOrigin: true,
              ws: true,
              secure: false
          },
      }
  },
  css: {
    loaderOptions: {
      less: {
        lessOptions: {
          // If you are using less-loader@5 please spread the lessOptions to options directly
          modifyVars: {
            'primary-color': '#1DA57A',
            'link-color': '#1DA57A',
            'border-radius-base': '2px',
          },
          javascriptEnabled: true,
        },
      },
    },
  },
  }