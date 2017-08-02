import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { RoutingModule } from './routing.module';
import { SharedModule } from './shared/shared.module';
import { AppComponent } from './app.component';
import { IngredienteService } from './services/ingrediente.service';
import { LancheService } from './services/lanche.service';
import { HomeComponent } from './home/home.component';
import { IngredientesComponent } from './ingredientes/ingredientes.component';
import { LanchesComponent } from './lanches/lanches.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    IngredientesComponent,
    LanchesComponent
  ],
  imports: [
    RoutingModule,
    BrowserModule,
    FormsModule,
    HttpModule,
    SharedModule
  ],
  providers: [IngredienteService,
    LancheService],
  bootstrap: [AppComponent]
})
export class AppModule { }