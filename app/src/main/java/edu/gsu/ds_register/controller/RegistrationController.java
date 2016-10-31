package edu.gsu.ds_register.controller;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import edu.gsu.ds_register.model.RegistrationModel;
import edu.gsu.ds_register.utils.FirebaseUtils;
import edu.gsu.ds_register.utils.StringUtils;

/**
 * Created by Joshua King on 10/31/16.
 */
public class RegistrationController {
	public static void saveRegistration (RegistrationModel registrationModel) {
		saveRegistration(registrationModel, null);
	}

	public static void saveRegistration (RegistrationModel registration, OnCompleteListener<Void> listener) {
		DatabaseReference usersRef = FirebaseUtils.get("registrations");

		if (StringUtils.isEmptyOrNull(registration.getFirebaseKey())) {
			usersRef = usersRef.push();
			registration.setFirebaseKey(usersRef.getKey());
		}
		else { usersRef = usersRef.child(registration.getFirebaseKey()); }

		Task<Void> setValueTask = usersRef.setValue(registration);

		if (listener != null) {
			setValueTask.addOnCompleteListener(listener);
		}
	}
}