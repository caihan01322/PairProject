import { IconText, ThemeSearch } from '@/components';
import styles from './index.less';
import { history, useDispatch, useSelector } from 'umi';
import {
  Row,
  Col,
  Card,
  Skeleton,
  Avatar,
  Pagination,
  Button,
  message,
} from 'antd';
import { HeartOutlined, HeartFilled } from '@ant-design/icons';
import { useEffect, useState } from 'react';
import { ModelNameSpaces, RootStore } from '@/types';
import delay from 'delay';
import CV from '@/assets/CV.svg';
import EC from '@/assets/EC.svg';
import IC from '@/assets/IC.svg';

const { Meta } = Card;

export default function SearchPage() {
  const dispatch = useDispatch();

  const { total, page, list, pageSize } = useSelector((store: RootStore) => {
    const { [ModelNameSpaces.Search]: SearchModal } = store;
    return SearchModal;
  });

  const [loading, setLoading] = useState(false);

  const handleSearch = (value: string) => {
    triggerSearch([value]);
  };

  const triggerSearch = async (s?: string[]) => {
    setLoading(true);
    await dispatch({
      type: `${ModelNameSpaces.Search}/search`,
      payload: s,
    });
    await delay(1000);
    setLoading(false);
  };

  const handlePageChange = (page: number) => {
    dispatch({
      type: `${ModelNameSpaces.Search}/changePage`,
      payload: page,
    });
    triggerSearch();
  };

  const state: any = history.location.state;

  useEffect(() => {
    if (state != null) {
      const { s } = state;
      triggerSearch(s);
    }
  }, []);

  const triggerChangeCodeStatus = (status: number, code: string) => {
    dispatch({
      type: `${ModelNameSpaces.Search}/changeCodeStatus`,
      payload: {
        code,
        status,
      },
    });
  };

  const triggerOpFav = async (status: number, code: string) => {
    const isOk = await dispatch({
      type: `${ModelNameSpaces.Favorite}/opFav`,
      payload: code,
    });
    if (isOk) {
      if (status === 0) {
        message.info('收藏成功');
        triggerChangeCodeStatus(1, code);
      } else {
        message.info('取消收藏成功');
        triggerChangeCodeStatus(0, code);
      }
    } else {
      message.info('服务器错误');
    }
  };

  const renderActions = (status: number, code: string) => {
    if (status === 0) {
      return [
        <IconText
          onClick={(e) => triggerOpFav(0, code)}
          icon={<HeartOutlined />}
          text="收藏"
        />,
      ];
    }
    return [
      <IconText
        onClick={(e) => triggerOpFav(1, code)}
        icon={<HeartFilled />}
        text="取消收藏"
      />,
    ];
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
      <div className={styles.title}>Huro</div>
      <div className={styles.searchContainer}>
        <ThemeSearch
          onSearch={handleSearch}
          defaultValue={state ? state.s[0] : ''}
        />
      </div>
      <Row gutter={{ lg: 32 }} className={styles.row}>
        {list.map((item) => (
          <Col sm={24} lg={12} xxl={8} className={styles.col}>
            <Card
              actions={renderActions(item.status, item.code)}
              bodyStyle={{
                height: '168px',
                overflow: 'hidden',
                marginBottom: '10px',
                textOverflow: 'ellipsis',
              }}
            >
              <Skeleton loading={loading} avatar active>
                <Meta
                  avatar={renderAvatar(item.contributor)}
                  title={
                    <Button type="link" href={item.link}>
                      {item.title}
                    </Button>
                  }
                  description={item.content}
                />
              </Skeleton>
            </Card>
          </Col>
        ))}
      </Row>
      <div className={styles.paginationContainer}>
        <Pagination
          total={total}
          pageSize={pageSize}
          showTotal={(total) => `总共 ${total} 个项目`}
          onChange={handlePageChange}
          current={page}
          showSizeChanger={false}
          responsive={true}
        />
      </div>
    </div>
  );
}
