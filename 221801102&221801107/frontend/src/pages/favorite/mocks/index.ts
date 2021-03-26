import { listItemProps } from '../models';

const list: listItemProps[] = [];

for (let i = 0; i < 20; i += 1) {
  list.push({
    title: 'xxx',
    content: 'xxx',
    id: `${i}`,
    contributer: 'xxx',
    status: 0,
    link: 'xxx',
  });
}

export default list;
