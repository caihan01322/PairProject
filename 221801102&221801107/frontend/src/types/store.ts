import { initialState as UserInitialState } from '@/models/user';
import { initialState as HomeInitialState } from '@/pages/models';
import { initialState as SearchInitialState } from '@/pages/search/models';

type UserModel = typeof UserInitialState;
type HomeModel = typeof HomeInitialState;
type SearchModel = typeof SearchInitialState;

enum ModelNameSpaces {
  User = 'User',
  Home = 'Home',
  Search = 'Search',
}

type RootStore = {
  [key in ModelNameSpaces.User]: UserModel;
} &
  {
    [key in ModelNameSpaces.Home]: HomeModel;
  } &
  {
    [key in ModelNameSpaces.Search]: SearchModel;
  };

export { ModelNameSpaces, RootStore };
