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
  }
});
