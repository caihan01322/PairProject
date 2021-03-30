import request from 'umi-request';

const login = (code: string) => {
  return request.get('/api/login', {
    params: {
      code,
    },
  });
};

const logout = () => {
  return request.get('/api/logout');
};

export { login, logout };
