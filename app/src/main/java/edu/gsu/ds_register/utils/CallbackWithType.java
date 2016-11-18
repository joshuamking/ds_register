package edu.gsu.ds_register.utils;

/**
 * Created by Joshua King on 10/14/16.
 */
public abstract class CallbackWithType <T> {
	public abstract void onComplete (T t);

	public void onError () {}
}
