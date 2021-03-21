import React, { useMemo } from 'react';
import { IRouteComponentProps, Link, history } from 'umi';
import { Menu, Layout as ALayout, Avatar, Dropdown, Button } from 'antd';
import { LogoutOutlined, FolderOutlined } from '@ant-design/icons';
import styles from './index.less';

const { Header, Content } = ALayout;

const Layout = ({ children }: IRouteComponentProps) => {
  const AvatarSetting = useMemo(() => {
    return (
      <Menu theme="dark">
        <Menu.Item icon={<FolderOutlined />}>收藏夹</Menu.Item>
        <Menu.Item icon={<LogoutOutlined />}>退出登录</Menu.Item>
      </Menu>
    );
  }, []);

  // mock
  const isLogin = false;
  const selectKey = history.location.pathname;

  return (
    <ALayout>
      <Header>
        <Link to="/">
          <div className={styles.logo} />
        </Link>
        {isLogin && (
          <Dropdown overlay={AvatarSetting}>
            <Avatar className={styles.avatar} size={'small'} />
          </Dropdown>
        )}
        {!isLogin && (
          <Button type="primary" ghost className={styles.signInBtn}>
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
    </ALayout>
  );
};

export default Layout;
