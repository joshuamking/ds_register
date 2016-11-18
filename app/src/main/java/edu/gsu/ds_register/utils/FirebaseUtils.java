package edu.gsu.ds_register.utils;

import com.google.firebase.database.*;
import edu.gsu.ds_register.model.FirebaseModel;

/**
 * Created by Joshua King on 10/30/16.
 */
public class FirebaseUtils {
	private static FirebaseDatabase database = FirebaseDatabase.getInstance();

	public static void addChildListener (String ref, ChildEventListener listener) {
		get(ref).addChildEventListener(listener);
	}

	public static void addValueListener (String ref, ValueEventListener listener) {
		get(ref).addValueEventListener(listener);
	}

	public static DatabaseReference get (String ref) {
		return database.getReference(ref);
	}

	public static void getSingleValue (String ref, ValueEventListener listener) {
		get(ref).addListenerForSingleValueEvent(listener);
	}

	public static <T extends FirebaseModel> void saveToFirebase (String s, final T t) {
		saveToFirebase(s, t, null);
	}

	public static <T extends FirebaseModel> void saveToFirebase (String s, final T t, final CallbackWithType<T> callback) {
		DatabaseReference announcementRef = get(s);
		DatabaseReference announcementRefWithId;
		if (t.getFirebaseKey() == null || t.getFirebaseKey().equals("")) {
			announcementRefWithId = announcementRef.push();
			t.setFirebaseKey(announcementRefWithId.getKey());
		}
		else { announcementRefWithId = announcementRef.child(t.getFirebaseKey()); }
		announcementRefWithId.setValue(t, new DatabaseReference.CompletionListener() {
			@Override public void onComplete (DatabaseError databaseError, DatabaseReference databaseReference) {
				if (callback != null) {
					if (databaseError == null) { callback.onComplete(t); }
					else { callback.onError(); }
				}
			}
		});
	}
}
