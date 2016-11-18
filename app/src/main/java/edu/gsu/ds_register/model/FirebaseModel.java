package edu.gsu.ds_register.model;

import com.google.firebase.database.Exclude;

/**
 * Created by Joshua King on 10/31/16.
 */
public abstract class FirebaseModel {
	private String firebaseKey;

	public abstract void saveToFirebase ();

	@Exclude public String getFirebaseKey () {
		return firebaseKey;
	}

	public void setFirebaseKey (String firebaseKey) {
		this.firebaseKey = firebaseKey;
	}
}
