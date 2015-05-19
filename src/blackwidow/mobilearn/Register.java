package blackwidow.mobilearn;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import blackwidow.library.DbHandler;
import blackwidow.library.Ip;
import blackwidow.library.JSONParser;

public class Register extends ActionBarActivity {
	ProgressDialog pDialog;

	JSONParser jParser;
	Ip ip;
	DbHandler db;
	String sn, ln, course, un, pw, cpw;

	TextView error;
	Button create_account;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		error = (TextView) findViewById (R.id.error_create_txt);
		create_account = (Button) findViewById (R.id.register_account_button);
		create_account.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				checkFields();
			}
		});

	}

	class getCourse extends AsyncTask<String, String, String>{

		public void onPreExecute(){
			super.onPreExecute();
			pDialog = new ProgressDialog(Register.this);
			pDialog.setMessage("Please Wait, Creating Account ... ");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		public void onPostExecute(String file_url){
			pDialog.dismiss();
		}

	}

	public void checkFields(){
		sn = ((EditText) findViewById (R.id.surname_create_et_al)).getText().toString().trim();
		ln = ((EditText) findViewById (R.id.lastname_create_et_al)).getText().toString().trim();
//		course = ((Spinner) findViewById (R.id.course_create_sp)).getSelectedItem().toString().trim();
		un = ((EditText) findViewById (R.id.username_create_et_al)).getText().toString().trim();
		pw = ((EditText) findViewById (R.id.password_create_et_al)).getText().toString().trim();
		cpw = ((EditText) findViewById (R.id.confirm_password_create_et_al)).getText().toString().trim();

		if (sn.equals("") && ln.equals("") && un.equals("") && pw.equals("") && cpw.equals("")){
			error.setTextColor(Color.RED);
			error.setText("Please Provide Details ... ");
		}else if (sn.equals("") || ln.equals("") || un.equals("") || pw.equals("") || cpw.equals("")){
			error.setTextColor(Color.RED);
			error.setText("Please Enter All Details ... ");
		}else{
//			new register().execute();
		}
	}

	class register extends AsyncTask<String, String, String>{

		public void onPreExecute(){
			super.onPreExecute();
			pDialog = new ProgressDialog(Register.this);
			pDialog.setMessage("Please Wait, Creating Account ... ");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			return null;
		}

		public void onPostExecute(String file_url){
			pDialog.dismiss();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
