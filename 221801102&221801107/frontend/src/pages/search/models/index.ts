import { Effect, ImmerReducer } from 'umi';
import { ModelNameSpaces } from '@/types';

export interface listItemProps {
  id: string;
  contributer: string;
  title: string;
  content: string;
  status: string;
  link: string;
}

export interface SearchModelState {
  list: listItemProps[];
  page: number;
  pageSize: number;
  total: number;
}

export interface SearchModelType {
  namespace: ModelNameSpaces.Search;
  state: SearchModelState;
  effects: {
    search: Effect;
  };
  reducers: {
    save: ImmerReducer<SearchModelState>;
  };
}

export const initialState: SearchModelState = {
  list: [],
  page: 1,
  pageSize: 6,
  total: 0,
};

const SearchModel: SearchModelType = {
  namespace: ModelNameSpaces.Search,
  state: initialState,
  effects: {
    *search({ payload }, { call, put }) {
      console.log(payload);
    },
  },
  reducers: {
    save(state, action) {
      console.log(action);
    },
  },
};

export default SearchModel;
