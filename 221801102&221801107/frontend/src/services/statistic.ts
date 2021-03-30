import request from 'umi-request';
import { BASE_URL } from '@/constants';

const Cloud = () => {
  return request.get(`${BASE_URL}/cloud`);
};

const Words = (contributor: string) => {
  return request.get(`${BASE_URL}/words`, {
    params: {
      contributor,
    },
  });
};

export { Cloud, Words };
