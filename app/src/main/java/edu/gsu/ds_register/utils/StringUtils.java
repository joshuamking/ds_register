package edu.gsu.ds_register.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Joshua King on 10/31/16.
 */
public class StringUtils {
	public static boolean isEmptyOrNull (@Nullable String s) {
		return isNull(s) || isEmptyString(s);
	}

	public static boolean isEmptyString (@NonNull String s) {
		return s.equals("");
	}

	public static boolean isNotEmptyAndNotNull (@Nullable String s) {
		return !isEmptyOrNull(s);
	}

	public static boolean isNull (@Nullable String s) {
		return s == null;
	}
}
