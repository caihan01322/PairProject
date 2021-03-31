import React, { useEffect, useState } from 'react';
import { List, Card, message } from 'antd';
import { HeartFilled, EditOutlined } from '@ant-design/icons';
import { IconText, EditModal } from '@/components';
import { ListProps, useDispatch, useSelector } from 'umi';
import { ModelNameSpaces, RootStore } from '@/types';
import styles from './index.less';
import CV from '@/assets/CV.svg';
import EC from '@/assets/EC.svg';
import IC from '@/assets/IC.svg';

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

  const renderAvatar = (contributor: string) => {
    switch (contributor) {
      case 'cvpr':
        return (
          <img
            src={CV}
            style={{ borderRadius: '50%' }}
            width="40px"
            height="40px"
          />
        );
      case 'iccv':
        return (
          <img
            src={IC}
            style={{ borderRadius: '50%' }}
            width="40px"
            height="40px"
          />
        );
      case 'eccv':
        return (
          <img
            src={EC}
            style={{ borderRadius: '50%' }}
            width="40px"
            height="40px"
          />
        );
      default:
        console.error('something error!');
        return (
          <img
            src=""
            style={{ borderRadius: '50%' }}
            width="40px"
            height="40px"
          />
        );
    }
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
            responsive: true,
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
              extra={renderAvatar(item.contributor)}
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
