package edu.gsu.ds_register.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import edu.gsu.ds_register.R;
import edu.gsu.ds_register.listener.SimpleListValueListener;
import edu.gsu.ds_register.model.PersonModel;
import edu.gsu.ds_register.utils.FirebaseUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
	@Override protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final TextView signUpButton = (TextView) findViewById(R.id.signin_signup_button);
		signUpButton.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick (View v) {
				startActivity(new Intent(MainActivity.this, RegisterActivity.class));
			}
		});

		FirebaseUtils.addValueListener("persons", new SimpleListValueListener<PersonModel>(PersonModel.class) {
			@Override public void onValueRetrieved (ArrayList<PersonModel> personModels) {
				Snackbar.make(signUpButton, String.format("Person models received: %d", personModels.size()), Snackbar.LENGTH_INDEFINITE).show();
			}
		});
	}
}
