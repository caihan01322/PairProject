module.exports = {

    devServer: {
        proxy: {
            '': {
                target: 'http://118.31.56.126/api/pages/',
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