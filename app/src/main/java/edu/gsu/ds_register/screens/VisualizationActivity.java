package edu.gsu.ds_register.screens;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import edu.gsu.ds_register.R;
import edu.gsu.ds_register.listener.SimpleListValueListener;
import edu.gsu.ds_register.model.PersonModel;
import edu.gsu.ds_register.utils.FirebaseUtils;

import java.util.ArrayList;
import java.util.Collections;

public class VisualizationActivity extends AppCompatActivity {
	@Override protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_visualization);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		ListView                     participantsListView = (ListView) findViewById(R.id.visualize_list_view);
		final ArrayList<PersonModel> persons              = new ArrayList<>();
		final ArrayAdapter<PersonModel> personModelArrayAdapter = new ArrayAdapter<PersonModel>(this, R.layout.person_info, persons) {
			@NonNull @Override public View getView (int position, View convertView, ViewGroup parent) {
				View view;
				if (convertView == null) {
					view = View.inflate(getContext(), R.layout.person_info, null);
				}
				else {
					view = convertView;
				}

				TextView nameView  = (TextView) view.findViewById(R.id.person_info_name_label);
				TextView emailView = (TextView) view.findViewById(R.id.person_info_email_label);
				TextView phoneView = (TextView) view.findViewById(R.id.person_info_phone_label);

				PersonModel person = persons.get(position);

				nameView.setText(person.getName());
				emailView.setText(person.getEmail());
				phoneView.setText(person.getPhoneNumber());

				return view;
			}
		};
		participantsListView.setAdapter(personModelArrayAdapter);

		FirebaseUtils.addValueListener("persons", new SimpleListValueListener<PersonModel>(PersonModel.class) {
			@Override public void onValueRetrieved (ArrayList<PersonModel> personModels) {
				persons.clear();
				Collections.sort(personModels);
				persons.addAll(personModels);
				personModelArrayAdapter.notifyDataSetChanged();
			}
		});
	}

	@Override public boolean onCreateOptionsMenu (Menu menu) {
		getMenuInflater().inflate(R.menu.menu_visualization, menu);
		return true;
	}

	@Override public boolean onOptionsItemSelected (MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_sign_out:
				FirebaseAuth.getInstance().signOut();
				finish();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}
