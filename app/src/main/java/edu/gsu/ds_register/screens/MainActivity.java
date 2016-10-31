package edu.gsu.ds_register.screens;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import edu.gsu.ds_register.R;
import edu.gsu.ds_register.listener.SimpleValueEventListener;
import edu.gsu.ds_register.utils.FirebaseUtils;

public class MainActivity extends AppCompatActivity {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final TextView viewById = (TextView) findViewById(R.id.blah);
		FirebaseUtils.addValueListener("test", new SimpleValueEventListener<String>() {
			@Override
			public void onValueRetrieved (String value) {
				viewById.setText(value);
			}
		});
	}
}
