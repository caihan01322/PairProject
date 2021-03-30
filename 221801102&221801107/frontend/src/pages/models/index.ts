import { ModelNameSpaces } from '@/types';



export interface HomeModelState {
  keywords: string[];
}

export interface HomeModelType {
  namespace: ModelNameSpaces.Home;
  state: HomeModelState;
}

export const initialState: HomeModelState = {
  keywords: [],
};

const HomeModel: HomeModelType = {
  namespace: ModelNameSpaces.Home,
  state: initialState,
};

export default HomeModel;
