package com.example.util;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name = "userdetails")

public class Users {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;

@Column(name = "firstName" ,nullable = false,length = 20)
@Size(min = 1, max = 20 ,message="not more than 20 char")
@NotNull(message="please insert fName")
@NotEmpty(message="please insert fName")

private String firstName;

@Column(name = "lastName" ,nullable = false ,length = 20)
@Size(min = 1, max = 20 ,message="not more than 20 char")
@NotNull(message="please insert lName")
@NotEmpty(message="please insert lName")
private String lastName;

@Column(name = "email" ,nullable = false)
@NotNull(message="please insert email")
@NotEmpty(message="please insert email")
@Email(message="Please provide a valid email address")
@Pattern(regexp=".+@.+\\..+", message="Please provide a valid email address")
private String email;

@Column(name = "pinCode" ,nullable = false,length = 6)
@NotNull(message="please insert pinCode ")
@NotEmpty(message="please insert pincode")
@Size(min = 6, max = 6 ,message="max and min 6 digit")
/*@Range(min = 6, max = 6 ,message="only  number")*/
private String pinCode;

@Column(name = "birthDate" ,nullable = false)
@NotNull(message="please insert birthDate")
@NotEmpty(message="please insert birthDate")
@DateTimeFormat(pattern="dd-mmm-yyy")

/*@Temporal(TemporalType.DATE)
@PastOrPresent(message="date is future date")*/
private String birthDate;

@Column(name ="status")
private int status;

@Column(name ="month")
private String month;

public String getMonth() {
	return month;
}

public void setMonth(String month) {
	this.month = month;
}

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPinCode() {
	return pinCode;
}

public void setPinCode(String pinCode) {
	this.pinCode = pinCode;
}

public String  getBirthDate() {
	return birthDate;
}

public void setBirthDate(String birthDate) {
	this.birthDate = birthDate;
}

public int getStatus() {
	return status;
}

public void setStatus(int status) {
	this.status = status;
}

}
