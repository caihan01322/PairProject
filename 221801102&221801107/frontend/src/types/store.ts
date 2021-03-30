import { initialState as UserInitialState } from '@/models/user';
import { initialState as SearchInitialState } from '@/models/search';
import { initialState as FavoriteInitialState } from '@/models/favorite';
import { initialState as StatisticInitialState } from '@/models/statistic';

type UserModel = typeof UserInitialState;
type SearchModel = typeof SearchInitialState;
type FavoriteModel = typeof FavoriteInitialState;
type StatisticModel = typeof StatisticInitialState;

enum ModelNameSpaces {
  User = 'User',
  Search = 'Search',
  Favorite = 'Favorite',
  Statistic = 'Statistic',
}

type RootStore = {
  [key in ModelNameSpaces.User]: UserModel;
} &
  {
    [key in ModelNameSpaces.Search]: SearchModel;
  } &
  {
    [key in ModelNameSpaces.Favorite]: FavoriteModel;
  } &
  {
    [key in ModelNameSpaces.Statistic]: StatisticModel;
  };

export { ModelNameSpaces, RootStore };
