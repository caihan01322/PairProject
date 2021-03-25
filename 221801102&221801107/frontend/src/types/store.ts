import { initialState as UserInitialState } from '@/models/user';
import { initialState as HomeInitialState } from '@/pages/models';

type UserModel = typeof UserInitialState;
type HomeModel = typeof HomeInitialState;

enum ModelNameSpaces {
  User = 'User',
  Home = 'Home',
}

type RootStore =
  {
    [key in ModelNameSpaces.User]: UserModel;
  }
  &
  {
    [key in ModelNameSpaces.Home]: HomeModel;
  };

export { ModelNameSpaces, RootStore };
