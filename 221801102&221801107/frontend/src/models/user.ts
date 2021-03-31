import { Effect, ImmerReducer } from 'umi';
import { ModelNameSpaces } from '@/types';
import * as UserServices from '../services/user';

export interface UserModelState {
  isLogin: boolean;
  username: string | null;
  /** 头像 url */
  avatar: string | null;
}

export interface loginDataProps {
  code: number;
  data: {
    CreatedAt: string;
    UpdatedAt: string;
    avatar: string;
    name: string;
  };
  msg: string;
}

export interface UserModelType {
  namespace: ModelNameSpaces.User;
  state: UserModelState;
  effects: {
    /** 登录 */
    login: Effect;
    /** 登出  */
    logout: Effect;
  };
  reducers: {
    changeLogin: ImmerReducer<UserModelState>;
    changeUsername: ImmerReducer<UserModelState>;
    changeAvatar: ImmerReducer<UserModelState>;
  };
}

export const initialState = {
  isLogin: localStorage.getItem('__isLogin__') ? true : false,
  avatar: localStorage.getItem('__avatar__'),
  username: localStorage.getItem('__username__'),
};

const UserModel: UserModelType = {
  namespace: ModelNameSpaces.User,
  state: initialState,
  effects: {
    *login({ payload }, { call, put }) {
      const res: loginDataProps = yield call(UserServices.login, payload);
      const { data, code } = res;
      if (code !== 200) {
        return false;
      }
      const { name, avatar } = data;
      localStorage.setItem('__isLogin__', 'ok');
      yield put({
        type: 'changeLogin',
        payload: true,
      });
      localStorage.setItem('__username__', name);
      yield put({
        type: 'changeUsername',
        payload: name,
      });
      localStorage.setItem('__avatar__', avatar);
      yield put({
        type: 'changeAvatar',
        payload: avatar,
      });
      return true;
    },
    *logout({ payload }, { call, put }) {
      yield call(UserServices.logout, payload);
      localStorage.removeItem('__isLogin__');
      yield put({
        type: 'changeLogin',
        payload: false,
      });
    },
  },
  reducers: {
    changeLogin(state, action) {
      state.isLogin = action.payload;
    },
    changeUsername(state, action) {
      state.username = action.payload;
    },
    changeAvatar(state, action) {
      state.avatar = action.payload;
    },
  },
};

export default UserModel;
