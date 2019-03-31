import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms' ;
import { HotelService } from "../../myService/hotel.service" ;
import { Observable } from 'rxjs';
import { switchMap } from 'rxjs/operators' ;
import  { hotel } from '../../myModel/hotel.model' ;
 @Component({
  selector: 'app-recherche',
  templateUrl: './recherche.component.html',
  styleUrls: ['./recherche.component.css']
})
export class RechercheComponent implements OnInit {

   searchTerme = new FormControl() ;

   result : Observable<hotel[]> ;

   searchTerme$ : Observable<string> = this.searchTerme.valueChanges ; // valueChanges
  /*
  Reactive form instances like FormGroup and FormControl have a valueChanges
   method that returns an observable that emits the latest values. You can
   therefore subscribe to valueChanges to update instance variables or perform operations.
  */
  constructor(private hotelService : HotelService) { }


  ngOnInit() {

       this.searchTerme$.pipe(
       switchMap( item =>  this.hotelService.searchTerm(item.toLowerCase())) ).
       subscribe( data => this.result = data ) ; // Quand on fait subscribe on actualise
         //la valeur !  c'est comme ça le patterne

  }

}
