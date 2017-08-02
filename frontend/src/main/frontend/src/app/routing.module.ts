import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { LanchesComponent } from './lanches/lanches.component';
import { IngredientesComponent } from './ingredientes/ingredientes.component';


const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'lanches', component: LanchesComponent },
  { path: 'ingredientes', component: IngredientesComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})

export class RoutingModule {}
