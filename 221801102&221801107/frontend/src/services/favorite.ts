import request from 'umi-request';

const opFav = async (code: string) => {
  return request.get('/api/op-fav', {
    params: { code },
  });
};

const fav = async (p: number) => {
  return request.get('/api/fav', {
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
  return request.post('/api/ed-fav', {
    data: {
      code,
      title,
      content,
    },
  });
};

export { opFav, fav, edFav };
