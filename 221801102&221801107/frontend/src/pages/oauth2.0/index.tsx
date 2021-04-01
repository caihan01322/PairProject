import { ModelNameSpaces } from '@/types';
import React, { useEffect } from 'react';
import { history, useDispatch } from 'umi';
import { message } from 'antd';
import delay from 'delay';

const OAuth = () => {
  const dispatch = useDispatch();
  const login = async (
    code: string | undefined | null | string[],
    redirect: string | undefined | null | string[],
  ) => {
    if (
      code == null ||
      Array.isArray(code)
    ) {
      history.push('/');
      return;
    }
    const isOk = await dispatch({
      type: `${ModelNameSpaces.User}/login`,
      payload: code,
    });
    console.log(code);
    if (!isOk) {
      message.error('登录失败, 请检查网络问题');
      await delay(1000);
    }
    history.push('/');
  };
  useEffect(() => {
    const query = history.location.query;
    const code = query && query.code;
    const redirect = query && query.redirect;
    login(code, redirect);
  }, []);
  return <div> ...oauth2.0 跳转界面</div>;
};

export default OAuth;
