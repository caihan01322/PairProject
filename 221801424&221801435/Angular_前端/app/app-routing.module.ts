import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TendencyComponent } from './pages/page-trend/components/tendency/tendency.component';
import { TopTrendComponent } from './pages/page-trend/components/top-trend/top-trend.component';
import { ArticleImportComponent } from './pages/article-manage/components/article-import/article-import.component';
import { ArticleListComponent } from './pages/article-manage/components/article-list/article-list.component';
import { SearchComponentComponent } from './pages/pageSearch/components/search-component/search-component.component';
import { UserInfoComponent } from './pages/user-manage/components/user-info/user-info.component';
import { UserStarComponent } from './pages/user-manage/components/user-star/user-star.component';
import { LoginComponent } from './pages/user-manage/components/login/login.component';
import { RegisterComponent } from './pages/user-manage/components/register/register.component';

const routes: Routes = [
  { path: 'welcome', loadChildren: () => import('./pages/pageSearch/welcome.module').then(m => m.WelcomeModule) },
  { path: 'page-trend/tendency',component:TendencyComponent},
  { path: 'page-trend/top-trend',component:TopTrendComponent},
  { path: 'article-manage/article-import',component:ArticleImportComponent},
  { path: 'article-manage/article-list',component:ArticleListComponent},
  { path: 'search/home',component:SearchComponentComponent},
  { path: 'user/info',component:UserInfoComponent},
  { path: 'user/star',component:UserStarComponent},
  { path: 'user/login',component:LoginComponent},
  { path: 'user/register',component:RegisterComponent},
  { path: '', pathMatch: 'full', redirectTo: 'search/home' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
