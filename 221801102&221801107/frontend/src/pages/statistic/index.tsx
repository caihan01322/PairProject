import { Row, Col, Card, Menu } from 'antd';
import React, { useEffect, useMemo, useState } from 'react';
import { LineChart, WordCloud, SmallLineChart, RoseChart } from '@/components';
import styles from './index.less';
import { useDispatch, useSelector } from 'umi';
import { ModelNameSpaces, RootStore } from '@/types';

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
  const triggerFetch = () => {
    setLoading(true);
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
        <SmallLineChart data={ICCVWordsData} />
      </Card>
    ),
    [ICCVWordsData],
  );

  const ECCVChart = useMemo(
    () => (
      <Card title="ECCV总体走势图" bodyStyle={{ height: '20vh' }}>
        <SmallLineChart data={ECCVWordsData} />
      </Card>
    ),
    [ECCVWordsData],
  );

  const EVPRChart = useMemo(
    () => (
      <Card title="CVPR总体走势图" bodyStyle={{ height: '20vh' }}>
        <SmallLineChart data={CVPRWordsData} />
      </Card>
    ),
    [CVPRWordsData],
  );

  const RoseMemoChart = useMemo(
    () => (
      <Card title="玫瑰图" bodyStyle={{ height: '20vh' }}>
        <RoseChart data={cloudData} />
      </Card>
    ),
    [cloudData],
  );

  const WordCloudMemo = useMemo(
    () => (
      <Card
        title="词云"
        className={styles.wordCloud}
        bodyStyle={{ height: 'calc(40vh - 12px)' }}
      >
        <WordCloud data={cloudData} />
      </Card>
    ),
    [cloudData],
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
            <LineChart data={selectData()} />
          </Card>
        </Col>
      </Row>
    </div>
  );
}
