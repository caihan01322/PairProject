import { Effect, ImmerReducer } from 'umi';
import { ModelNameSpaces } from '@/types';

export interface UserModelState {
  isLogin: boolean;
  name: string | null;
  /** 头像 url */
  avatar: string | null;
}

export interface UserModelType {
  namespace: ModelNameSpaces.User;
  state: UserModelState;
  effects: {
    login: Effect;
  };
  reducers: {
    save: ImmerReducer<UserModelState>;
  };
}

export const initialState = {
  isLogin: true,
  avatar: null,
  name: 'huro',
};

const UserModel: UserModelType = {
  namespace: ModelNameSpaces.User,
  state: initialState,
  effects: {
    *login({ payload }, { call, put }) {
      console.log(payload);
    },
  },
  reducers: {
    save(state, action) {
      state.name = action.payload;
    },
  },
};

export default UserModel;
