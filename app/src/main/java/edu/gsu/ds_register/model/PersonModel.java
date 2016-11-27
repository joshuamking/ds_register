package edu.gsu.ds_register.model;

import edu.gsu.ds_register.utils.FirebaseUtils;

/**
 * Created by Joshua King on 10/31/16.
 */
public class PersonModel extends FirebaseModel {
	public static final String FIREBASE_NAME = "persons";
	private Long   dob;
	private String email;
	private String name;
	private String password;
	private String phoneNumber;

	public PersonModel (String name, String email, String password, String phoneNumber, Long dob) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.dob = dob;
	}

	public PersonModel () {
	}

	@Override public void saveToFirebase () {
		FirebaseUtils.saveToFirebase(FIREBASE_NAME, this);
	}

	public Long getDob () {
		return dob;
	}

	public String getEmail () {
		return email;
	}

	public String getName() {
		return name;
	}

	public void setDob(Long dob) {
		this.dob = dob;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword () {
		return password;
	}

	public String getPhoneNumber () {
		return phoneNumber;
	}

	public void setName(String name) {
		this.name = name;
	}
}
