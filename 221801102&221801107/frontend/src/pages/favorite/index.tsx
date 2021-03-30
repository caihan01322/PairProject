import React from 'react';
import { List, Card } from 'antd';
import { HeartFilled, EditOutlined } from '@ant-design/icons';
import { IconText } from '@/components';
import { useDispatch, useSelector } from 'umi';
import { ModelNameSpaces, RootStore } from '@/types';
import utl from 'lodash';
import styles from './index.less';

export default function Favorite() {
  const dispatch = useDispatch();
  const { pageSize, total, list } = useSelector((store: RootStore) => {
    const { [ModelNameSpaces.Favorite]: FavoriteModel } = store;
    return utl.pick(FavoriteModel, ['pageSize', 'total', 'list']);
  });

  const handlePaginationChange = (page: number) => {
    dispatch({
      type: `${ModelNameSpaces.Favorite}/changePage`,
      payload: page,
    });
  };

  return (
    <div className={styles.container}>
      <Card title="收藏夹">
        <List
          itemLayout="vertical"
          size="large"
          pagination={{
            onChange: handlePaginationChange,
            pageSize,
            total,
          }}
          dataSource={list}
          renderItem={(item) => (
            <List.Item
              key={item.id}
              actions={[
                <IconText icon={<HeartFilled />} text="取消收藏" />,
                <IconText icon={<EditOutlined />} text="编辑" />,
              ]}
              extra={<img width={80} alt="logo" />}
            >
              <List.Item.Meta description={item.title} />
              {item.content}
            </List.Item>
          )}
        />
      </Card>
    </div>
  );
}
