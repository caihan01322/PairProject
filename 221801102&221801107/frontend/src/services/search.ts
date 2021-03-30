import request from 'umi-request';

const search = (s: string[], p: number) => {
  console.log(s, p);
  return request.post('/api/search', {
    data: {
      s,
      p,
    },
  });
};

export { search };
