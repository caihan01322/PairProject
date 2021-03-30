import { BASE_URL } from '@/constants';
import request from 'umi-request';

const opFav = async (code: string) => {
  return request.get(`${BASE_URL}/op-fav`, {
    params: { code },
  });
};

const fav = async (p: number) => {
  return request.get(`${BASE_URL}/fav`, {
    params: { p },
  });
};

const edFav = async ({
  code,
  title,
  content,
}: {
  code: string;
  title: string;
  content: string;
}) => {
  return request.post(`${BASE_URL}/ed-fav`, {
    data: {
      code,
      title,
      content,
    },
  });
};

export { opFav, fav, edFav };
