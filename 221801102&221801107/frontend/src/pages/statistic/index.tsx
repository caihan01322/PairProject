import { Row, Col, Card, Menu } from 'antd';
import React, { useEffect, useMemo, useState } from 'react';
import {
  LineChart,
  WordCloud,
  SmallLineChart,
  RoseChart,
  ChartLoading,
} from '@/components';
import styles from './index.less';
import { useDispatch, useSelector } from 'umi';
import { ModelNameSpaces, RootStore } from '@/types';
import delay from 'delay';

export default function Statistic() {
  const dispatch = useDispatch();
  const [loading, setLoading] = useState(false);
  const {
    cloudData,
    ECCVWordsData,
    CVPRWordsData,
    ICCVWordsData,
    menuKey,
  } = useSelector((store: RootStore) => {
    const { [ModelNameSpaces.Statistic]: StatisticModal } = store;
    return StatisticModal;
  });
  const triggerFetch = async () => {
    setLoading(true);
    await delay(2000);
    dispatch({
      type: `${ModelNameSpaces.Statistic}/getCloud`,
    });
    dispatch({
      type: `${ModelNameSpaces.Statistic}/getWords`,
    });
    setLoading(false);
  };

  useEffect(() => {
    triggerFetch();
  }, []);

  const handleClick = (menuProps: any) => {
    dispatch({
      type: `${ModelNameSpaces.Statistic}/changeMenuKey`,
      payload: menuProps.key,
    });
  };

  const selectData = () => {
    switch (menuKey) {
      case 'CVPR':
        return CVPRWordsData;
      case 'ICCV':
        return ICCVWordsData;
      case 'ECCV':
        return ECCVWordsData;
      default:
        console.error('something error!');
        return [];
    }
  };

  const ICCVChart = useMemo(
    () => (
      <Card title="ICCV总体走势图" bodyStyle={{ height: '20vh' }}>
        <ChartLoading loading={loading}>
          <SmallLineChart data={ICCVWordsData} />
        </ChartLoading>
      </Card>
    ),
    [ICCVWordsData, loading],
  );

  const ECCVChart = useMemo(
    () => (
      <Card title="ECCV总体走势图" bodyStyle={{ height: '20vh' }}>
        <ChartLoading loading={loading}>
          <SmallLineChart data={ECCVWordsData} />
        </ChartLoading>
      </Card>
    ),
    [ECCVWordsData, loading],
  );

  const EVPRChart = useMemo(
    () => (
      <Card title="CVPR总体走势图" bodyStyle={{ height: '20vh' }}>
        <ChartLoading loading={loading}>
          <SmallLineChart data={CVPRWordsData} />
        </ChartLoading>
      </Card>
    ),
    [CVPRWordsData, loading],
  );

  const RoseMemoChart = useMemo(
    () => (
      <Card title="玫瑰图" bodyStyle={{ height: '20vh' }}>
        <ChartLoading loading={loading}>
          <RoseChart data={cloudData} />
        </ChartLoading>
      </Card>
    ),
    [cloudData, loading],
  );

  const WordCloudMemo = useMemo(
    () => (
      <Card
        title="词云"
        className={styles.wordCloud}
        bodyStyle={{ height: 'calc(40vh - 12px)' }}
      >
        <ChartLoading loading={loading}>
          <WordCloud data={cloudData} />
        </ChartLoading>
      </Card>
    ),
    [cloudData, loading],
  );

  return (
    <div className={styles.container}>
      <Row gutter={16}>
        <Col sm={24} lg={12} xxl={6}>
          {RoseMemoChart}
        </Col>
        <Col sm={24} lg={12} xxl={6}>
          {ICCVChart}
        </Col>
        <Col sm={24} lg={12} xxl={6}>
          {ECCVChart}
        </Col>
        <Col sm={24} lg={12} xxl={6}>
          {EVPRChart}
        </Col>
        <Col sm={24} lg={24} xxl={6} style={{ marginTop: '50px' }}>
          {WordCloudMemo}
        </Col>
        <Col sm={24} lg={24} xxl={18} style={{ marginTop: '50px' }}>
          <Menu
            onClick={handleClick}
            selectedKeys={[menuKey]}
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
            <ChartLoading loading={loading}>
              <LineChart data={selectData()} />
            </ChartLoading>
          </Card>
        </Col>
      </Row>
    </div>
  );
}
