import React, { useMemo, useState } from 'react';
import {
  IRouteComponentProps,
  Link,
  history,
  useDispatch,
  useSelector,
} from 'umi';
import { Menu, Layout as ALayout, Avatar, Dropdown, Button } from 'antd';
import { LogoutOutlined, FolderOutlined } from '@ant-design/icons';
import { LoginModal } from '@/components';
import styles from './index.less';
import { GITHUB_CLIENT_ID } from '@/constants';
import { ModelNameSpaces, RootStore } from '@/types';

const { Header, Content } = ALayout;

const Layout = ({ children }: IRouteComponentProps) => {
  const dispatch = useDispatch();
  const { avatar, isLogin, name } = useSelector((store: RootStore) => {
    const { [ModelNameSpaces.User]: UserModel } = store;
    return {
      ...UserModel,
    };
  });

  // mock
  const selectKey = history.location.pathname;

  const [visible, setVisible] = useState(false);

  const AvatarSetting = useMemo(() => {
    return (
      <Menu theme="dark" style={{ marginTop: '20px' }}>
        <Menu.Item icon={<FolderOutlined />}>收藏夹</Menu.Item>
        <Menu.Item icon={<LogoutOutlined />}>退出登录</Menu.Item>
      </Menu>
    );
  }, []);

  return (
    <ALayout>
      <Header>
        <Link to="/">
          <div className={styles.logo} />
        </Link>
        {isLogin && (
          <>
            <Dropdown overlay={AvatarSetting} placement="bottomCenter">
              <span className={styles.name}>{name}</span>
            </Dropdown>
            <Avatar className={styles.avatar} size="small" src={avatar} />
          </>
        )}
        {!isLogin && (
          <Button
            type="primary"
            ghost
            className={styles.signInBtn}
            onClick={() => setVisible(true)}
          >
            登录
          </Button>
        )}
        <Menu theme="dark" mode="horizontal" selectedKeys={[selectKey]}>
          <Menu.Item key="/statistic">
            <Link to="/statistic">数据统计</Link>
          </Menu.Item>
        </Menu>
      </Header>
      <Content>{children}</Content>
      <LoginModal
        visible={visible}
        setVisible={setVisible}
        githubClientId={GITHUB_CLIENT_ID}
      />
    </ALayout>
  );
};

export default Layout;
