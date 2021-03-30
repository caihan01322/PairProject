import { ModelNameSpaces } from '@/types';
import React, { useEffect } from 'react';
import { history, useDispatch } from 'umi';

const OAuth = () => {
  const dispatch = useDispatch();
  console.log('ok');
  const login = async (
    code: string | undefined | null | string[],
    redirect: string | undefined | null | string[],
  ) => {
    if (
      code == null ||
      Array.isArray(code) ||
      redirect == null ||
      Array.isArray(redirect)
    ) {
      history.push('/');
      return;
    }
    await dispatch({
      type: `${ModelNameSpaces.User}/login`,
      payload: code,
    });
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
