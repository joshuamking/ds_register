package edu.gsu.ds_register.screens;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.TextView;
import edu.gsu.ds_register.R;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {
	private Calendar dateOfBirthCalendar;

	@Override protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		final TextView dateOfBirthTextView = (TextView) findViewById(R.id.date_of_birth);
		dateOfBirthCalendar = Calendar.getInstance();
		dateOfBirthCalendar.add(Calendar.YEAR, -18);
		final DatePickerDialog dateOfBirthPickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
			@Override public void onDateSet (DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				dateOfBirthCalendar.set(year, monthOfYear, dayOfMonth);
				DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("MMMM d, YYYY");
				dateOfBirthTextView.setText(LocalDate.fromCalendarFields(dateOfBirthCalendar).toString(dateTimeFormatter));
			}
		}, dateOfBirthCalendar.get(Calendar.YEAR), dateOfBirthCalendar.get(Calendar.MONTH), dateOfBirthCalendar.get(Calendar.DAY_OF_MONTH));

		dateOfBirthTextView.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick (View v) {
				showDatePicker(dateOfBirthPickerDialog);
			}
		});
		dateOfBirthTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override public void onFocusChange (View v, boolean hasFocus) {
				if (hasFocus) {
					InputMethodManager imm = (InputMethodManager) dateOfBirthTextView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(dateOfBirthTextView.getWindowToken(), 0);
					showDatePicker(dateOfBirthPickerDialog);
				}
			}
		});
		dateOfBirthTextView.setRawInputType(InputType.TYPE_CLASS_TEXT);
		dateOfBirthTextView.setTextIsSelectable(true);

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick (View view) {
				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
			}
		});
	}

	private void showDatePicker (DatePickerDialog dateOfBirthPickerDialog) {
		dateOfBirthPickerDialog.updateDate(dateOfBirthCalendar.get(Calendar.YEAR), dateOfBirthCalendar.get(Calendar.MONTH), dateOfBirthCalendar.get(Calendar.DAY_OF_MONTH));
		dateOfBirthPickerDialog.show();
	}
}
