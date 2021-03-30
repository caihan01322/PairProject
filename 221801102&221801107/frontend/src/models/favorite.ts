import { ModelNameSpaces, RootStore } from '@/types';
import { Effect, ImmerReducer } from 'umi';
import * as FavoriteServices from '@/services/Favorite';

export interface ListProps {
  code: string;
  contributer: string;
  title: string;
  content: string;
  status: number;
  link: string;
}

export interface Data {
  total: number;
  page_size: number;
  page: number;
  list: ListProps[];
}

export interface FavDataProps {
  code: number;
  msg: string;
  data: Data;
}

export interface FavoriteModelState {
  list: ListProps[];
  total: number;
  pageSize: 6;
  page: number;
}

export interface OpFavDataProps {
  code: number;
  msg: string;
  data: {};
}

export interface FavoriteModelType {
  namespace: ModelNameSpaces.Favorite;
  state: FavoriteModelState;
  effects: {
    getFav: Effect;
    opFav: Effect;
    edFav: Effect;
  };
  reducers: {
    saveFav: ImmerReducer<FavoriteModelState>;
    changeTotal: ImmerReducer<FavoriteModelState>;
    changeTitleAndContent: ImmerReducer<FavoriteModelState>;
    changePage: ImmerReducer<FavoriteModelState>;
  };
}

export const initialState: FavoriteModelState = {
  list: [],
  total: 0,
  page: 1,
  pageSize: 6,
};

const FavoriteModel: FavoriteModelType = {
  namespace: ModelNameSpaces.Favorite,
  state: initialState,
  effects: {
    *getFav({ payload }, { call, put, select }) {
      const { page } = yield select((store: RootStore) => {
        const { [ModelNameSpaces.Favorite]: FavoriteModal } = store;
        return FavoriteModal;
      });
      const res: FavDataProps = yield call(FavoriteServices.fav, page);
      const { code, data } = res;
      if (code !== 200) {
        return false;
      }
      const { list, total } = data;
      yield put({
        type: 'saveFav',
        payload: list,
      });
      yield put({
        type: 'changeTotal',
        payload: total,
      });
      return true;
    },
    *opFav({ payload }, { call, put }) {
      const res: OpFavDataProps = yield call(FavoriteServices.opFav, payload);
      const { code } = res;
      if (code !== 200) {
        return false;
      }
      return true;
    },
    *edFav({ payload }, { call, put }) {
      const res: OpFavDataProps = yield call(FavoriteServices.edFav, payload);
      const { code } = res;
      if (code !== 200) {
        return false;
      }
      return true;
    },
  },
  reducers: {
    saveFav(state, action) {
      state.list = action.payload;
    },
    changeTotal(state, action) {
      state.total = action.payload;
    },
    changeTitleAndContent(state, action) {
      const { code, title, content } = action.payload;
      state.list = state.list.map((item) => {
        if (item.code === code) {
          item.title = title;
          item.content = content;
        }
        return item;
      });
    },
    changePage(state, action) {
      state.page = action.payload;
    },
  },
};

export default FavoriteModel;
