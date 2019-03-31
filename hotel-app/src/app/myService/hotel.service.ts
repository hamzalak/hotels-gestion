import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, Subject , BehaviorSubject, ReplaySubject } from 'rxjs';
import { tap, map } from 'rxjs/operators';
import { hotel } from '../myModel/hotel.model';
import { manager } from '../myModel/manager.model';
import { room } from '../myModel/room.model' ;
import { contact } from '../myModel/contact.model' ;


@Injectable({
  providedIn: 'root'
})
export class HotelService {


  private baseUrlHotel = 'http://localhost:9090/hotel';
  private baseUrlRoom = 'http://localhost:9090/room';
  private baseUrlContact = 'http://localhost:9090/contact';
  private monJasonLocal = '../assets/hotel.json';



  constructor(private http: HttpClient) { }

  getHotelList(): Observable<any> {

    return this.http.get(/*this.monJasonLocal*/`${this.baseUrlHotel}` + '/all');
  }


  //Recherche d'hotel

  searchTerm(val): Observable<any> {

    return <Observable<hotel[]>>this.http.get(`${this.baseUrlHotel}` + '/all')
      .pipe(map((items: hotel[]) => items.filter(hot => hot.nom.toLowerCase().includes(val))), // map de rxjs prend
        //le array of observables comme argument
        tap(data => console.log(data)));
  }

  //create Hotel

  createHotel(hotel: hotel): Observable<Object> {
    return this.http.post(`${this.baseUrlHotel}` + '/createHotel', hotel);
  }

  //delete Hotel

  deleteHotel(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrlHotel}` + `/${id}`);
  }

// Actualisation de l'hotel

  updateHotel(id : number , hotel: hotel): Observable<Object> {
    return this.http.put(`${this.baseUrlHotel}` + `/${id}`, hotel);
  }


  //  recupère les rooms de l'hotel et partage avec les autre components à travers Subject

  //Observable source
  public roomsOfHotel = {idHotel : 0 , nom : "" , rooms : new Subject<any>() };

  getRoomsOfHotel(val: number , nom : string ) {

      this.roomsOfHotel =  {idHotel : val , nom : nom , rooms : <Subject<any>>this.http.get(`${this.baseUrlRoom}` + '/rooms' + `/${val}`) };


  }


  /////////////////Opertations sur Room////////////////////////////

 //Delete

  deleteRoom(id:number){

      return this.http.delete(`${this.baseUrlRoom}`+`/${id}`) ;

  }

  // Créer Un Room



  //Actualiser Un Room

 updateRoom(val:number, room: room){

    return this.http.put(`${this.baseUrlRoom}`+`/${val}`, room)

 }

/////////////Creation du room /////////////////////////////
 createRoom(val: number , room: room , ){

    return this.http.post(`${this.baseUrlRoom}`+`/${val}`, room) ;

 }
//////////////////Envoies du Text////////////////////////////////

sendText(contact: contact){

   return this.http.post(`${this.baseUrlContact}`+`/sendMail`, contact) ;

}

}
