import { ThemeSearch } from '@/components';
import styles from './index.less';
import { history } from 'umi';
import { Row, Col, Card, Skeleton, Avatar, Pagination } from 'antd';
import { HeartOutlined } from '@ant-design/icons';

const { Meta } = Card;

export default function SearchPage() {
  console.log(history.location.state);
  const handleSearch = (value: string) => {
    console.log(value);
  };

  return (
    <div className={styles.container}>
      <div className={styles.title}>Huro</div>
      <div className={styles.searchContainer}>
        <ThemeSearch onSearch={handleSearch} />
      </div>
      <Row gutter={{ lg: 32 }} className={styles.row}>
        <Col sm={24} lg={12} xxl={8} className={styles.col}>
          <Card actions={[<HeartOutlined />]}>
            <Skeleton loading={false} avatar active>
              <Meta
                avatar={
                  <Avatar src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png" />
                }
                title="Towards Contextual Learning in Few-Shot"
                description="
                Few-shot Learning (FSL) aims to classify new concepts from a small number of examples. While there have been an incr.."
              />
            </Skeleton>
          </Card>
        </Col>
        <Col sm={24} lg={12} xxl={8} className={styles.col}>
          <Card actions={[<HeartOutlined />]}>
            <Skeleton loading={false} avatar active>
              <Meta
                avatar={
                  <Avatar src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png" />
                }
                title="Towards Contextual Learning in Few-Shot"
                description=" Few-shot Learning (FSL) aims to classify new concepts from a small number of examples. While there have been an incr.."
              />
            </Skeleton>
          </Card>
        </Col>
        <Col sm={24} lg={12} xxl={8} className={styles.col}>
          <Card actions={[<HeartOutlined />]}>
            <Skeleton loading={false} avatar active>
              <Meta
                avatar={
                  <Avatar src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png" />
                }
                title="Towards Contextual Learning in Few-Shot"
                description=" Few-shot Learning (FSL) aims to classify new concepts from a small number of examples. While there have been an incr.."
              />
            </Skeleton>
          </Card>
        </Col>
        <Col sm={24} lg={12} xxl={8} className={styles.col}>
          <Card actions={[<HeartOutlined />]}>
            <Skeleton loading={false} avatar active>
              <Meta
                avatar={
                  <Avatar src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png" />
                }
                title="Towards Contextual Learning in Few-Shot"
                description=" Few-shot Learning (FSL) aims to classify new concepts from a small number of examples. While there have been an incr.."
              />
            </Skeleton>
          </Card>
        </Col>

        <Col sm={24} lg={12} xxl={8} className={styles.col}>
          <Card actions={[<HeartOutlined />]}>
            <Skeleton loading={false} avatar active>
              <Meta
                avatar={
                  <Avatar src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png" />
                }
                title="Towards Contextual Learning in Few-Shot"
                description=" Few-shot Learning (FSL) aims to classify new concepts from a small number of examples. While there have been an incr.."
              />
            </Skeleton>
          </Card>
        </Col>
        <Col sm={24} lg={12} xxl={8} className={styles.col}>
          <Card actions={[<HeartOutlined />]}>
            <Skeleton loading={false} avatar active>
              <Meta
                avatar={
                  <Avatar src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png" />
                }
                title="Towards Contextual Learning in Few-Shot"
                description=" Few-shot Learning (FSL) aims to classify new concepts from a small number of examples. While there have been an incr.."
              />
            </Skeleton>
          </Card>
        </Col>
        <Col sm={24} lg={12} xxl={8} className={styles.col}>
          <Card actions={[<HeartOutlined />]}>
            <Skeleton loading={false} avatar active>
              <Meta
                avatar={
                  <Avatar src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png" />
                }
                title="Towards Contextual Learning in Few-Shot"
                description=" Few-shot Learning (FSL) aims to classify new concepts from a small number of examples. While there have been an incr.."
              />
            </Skeleton>
          </Card>
        </Col>
        <Col sm={24} lg={12} xxl={8} className={styles.col}>
          <Card actions={[<HeartOutlined />]}>
            <Skeleton loading={false} avatar active>
              <Meta
                avatar={
                  <Avatar src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png" />
                }
                title="Towards Contextual Learning in Few-Shot"
                description=" Few-shot Learning (FSL) aims to classify new concepts from a small number of examples. While there have been an incr.."
              />
            </Skeleton>
          </Card>
        </Col>

        <Col sm={24} lg={12} xxl={8} className={styles.col}>
          <Card actions={[<HeartOutlined />]}>
            <Skeleton loading={false} avatar active>
              <Meta
                avatar={
                  <Avatar src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png" />
                }
                title="Towards Contextual Learning in Few-Shot"
                description=" Few-shot Learning (FSL) aims to classify new concepts from a small number of examples. While there have been an incr.."
              />
            </Skeleton>
          </Card>
        </Col>
        <Col sm={24} lg={12} xxl={8} className={styles.col}>
          <Card actions={[<HeartOutlined />]}>
            <Skeleton loading={false} avatar active>
              <Meta
                avatar={
                  <Avatar src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png" />
                }
                title="Towards Contextual Learning in Few-Shot"
                description=" Few-shot Learning (FSL) aims to classify new concepts from a small number of examples. While there have been an incr.."
              />
            </Skeleton>
          </Card>
        </Col>
        <Col sm={24} lg={12} xxl={8} className={styles.col}>
          <Card actions={[<HeartOutlined />]}>
            <Skeleton loading={false} avatar active>
              <Meta
                avatar={
                  <Avatar src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png" />
                }
                title="Towards Contextual Learning in Few-Shot"
                description=" Few-shot Learning (FSL) aims to classify new concepts from a small number of examples. While there have been an incr.."
              />
            </Skeleton>
          </Card>
        </Col>
        <Col sm={24} lg={12} xxl={8} className={styles.col}>
          <Card actions={[<HeartOutlined />]}>
            <Skeleton loading={false} avatar active>
              <Meta
                avatar={
                  <Avatar src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png" />
                }
                title="Towards Contextual Learning in Few-Shot"
                description=" Few-shot Learning (FSL) aims to classify new concepts from a small number of examples. While there have been an incr.."
              />
            </Skeleton>
          </Card>
        </Col>
      </Row>
      <div className={styles.paginationContainer}>
        <Pagination
          total={85}
          showTotal={(total) => `总共 ${total} 个项目`}
          defaultCurrent={1}
          showSizeChanger={false}
          responsive={true}
        />
      </div>
    </div>
  );
}
