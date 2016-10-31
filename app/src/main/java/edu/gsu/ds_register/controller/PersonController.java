package edu.gsu.ds_register.controller;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import edu.gsu.ds_register.model.PersonModel;
import edu.gsu.ds_register.utils.FirebaseUtils;
import edu.gsu.ds_register.utils.StringUtils;

/**
 * Created by Joshua King on 10/31/16.
 */
public class PersonController {
	public static void savePerson (PersonModel personModel) {
		savePerson(personModel, null);
	}

	public static void savePerson (PersonModel personModel, OnCompleteListener<Void> listener) {
		DatabaseReference usersRef = FirebaseUtils.get("people");

		if (StringUtils.isEmptyOrNull(personModel.getFirebaseKey())) {
			usersRef = usersRef.push();
			personModel.setFirebaseKey(usersRef.getKey());
		}
		else { usersRef = usersRef.child(personModel.getFirebaseKey()); }

		Task<Void> setValueTask = usersRef.setValue(personModel);

		if (listener != null) {
			setValueTask.addOnCompleteListener(listener);
		}
	}
}