package edu.gsu.ds_register.model;

import edu.gsu.ds_register.utils.FirebaseUtils;

/**
 * Created by Joshua King on 10/31/16.
 */
public class RegistrationModel extends FirebaseModel {
	public static final String FIREBASE_NAME = "registrations";

	@Override public void saveToFirebase () {
		FirebaseUtils.saveToFirebase(FIREBASE_NAME, this);
	}

	// TODO: 10/31/16 : Implement this
}
