import { Effect, ImmerReducer } from 'umi';
import { ModelNameSpaces } from '@/types';
import mockList from '../mocks';

export interface listItemProps {
  id: string;
  contributer: string;
  title: string;
  content: string;
  status: number;
  link: string;
}

export interface FavoriteModelState {
  list: listItemProps[];
  page: number;
  pageSize: number;
  total: number;
}

export interface FavoriteModelType {
  namespace: ModelNameSpaces.Favorite;
  state: FavoriteModelState;
  effects: {
    getData: Effect;
  };
  reducers: {
    save: ImmerReducer<FavoriteModelState>;
    changePage: ImmerReducer<FavoriteModelState>;
  };
}

export const initialState: FavoriteModelState = {
  list: mockList,
  page: 1,
  pageSize: 6,
  total: 20,
};

const FavoriteModel: FavoriteModelType = {
  namespace: ModelNameSpaces.Favorite,
  state: initialState,
  effects: {
    *getData({ payload }, { call, put }) {
      console.log(payload);
    },
  },
  reducers: {
    save(state, action) {
      console.log(action);
    },
    changePage(state, action) {
      state.page = action.payload;
    },
  },
};

export default FavoriteModel;
