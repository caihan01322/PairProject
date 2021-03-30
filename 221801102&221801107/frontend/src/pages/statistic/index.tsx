import { Row, Col, Card, Menu } from 'antd';
import React from 'react';
import { LineChart, RoseChart, WordCloud } from '@/components';
import styles from './index.less';

export default function Statistic() {
  const handleClick = (menuProps: any) => {
    console.log(menuProps.key);
  };

  return (
    <div className={styles.container}>
      <Row gutter={16}>
        <Col span="6">
          <Card
            title="词云"
            className={styles.wordCloud}
            bodyStyle={{ height: '20vh' }}
          >
            <WordCloud />
          </Card>
        </Col>
        <Col span="6">
          <Card
            title="玫瑰图"
            className={styles.roseChart}
            bodyStyle={{ height: '20vh' }}
          >
            <RoseChart />
          </Card>
        </Col>
        <Col span="6">
          <Card
            title="玫瑰图"
            className={styles.roseChart}
            bodyStyle={{ height: '20vh' }}
          >
            <RoseChart />
          </Card>
        </Col>
        <Col span="6">
          <Card
            title="玫瑰图"
            className={styles.roseChart}
            bodyStyle={{ height: '20vh' }}
          >
            <RoseChart />
          </Card>
        </Col>
        <Col span="24" style={{ marginTop: '50px' }}>
          <Menu
            onClick={handleClick}
            defaultSelectedKeys={['CVPR']}
            mode="horizontal"
            theme="dark"
          >
            <Menu.Item key="CVPR">CVPR</Menu.Item>
            <Menu.Item key="ICCV">ICCV</Menu.Item>
            <Menu.Item key="ECCV">ECCV</Menu.Item>
          </Menu>
          <Card
            title={null}
            className={styles.lineChart}
            bodyStyle={{ height: '40vh' }}
          >
            <LineChart />
          </Card>
        </Col>
      </Row>
    </div>
  );
}
