package test.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.* ;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="manager")
public class Manager implements Serializable {
@Id
@Column
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer id ; 
@Column(name="lastName")
private String lastName ; 
@Column(name="firstName")
private String firstName ; 
@Column(name="age")
private Integer age ; 

@JsonIgnore
@OneToOne(mappedBy="manager")
private Hotel hotel ; 
public Manager(String lastName, String firstName,Integer age) {
	super();
	this.lastName = lastName;
	this.firstName = firstName;
	this.age = age ; 
}
public Manager() {
	super();
	// TODO Auto-generated constructor stub
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public Hotel getHotel() {
	return hotel;
}
public void setHotel(Hotel hotel) {
	this.hotel = hotel;
}
public Integer getAge() {
	return age;
}
public void setAge(Integer age) {
	this.age = age;
}

	
}
