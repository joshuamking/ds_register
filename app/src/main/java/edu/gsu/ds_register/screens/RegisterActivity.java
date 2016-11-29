package edu.gsu.ds_register.screens;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import edu.gsu.ds_register.R;
import edu.gsu.ds_register.model.PersonModel;
import edu.gsu.ds_register.utils.StringUtils;
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

		final EditText dateOfBirthEditText = (EditText) findViewById(R.id.date_of_birth);
		final EditText emailEditText = (EditText) findViewById(R.id.person_email);
		final EditText phoneNumberEditText = (EditText) findViewById(R.id.phone_number);
		final EditText nameEditText = (EditText) findViewById(R.id.person_name);
		final EditText passwordEditText = (EditText) findViewById(R.id.person_password);

		dateOfBirthCalendar = Calendar.getInstance();
		dateOfBirthCalendar.add(Calendar.YEAR, -18);
		final DatePickerDialog dateOfBirthPickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
			@Override public void onDateSet (DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				dateOfBirthCalendar.set(year, monthOfYear, dayOfMonth);
				DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("MMMM d, YYYY");
				dateOfBirthEditText.setText(LocalDate.fromCalendarFields(dateOfBirthCalendar).toString(dateTimeFormatter));
			}
		}, dateOfBirthCalendar.get(Calendar.YEAR), dateOfBirthCalendar.get(Calendar.MONTH), dateOfBirthCalendar.get(Calendar.DAY_OF_MONTH));

		dateOfBirthEditText.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick (View v) {
				showDatePicker(dateOfBirthPickerDialog);
			}
		});
		dateOfBirthEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override public void onFocusChange (View v, boolean hasFocus) {
				if (hasFocus) {
					InputMethodManager imm = (InputMethodManager) dateOfBirthEditText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(dateOfBirthEditText.getWindowToken(), 0);
					showDatePicker(dateOfBirthPickerDialog);
				}
			}
		});
		dateOfBirthEditText.setRawInputType(InputType.TYPE_CLASS_TEXT);
		dateOfBirthEditText.setTextIsSelectable(true);

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick (final View view) {
				String dateOfBirthValue = String.valueOf(dateOfBirthEditText.getText());
				String emailValue = String.valueOf(emailEditText.getText());
				String phoneNumberValue = String.valueOf(phoneNumberEditText.getText());
				String nameValue = String.valueOf(nameEditText.getText());
				String passwordValue = String.valueOf(passwordEditText.getText());

				if (StringUtils.isEmptyOrNull(dateOfBirthValue) || StringUtils.isEmptyOrNull(emailValue) || StringUtils.isEmptyOrNull(phoneNumberValue) || StringUtils.isEmptyOrNull(nameValue) || StringUtils.isEmptyOrNull(passwordValue)) {
					Snackbar.make(view, "Please fill out all of the fields", Snackbar.LENGTH_LONG).show();
					return;
				}

				final PersonModel person = new PersonModel(nameValue, emailValue, passwordValue, phoneNumberValue, dateOfBirthCalendar.getTimeInMillis());
				FirebaseAuth.getInstance()
							.createUserWithEmailAndPassword(emailValue, passwordValue)
							.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
								@Override public void onComplete (@NonNull Task<AuthResult> task) {
									if (task.isSuccessful()) {
										person.saveToFirebase();
										finish();
									}
									else {
										Snackbar.make(view, "An error occurred while registering. If you have already registered, please sign in.", Snackbar.LENGTH_INDEFINITE)
												.setAction("Sign In", new View.OnClickListener() {
													@Override public void onClick (View v) {
														finish();
													}
												});
									}
								}
							});
			}
		});
	}

	private void showDatePicker (DatePickerDialog dateOfBirthPickerDialog) {
		dateOfBirthPickerDialog.updateDate(dateOfBirthCalendar.get(Calendar.YEAR), dateOfBirthCalendar.get(Calendar.MONTH), dateOfBirthCalendar.get(Calendar.DAY_OF_MONTH));
		dateOfBirthPickerDialog.show();
	}
}
