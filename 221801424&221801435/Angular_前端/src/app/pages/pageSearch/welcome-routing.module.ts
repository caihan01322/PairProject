import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { WelcomeComponent } from './welcome.component';
import { SearchComponentComponent} from '../pageSearch/components/search-component/search-component.component'
import { SearchInputComponent } from './components/search-input/search-input.component';

const routes: Routes = [
  { path: '', component: SearchComponentComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class WelcomeRoutingModule { }
