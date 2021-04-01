import styles from './index.less';
import { ThemeSearch, CircleLetter } from '@/components';
import { Row, Col } from 'antd';
import { history, useSelector } from 'umi';
import { memo } from 'react';
import { ModelNameSpaces, RootStore } from '@/types';

const Home = memo(() => {
  const { keywords } = useSelector((store: RootStore) => {
    const { [ModelNameSpaces.Search]: SearchModal } = store;
    return SearchModal;
  });
  const handleSearch = (value: string) => {
    if (value === '') return;
    history.push(`/search`, {
      s: [value],
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
        <Row className="keywords">
          {keywords.map((item) => (
            <Col className="item">
              <CircleLetter
                letter={item.key}
                onClick={(e) => handleSearch(item.key)}
              />
            </Col>
          ))}
        </Row>
      </div>
    </div>
  );
});

export default Home;
