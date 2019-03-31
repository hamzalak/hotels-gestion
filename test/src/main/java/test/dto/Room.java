package test.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.* ;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="room")

public class Room implements Serializable{

@Id 
@Column
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer id ; 

@Column
private LocalDate dateDispo;

@Column(name="prix")
private Double prix ;



@ManyToOne(fetch = FetchType.LAZY) 
@JsonIgnore
private Hotel hotel ; 

public Room() {
	super();
	// TODO Auto-generated constructor stub
}
public Room (Double prix) {
	super();
	this.prix = prix;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}

public Double getPrix() {
	return prix;
}
public void setPrix(Double prix) {
	this.prix = prix;
}

public Hotel getHotel() {
	return hotel;
}
public void setHotel(Hotel hotel) {
	this.hotel = hotel;
}


public LocalDate getDateDispo() {
	return dateDispo;
}
public void setDateDispo(LocalDate dateDispo) {
	this.dateDispo = dateDispo;
}
@Override
public String toString() {
	return "Room [id=" + id + ", prix=" + prix + "]";
} 

	
}
