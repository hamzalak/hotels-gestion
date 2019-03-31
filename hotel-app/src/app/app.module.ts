import { BrowserModule } from '@angular/platform-browser';
import { NgModule  } from '@angular/core';
import {   ReactiveFormsModule, FormsModule } from '@angular/forms' ;
import  { HttpClientModule} from '@angular/common/http' ;
import { AppComponent } from './app.component';
import {  RouterModule , Routes , Router } from '@angular/router'
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HotelNvComponent } from './myComponent/hotel-nv/hotel-nv.component';
import { LayoutModule } from '@angular/cdk/layout';
import {
  MatInputModule  ,
  MatToolbarModule,
  MatButtonModule,
  MatSidenavModule,
  MatIconModule,
  MatListModule ,
  MatCardModule,
  MatGridListModule,
  MatMenuModule, MatTableModule, MatPaginatorModule, MatSortModule
} from '@angular/material';
import { RechercheComponent } from './myComponent/recherche/recherche.component';
import { HotelAjoutComponent } from './myComponent/hotel-ajout/hotel-ajout.component';
import { MesHotelsComponent } from './myComponent/mes-hotels/mes-hotels.component';
import { HotelRoomsComponent } from './myComponent/hotel-rooms/hotel-rooms.component';
import { ContactComponent } from './myComponent/contact/contact.component';

const routes : Routes = [
  //{path: '' , component : HotelListComponent} // partie routes dans nav , sans oublier
                                              //<router-outlet></router-outlet>
   {path:'', component: MesHotelsComponent }
  ,{path: 'recherche' , component : RechercheComponent}
  ,{path: 'ajout' , component : HotelAjoutComponent}
  ,{path: 'rooms' , component : HotelRoomsComponent}
  ,{path: 'contact' , component : ContactComponent}


] ;


@NgModule({
  declarations: [
    AppComponent,
    HotelNvComponent,
    RechercheComponent,
    HotelAjoutComponent,
    MesHotelsComponent,
    HotelRoomsComponent,
    ContactComponent,
   ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule ,
    RouterModule.forRoot(routes) , // partie navigation
    MatCardModule ,  // PARTIE cards
    MatInputModule , // partie input (form)
    ReactiveFormsModule, //formulaire
    FormsModule, MatGridListModule, MatMenuModule, MatTableModule, MatPaginatorModule, MatSortModule // Formulaire
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
