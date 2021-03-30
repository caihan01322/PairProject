import request from 'umi-request';
import { BASE_URL } from '@/constants';

const search = (s: string[], p: number) => {
  console.log(s, p);
  return request.post(`${BASE_URL}/search`, {
    data: {
      s,
      p,
    },
  });
};

export { search };
