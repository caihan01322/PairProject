import { Result, Button } from 'antd';

const ResultPage = () => {
  return (
    <Result
      style={{ marginTop: '20vh' }}
      status="404"
      title="404"
      subTitle="不好意思啊, 你访问的网页不存在"
      extra={
        <Button type="primary" href="/">
          返回主页
        </Button>
      }
    />
  );
};

export default ResultPage;
