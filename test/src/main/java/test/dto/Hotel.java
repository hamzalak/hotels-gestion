package test.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.* ;

import org.springframework.data.repository.query.parser.Part.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="hotel")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Hotel implements Serializable{

@Id
@Column
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer id ;
@Column(name="nom")
private String nom ;
@Column(name="adresse")
private String adresse ;
@Column(name="stars")
private Integer stars ;
@OneToMany(mappedBy="hotel",cascade= CascadeType.ALL, fetch = FetchType.LAZY)///Mapped By : RELATION GÉRÉ PAR L'OBJET hotel de la Classe Room
private List<Room> rooms ; 
@OneToOne
//(cascade = CascadeType.ALL)
private Manager manager ; 
public Hotel() {
	super();
}
public Hotel(String nom, String adresse, Integer stars) {
	super();
	this.nom = nom;
	this.adresse = adresse;
	this.stars = stars;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getAdresse() {
	return adresse;
}
public void setAdresse(String adresse) {
	this.adresse = adresse;
}
public Integer getStars() {
	return stars;
}
public void setStars(Integer stars) {
	this.stars = stars;
}


public List<Room> getRooms() {
	return rooms;
}
public void setRooms(List<Room> rooms) {
	this.rooms = rooms;
}
@Override
public String toString() {
	return "Hotel [id=" + id + ", nom=" + nom + ", adresse=" + adresse + ", stars=" + stars + "]";
} 

public Manager getManager() {
	return manager;
}
public void setManager(Manager manager) {
	this.manager = manager;
}
 
}
