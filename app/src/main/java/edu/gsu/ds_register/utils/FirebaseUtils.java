package edu.gsu.ds_register.utils;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
}
