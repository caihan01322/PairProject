import { ModelNameSpaces, RootStore } from '@/types';
import { getLocalStroageTopK } from '@/utils/loopLocalStorage';
import { Effect, ImmerReducer } from 'umi';
import * as SearchServices from '@/services/search';

const item = getLocalStroageTopK(window.localStorage, 10);

export interface SearchModelState {
  keywords: { key: string; value: number }[];
  pageSize: number;
  page: number;
  total: number;
  list: List[];
  lastSearchList: string[];
}

export interface List {
  code: string;
  contributor: string;
  title: string;
  content: string;
  status: number;
  link: string;
}

export interface Data {
  total: number;
  page_size: number;
  page: number;
  list: List[];
}

export interface SearchDataProps {
  code: number;
  msg: string;
  data: Data;
}

export interface SearchModelType {
  namespace: ModelNameSpaces.Search;
  state: SearchModelState;
  effects: {
    /** 搜索 */
    search: Effect;
  };
  reducers: {
    changePage: ImmerReducer<SearchModelState>;
    changeTotal: ImmerReducer<SearchModelState>;
    saveData: ImmerReducer<SearchModelState>;
    saveLastSearchList: ImmerReducer<SearchModelState>;
    changeCodeStatus: ImmerReducer<SearchModelState>;
  };
}

export const initialState: SearchModelState = {
  keywords: item,
  page: 1,
  pageSize: 12,
  total: 0,
  list: [],
  lastSearchList: [],
};

const SearchModel: SearchModelType = {
  namespace: ModelNameSpaces.Search,
  state: initialState,
  effects: {
    *search({ payload }, { put, call, select }) {
      const { page, lastSearchList } = yield select((store: RootStore) => {
        const { [ModelNameSpaces.Search]: SearchModal } = store;
        return SearchModal;
      });
      const searchList = payload || lastSearchList || [];
      yield put({
        type: 'saveLastSearchList',
        payload: searchList,
      });
      const res: SearchDataProps = yield call(
        SearchServices.search,
        searchList,
        page,
      );
      for (let s of searchList) {
        if (localStorage.getItem(s) === null) {
          localStorage.setItem(s, '0');
        }
        const item = localStorage.getItem(s) as string;
        localStorage.setItem(s, `${parseInt(item) + 1}`);
      }
      const { code, data } = res;
      if (code !== 200) {
        return false;
      }
      const { total, list } = data;
      yield put({
        type: 'changeTotal',
        payload: total,
      });
      yield put({
        type: 'saveData',
        payload: list,
      });
      return true;
    },
  },
  reducers: {
    changePage(state, action) {
      state.page = action.payload;
    },
    changeTotal(state, action) {
      state.total = action.payload;
    },
    saveData(state, action) {
      state.list = action.payload;
    },
    saveLastSearchList(state, action) {
      state.lastSearchList = action.payload;
    },
    changeCodeStatus(state, action) {
      const { code, status } = action.payload;
      state.list = state.list.map((value) => {
        if (value.code === code) {
          value.status = status;
        }
        return value;
      });
    },
  },
};

export default SearchModel;
