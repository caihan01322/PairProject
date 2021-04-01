import { Input } from 'antd';
import { SearchProps } from 'antd/lib/input';
import { UploadOutlined } from '@ant-design/icons';
import { setClsPrefixHOC } from '@/utils/setClsPrefixHOC';
import Prefix from '../constants';
import './index.less';

const setClsPrefix = setClsPrefixHOC(Prefix.themeSearch);

const { Search } = Input;

const ThemeSearch = (props: SearchProps) => {
  const { placeholder, enterButton, size, ...restProps } = props;
  const coverProps = { placeholder, enterButton, size };
  return (
    <Search
      {...restProps}
      className={setClsPrefix()}
      placeholder="请输入论文标题"
      enterButton="搜索"
      suffix={<UploadOutlined />}
      size="large"
      {...coverProps}
    />
  );
};

export default ThemeSearch;
