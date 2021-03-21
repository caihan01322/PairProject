import React, { useMemo } from 'react';
import { IRouteComponentProps } from 'umi';
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

  return (
    <ALayout>
      <Header>
        <div className={styles.logo} />
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
        <Menu theme="dark" mode="horizontal">
          <Menu.Item key="1">数据统计</Menu.Item>
        </Menu>
      </Header>
      <Content>{children}</Content>
    </ALayout>
  );
};

export default Layout;
