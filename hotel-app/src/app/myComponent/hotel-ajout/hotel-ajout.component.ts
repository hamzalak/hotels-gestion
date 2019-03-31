import { Component, OnInit } from '@angular/core';
import { HotelService } from '../../myService/hotel.service';
import { hotel } from '../../myModel/hotel.model';
import { manager } from '../../myModel/manager.model';
import { room } from '../../myModel/room.model';


@Component({
  selector: 'app-hotel-ajout',
  templateUrl: './hotel-ajout.component.html',
  styleUrls: ['./hotel-ajout.component.css']
})
export class HotelAjoutComponent implements OnInit {

  constructor(private hotelService: HotelService) { }

  ngOnInit() {
  }
  al: boolean = true;
  hotel = new hotel();
  manager = new manager();
  room = new room();


  id: number;

  saveHotel() {

    this.hotel.manager = this.manager;
    //this.hotel.rooms.push(this.room) ;
    this.hotelService.createHotel(this.hotel).subscribe(
      data => {
        this.id = <number>data ;
        this.hotelService.createRoom(this.id, this.room)
          .subscribe(data => {
                              window.location.reload();
                           }
        );
      }
    );

  }

  onSubmit() {
    this.saveHotel();

  }
  versListDesChambres() {


  }


}
