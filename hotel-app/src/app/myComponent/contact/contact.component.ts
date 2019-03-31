import { Component, OnInit } from '@angular/core';

import { contact } from '../../myModel/contact.model' ;

import { HotelService } from '../../myService/hotel.service' ;

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {


  showSuccess : boolean = true ;
  myContact = new contact() ;

  constructor(private hotelService : HotelService) { }

  ngOnInit() {

  }

   onSubmit(){

     if(this.myContact.text != null && this.myContact.email != null){

        this.hotelService.sendText(this.myContact).subscribe(data =>{

         console.log(data) ;

        }) ;

        this.showSuccess = false ; 
     }
   }


}
