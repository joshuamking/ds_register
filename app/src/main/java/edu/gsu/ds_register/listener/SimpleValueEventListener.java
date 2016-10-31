package edu.gsu.ds_register.listener;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.ParameterizedType;

/**
 * Created by Joshua King on 10/31/16.
 */
public abstract class SimpleValueEventListener <T> implements ValueEventListener {
	public abstract void onValueRetrieved (T t);

	@Override
	public void onDataChange (DataSnapshot dataSnapshot) {
		@SuppressWarnings ("unchecked")
		Class<T> tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		if (dataSnapshot.exists()) { onValueRetrieved(dataSnapshot.getValue(tClass)); }
	}

	@Override
	public void onCancelled (DatabaseError databaseError) {

	}
}
