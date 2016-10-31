package edu.gsu.ds_register.model;

import com.google.firebase.database.Exclude;

/**
 * Created by Joshua King on 10/31/16.
 */
public class FirebaseModel {
	private String firebaseKey;

	@Exclude
	public String getFirebaseKey () {
		return firebaseKey;
	}

	public void setFirebaseKey (String firebaseKey) {
		this.firebaseKey = firebaseKey;
	}
}
