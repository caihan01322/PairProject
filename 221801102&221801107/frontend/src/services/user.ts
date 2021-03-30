import { BASE_URL } from '@/constants';
import request from 'umi-request';

const login = (code: string) => {
  return request.get(`${BASE_URL}/login`, {
    params: {
      code,
    },
  });
};

const logout = () => {
  return request.get(`${BASE_URL}/logout`);
};

export { login, logout };
