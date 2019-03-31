import { Component, OnInit  } from '@angular/core';
import { room } from "../../myModel/room.model" ;
import { MesHotelsComponent }  from '../mes-hotels/mes-hotels.component' ;
import { HotelService } from '../../myService/hotel.service' ;
import { Observable } from 'rxjs' ;
@Component({
  selector: 'app-hotel-rooms',//<app-hotel-rooms></app-hotel-rooms>
  templateUrl: './hotel-rooms.component.html',
  styleUrls: ['./hotel-rooms.component.css']
})
export class HotelRoomsComponent implements OnInit  {

   submit: boolean = false ;
   meRooms : room[] ;
   nomHotel : string ;
   room = new room() ;

  constructor( private hotelService : HotelService) { }

  ngOnInit() {

     if(this.hotelService.roomsOfHotel.nom==""){

           this.nomHotel = "default" ;

     }else{

         this.hotelService.roomsOfHotel.rooms.subscribe(data =>  this.meRooms= data) ;


         this.nomHotel = this.hotelService.roomsOfHotel.nom ;
    }

  }
/////////////////////////////////////////////////

/*  Supprsession du room */

suSubmit : boolean = true ;

  suppression(id: number){

    this.hotelService.deleteRoom(id) ;
    this.submit = true ;
    this.suSubmit = false ;
  }



  /*  Ajout du room */

 submitAjout : boolean = true ;

  showAjout(){
     this.submit =true ;
     this.submitAjout = false ;
  }

  submitRoom(){

    this.hotelService.createRoom(this.hotelService.roomsOfHotel.idHotel,this.room)
    .subscribe(data=> console.log(data)) ;

    setTimeout( ()=> {
      this.room = new room() ;
    } , 500) ;


  }

  /*  Actualisation du room */

  submitActu: boolean = true ;
  idActu : number = 0 ;

  showActual(id: number){
     this.idActu = id ;
     this.submit = true ;
     this.submitActu = false ;
  }

  actualisation(){

      this.hotelService.updateRoom(this.idActu,this.room).subscribe(data=> console.log(data)) ;
      setTimeout( ()=> {
        this.room = new room() ;
      } , 500) ;
  }

}
