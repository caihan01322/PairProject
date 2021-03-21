import styles from './index.less';
import { ThemeSearch, CircleLetter } from '@/components';
import { Row, Col } from 'antd';

const Home = () => {
  return (
    <div className={styles.scoped}>
      <div className="container">
        <Row>
          <div className="logo">Huro</div>
        </Row>
        <Row>
          <ThemeSearch />
        </Row>
        <Row style={{ marginTop: '50px', marginLeft: '30px' }}>
          <Col>
            <CircleLetter letter="这是一篇搜索论文的东西" />
          </Col>
          <Col>
            <CircleLetter letter="ADSDS" />
          </Col>
          <Col>
            <CircleLetter letter="B" />
          </Col>
          <Col>
            <CircleLetter letter="C" />
          </Col>
          <Col>
            <CircleLetter letter="D" />
          </Col>
        </Row>
        <Row style={{ marginLeft: '30px' }}>
          <Col>
            <CircleLetter letter="这是一篇搜索论文的东西" />
          </Col>
          <Col>
            <CircleLetter letter="这是一篇搜索论文的东西" />
          </Col>
          <Col>
            <CircleLetter letter="这是一篇搜索论文的东西" />
          </Col>
          <Col>
            <CircleLetter letter="这是一篇搜索论文的东西" />
          </Col>
          <Col>
            <CircleLetter letter="这是一篇搜索论文的东西" />
          </Col>
        </Row>
      </div>
    </div>
  );
};

export default Home;
