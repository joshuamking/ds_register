package edu.gsu.ds_register.screens;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import edu.gsu.ds_register.R;
import edu.gsu.ds_register.listener.SimpleValueEventListener;
import edu.gsu.ds_register.model.PersonModel;
import edu.gsu.ds_register.utils.FirebaseUtils;

public class MainActivity extends AppCompatActivity {
	@Override protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final TextView textView = (TextView) findViewById(R.id.blah);
		FirebaseUtils.addValueListener("test", new SimpleValueEventListener<String>() {
			@Override public void onValueRetrieved (String value) {
				textView.setText(value);
			}
		});
		final PersonModel personModel = new PersonModel("Homeless", "Guy", "noemailduh@me.com", "why...?", "9876543210", System.currentTimeMillis());
		personModel.saveToFirebase();


		/*
		Am I on the right track?

		final EditText getFirstName = (EditText) findViewById(R.id.getFirstName);
        final EditText getLastName = (EditText) findViewById(R.id.getLastName);
        final EditText getEmail = (EditText) findViewById(R.id.getEmail);
        final EditText getPassword = (EditText) findViewById(R.id.getPassword);
        final EditText getPhoneNumber = (Button) findViewById(R.id.getPhoneNumber);
        final EditText getPhoneNumber = (Button) findViewById(R.id.getPhoneNumber);
        final EditText getDob = (Button) findViewById(R.id.getDob);

        //if it's a button then  final Button getButton = (Button) findViewById(R.id.getButton);

		then the button would have it's own button.setOnClickListener()
		 */








		// TODO: 11/18/16 : THIS CALL WON"T WORK YET... I WILL FIX SOON.
		//		FirebaseUtils.addValueListener("persons", new SimpleValueEventListener<ArrayList<PersonModel>>() {
		//			@Override public void onValueRetrieved (ArrayList<PersonModel> personModels) {
		//				Snackbar.make(textView, String.format("Person models received: %d", personModels.size()), Snackbar.LENGTH_INDEFINITE).show();
		//			}
		//		});
	}
}
