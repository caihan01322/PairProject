import { defineConfig } from 'umi';

export default defineConfig({
  nodeModulesTransform: {
    type: 'none',
  },
  fastRefresh: {},
  antd: {
    dark: true,
  },
  dva: {
    immer: true,
  },
  proxy: {
    '/api': {
      target: 'http://192.168.0.118:8000',
      changeOrigin: true,
      pathRewrite: { '^/api': '/api' },
    },
  },
});
