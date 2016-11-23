package edu.gsu.ds_register.listener;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Joshua King on 10/31/16.
 */
public abstract class SimpleValueEventListener <T> implements ValueEventListener {
	private Class<T> tClass;

	public abstract void onValueRetrieved (T t);

	public SimpleValueEventListener (Class<T> tClass) {
		this.tClass = tClass;
	}

	private static <T> T getSingleFirebaseObject (DataSnapshot dataSnapshot, Class<T> aClass) {
		return dataSnapshot.getValue(aClass);
	}

	@Override public void onDataChange (DataSnapshot dataSnapshot) {
		if (dataSnapshot.exists()) {onValueRetrieved(getSingleFirebaseObject(dataSnapshot, tClass));}
	}

	@Override public void onCancelled (DatabaseError databaseError) {

	}
}
