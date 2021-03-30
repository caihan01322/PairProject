import styles from './index.less';
import { ThemeSearch, CircleLetter } from '@/components';
import { Row, Col } from 'antd';
import { history, ConnectProps, useSelector, useDispatch } from 'umi';
import { RootStore, ModelNameSpaces } from '@/types';
import { memo, useEffect } from 'react';

interface HomeProps extends ConnectProps {}

const Home = memo(() => {
  const handleSearch = (value: string) => {
    if (value === '') return;
    history.push(`/search`, {
      s: [value],
      p: 1,
    });
  };

  return (
    <div className={styles.scoped}>
      <div className="container">
        <Row>
          <div className="logo">Huro</div>
        </Row>
        <Row>
          <ThemeSearch onSearch={handleSearch} />
        </Row>
        <Row style={{ marginTop: '50px', marginLeft: '30px' }}>
          <Col>
            <CircleLetter letter="这是一篇搜索论文的东西" />
          </Col>
          <Col>
            <CircleLetter letter="ADSDS" />
          </Col>
        </Row>
      </div>
    </div>
  );
});

export default Home;
