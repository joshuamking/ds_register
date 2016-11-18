package edu.gsu.ds_register.model;

import edu.gsu.ds_register.utils.FirebaseUtils;

/**
 * Created by Joshua King on 10/31/16.
 */
public class PersonModel extends FirebaseModel {
	public static final String FIREBASE_NAME = "persons";
	private Long   dob;
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private String phoneNumber;

	public PersonModel (String firstName, String lastName, String email, String password, String phoneNumber, Long dob) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.dob = dob;
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

	public String getFirstName () {
		return firstName;
	}

	public String getLastName () {
		return lastName;
	}

	public String getPassword () {
		return password;
	}

	public String getPhoneNumber () {
		return phoneNumber;
	}

	public void setFirstName (String firstName) {
		this.firstName = firstName;
	}
}
