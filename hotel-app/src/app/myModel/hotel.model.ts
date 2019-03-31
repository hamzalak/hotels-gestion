
import { manager } from './manager.model' ;
import { room } from './room.model' ; 
export class hotel{

id : number  ;
adresse : string ;
nom : string ;
stars : number ;
manager : manager ;
rooms : room[] ;
}
