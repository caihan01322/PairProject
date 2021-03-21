import { Input } from 'antd';
import { UploadOutlined } from '@ant-design/icons';
import { setClsPrefixHOC } from '@/utils/setClsPrefixHOC';
import Prefix from '../constants';
import './index.less';

const setClsPrefix = setClsPrefixHOC(Prefix.themeSearch);

const { Search } = Input;

const ThemeSearch = () => {
  return (
    <Search
      className={setClsPrefix()}
      placeholder="请输入论文标题"
      enterButton="搜索"
      suffix={<UploadOutlined />}
      size="large"
    />
  );
};

export default ThemeSearch;
