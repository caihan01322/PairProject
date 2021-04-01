import { ModelNameSpaces, RootStore } from '@/types';
import { Effect, ImmerReducer } from 'umi';
import * as StatisticServices from '@/services/statistic';

export interface cloudData {
  name: string;
  value: number;
}

export interface CloudDataProps {
  code: number;
  msg: string;
  data: cloudData[];
}

export interface WordsData {
  month: string;
  word: string;
  value: number;
}

export interface WordsDataProps {
  code: number;
  msg: string;
  data: WordsData[];
}

export interface StatisticModelState {
  cloudData: cloudData[];
  CVPRWordsData: WordsData[];
  ICCVWordsData: WordsData[];
  ECCVWordsData: WordsData[];
  menuKey: string;
}

export interface StatisticModelType {
  namespace: ModelNameSpaces.Statistic;
  state: StatisticModelState;
  effects: {
    /** 获得词云 */
    getCloud: Effect;
    /** 获得议会折线图 */
    getWords: Effect;
  };
  reducers: {
    saveCloud: ImmerReducer<StatisticModelState>;
    saveCVPRWords: ImmerReducer<StatisticModelState>;
    saveICCVWords: ImmerReducer<StatisticModelState>;
    saveECCVWords: ImmerReducer<StatisticModelState>;
    changeMenuKey: ImmerReducer<StatisticModelState>;
  };
}

export const initialState: StatisticModelState = {
  cloudData: [],
  CVPRWordsData: [],
  ICCVWordsData: [],
  ECCVWordsData: [],
  menuKey: 'ECCV',
};

const StatisticModel: StatisticModelType = {
  namespace: ModelNameSpaces.Statistic,
  state: initialState,
  effects: {
    *getCloud({ payload }, { call, put }) {
      const res: CloudDataProps = yield call(StatisticServices.Cloud);
      const { code, data } = res;
      if (code !== 200) {
        return false;
      }
      yield put({
        type: 'saveCloud',
        payload: data,
      });
      return true;
    },
    *getWords({ payload }, { call, put }) {
      const ECCVRes: WordsDataProps = yield call(
        StatisticServices.Words,
        'eccv',
      );
      const ICCVRes: WordsDataProps = yield call(
        StatisticServices.Words,
        'iccv',
      );
      const CVPRRes: WordsDataProps = yield call(
        StatisticServices.Words,
        'cvpr',
      );
      const { code: ECCVCode, data: ECCVData } = ECCVRes;
      const { code: ICCVCode, data: ICCVData } = ICCVRes;
      const { code: CVPRCode, data: CVPRData } = CVPRRes;
      if (ECCVCode !== 200 || ICCVCode !== 200 || CVPRCode !== 200) {
        return false;
      }
      yield put({
        type: 'saveCVPRWords',
        payload: CVPRData.sort((item1, item2) =>
          item1.month > item2.month ? 1 : -1,
        ),
      });
      yield put({
        type: 'saveECCVWords',
        payload: ECCVData.sort((item1, item2) =>
          item1.month > item2.month ? 1 : -1,
        ),
      });
      yield put({
        type: 'saveICCVWords',
        payload: ICCVData.sort((item1, item2) =>
          item1.month > item2.month ? 1 : -1,
        ),
      });
      return true;
    },
  },
  reducers: {
    saveCloud(state, action) {
      state.cloudData = action.payload;
    },
    saveCVPRWords(state, action) {
      state.CVPRWordsData = action.payload;
    },
    saveECCVWords(state, action) {
      state.ECCVWordsData = action.payload;
    },
    saveICCVWords(state, action) {
      state.ICCVWordsData = action.payload;
    },
    changeMenuKey(state, action) {
      state.menuKey = action.payload;
    },
  },
};

export default StatisticModel;
