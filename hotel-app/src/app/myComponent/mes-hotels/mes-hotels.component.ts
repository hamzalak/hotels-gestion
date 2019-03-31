import { Component } from '@angular/core';
import { map, tap } from 'rxjs/operators';
import { Breakpoints, BreakpointObserver } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { hotel } from '../../myModel/hotel.model';
import { room } from '../../myModel/room.model';

import { HotelService } from '../../myService/hotel.service';

@Component({
  selector: 'app-mes-hotels',
  templateUrl: './mes-hotels.component.html',
  styleUrls: ['./mes-hotels.component.css']
})
export class MesHotelsComponent {


  roomsOfHotel: room[];
  hotels = [];
  table = [];
  cards;

  constructor(private breakpointObserver: BreakpointObserver, private hotelService: HotelService) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.hotelService.getHotelList().subscribe((item) => {
      this.table = item;
      this.fournirCards(this.table);
    });
    //console.log(typeof this.hotelService.getHotelList().subscribe() ) ;
    //  this.arr.forEach(item => console.log(item)/*this.hotels.push({ title: item.nom , cols: 1, rows: 1 , objet : item })*/) ;
  }



  fournirCards(val: hotel[]) {

    this.cards = this.breakpointObserver.observe(Breakpoints.Handset).pipe(

      map(({ matches }) => {

        val.forEach(item => this.hotels.push({ title: item.nom, cols: 2, rows: 1, objet: item }));

        if (matches) {
          return [
            { title: 'Card 1', cols: 1, rows: 1 },
            { title: 'Card 2', cols: 1, rows: 1 },
            { title: 'Card 3', cols: 1, rows: 1 },
            { title: 'Card 4', cols: 1, rows: 1 }
          ];
        }

        return this.hotels;

      }))
      ;

  }
  // Suppression d'hotel
  ////////////////////////////////////////////

  deleteHotel(val: number) {

    if (window.confirm("Ếtes vous sûr de vouloir supprimer cet Hotel ? ")) {
      this.hotelService.deleteHotel(val).subscribe(
        data => {
          console.log(data);
        },
        error => console.log(error));
      //Pou rafraichir la page
      window.location.reload();
    }
  }

  // Fornie l'ID au service pour récuperer les rooms
  ////////////////////////////////////////////////////

  provideHotelIdAndName(val: number , nm : string) {

    this.hotelService.getRoomsOfHotel(val,nm);

  }

  //Fournir l'Id au service pour actualiser les donées de l'hotel
  //////////////////////////////////////
  cover: boolean = false;
  id : number ;
  nom : string ;
  hotel = new hotel();
  toUpdateHotel(id: number , nom : string) {
       this.nom = nom ;
       this.id = id ;
       this.cover = true ;
  }
  upDate() {

    this.hotelService.updateHotel(this.id ,this.hotel).subscribe(data => console.log(data)) ;
  }

}


  /** Based on the screen size, switch from standard to one column per row */
