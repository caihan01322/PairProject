import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CollectionComponent } from './collection/collection.component';
import { CoreComponent } from './core.component';
import { DataComponent } from './data/data.component';
import { HomeComponent } from './home/home.component';
import { ListComponent } from './list/list.component';

const routes: Routes = [
  {
    path : '',
    component:CoreComponent,
    children:[
        {
          path:'home',
          component:HomeComponent
        },
        {
          path:'list',
          component : ListComponent
        },
        {
          path:'collections/:userid',
          component:CollectionComponent
        },
        {
          path:'data',
          component:DataComponent
        },
        {
          path:'**',
          redirectTo : "home"
        }
        
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CoreRoutingModule { }
