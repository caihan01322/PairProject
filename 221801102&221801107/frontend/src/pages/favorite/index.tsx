import React, { useEffect, useState } from 'react';
import { List, Card, message } from 'antd';
import { HeartFilled, EditOutlined } from '@ant-design/icons';
import { IconText, EditModal } from '@/components';
import { ListProps, useDispatch, useSelector } from 'umi';
import { ModelNameSpaces, RootStore } from '@/types';
import utl from 'lodash';
import styles from './index.less';

export default function Favorite() {
  const dispatch = useDispatch();
  const { pageSize, total, list } = useSelector((store: RootStore) => {
    const { [ModelNameSpaces.Favorite]: FavoriteModel } = store;
    return FavoriteModel;
  });
  const [loading, setLoading] = useState(false);
  const [editLoading, setEditLoading] = useState(false);
  const [visible, setVisible] = useState(false);
  const [initialValues, setInitialValue] = useState({
    title: '',
    content: '',
  });
  const [codeNow, setCodeNow] = useState('');

  const handlePaginationChange = (page: number) => {
    dispatch({
      type: `${ModelNameSpaces.Favorite}/changePage`,
      payload: page,
    });
    fetchData();
  };

  const fetchData = () => {
    setLoading(false);
    dispatch({
      type: `${ModelNameSpaces.Favorite}/getFav`,
    });
    setLoading(true);
  };

  useEffect(() => {
    fetchData();
  }, []);

  const triggerOpFav = async (code: string) => {
    const isOk = await dispatch({
      type: `${ModelNameSpaces.Favorite}/opFav`,
      payload: code,
    });
    if (isOk) {
      message.info('取消收藏成功');
    } else {
      message.info('服务器错误');
    }
  };
  const handleEditClick = (item: ListProps) => {
    const { title, content, code } = item;
    setInitialValue({
      title,
      content,
    });
    setCodeNow(code);
    setVisible(true);
  };

  const handleFinish = async (values: any) => {
    setEditLoading(true);
    const isOk = await dispatch({
      type: `${ModelNameSpaces.Favorite}/edFav`,
      payload: {
        ...values,
        code: codeNow,
      },
    });
    if (isOk) {
      message.info('修改成功');
      dispatch({
        type: `${ModelNameSpaces.Favorite}/changeTitleAndContent`,
        payload: {
          ...values,
          code: codeNow,
        },
      });
    } else {
      message.info('修改失败');
    }
    setEditLoading(false);
    setVisible(false);
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
              key={item.code}
              actions={[
                <IconText
                  icon={<HeartFilled />}
                  onClick={(e) => triggerOpFav(item.code)}
                  text="取消收藏"
                />,
                <IconText
                  icon={<EditOutlined />}
                  text="编辑"
                  onClick={(e) => handleEditClick(item)}
                />,
              ]}
              extra={<img width={80} alt="logo" />}
            >
              <List.Item.Meta description={item.title} />
              {item.content}
            </List.Item>
          )}
        />
      </Card>
      <EditModal
        visible={visible}
        setVisible={setVisible}
        initialValues={initialValues}
        onFinish={handleFinish}
        loading={editLoading}
      />
    </div>
  );
}
