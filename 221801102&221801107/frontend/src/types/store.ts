import { initialState as UserInitialState } from '@/models/user';
import { initialState as HomeInitialState } from '@/pages/models';
import { initialState as SearchInitialState } from '@/pages/search/models';
import { initialState as FavoriteInitialState } from '@/pages/favorite/models';

type UserModel = typeof UserInitialState;
type HomeModel = typeof HomeInitialState;
type SearchModel = typeof SearchInitialState;
type FavoriteModel = typeof FavoriteInitialState;

enum ModelNameSpaces {
  User = 'User',
  Home = 'Home',
  Search = 'Search',
  Favorite = 'Favorite',
}

type RootStore = {
  [key in ModelNameSpaces.User]: UserModel;
} &
  {
    [key in ModelNameSpaces.Home]: HomeModel;
  } &
  {
    [key in ModelNameSpaces.Search]: SearchModel;
  } &
  {
    [key in ModelNameSpaces.Favorite]: FavoriteModel;
  };

export { ModelNameSpaces, RootStore };
