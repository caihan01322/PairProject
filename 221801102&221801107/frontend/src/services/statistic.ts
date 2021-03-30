import request from 'umi-request';

const Cloud = () => {
  return request.get('/api/cloud');
};

const Words = (contributor: string) => {
  return request.get('/api/words', {
    params: {
      contributor,
    },
  });
};

export { Cloud, Words };
