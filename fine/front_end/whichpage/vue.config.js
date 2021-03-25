module.exports = {

    devServer: {
        proxy: {
            '': {
                target: 'http://47.98.191.214/api/pages/',
                changeOrigin: true,
            },
        },
    },

    // publicPath: '',

    pwa: {
        iconPaths: {
            favicon32: 'favicon.ico',
            favicon16: 'favicon.ico',
            appleTouchIcon: 'favicon.ico',
            maskIcon: 'favicon.ico',
            msTileImage: 'favicon.ico'
        }
    }
};