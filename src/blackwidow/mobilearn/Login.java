package blackwidow.mobilearn;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import blackwidow.library.DbHandler;
import blackwidow.library.Ip;
import blackwidow.library.JSONParser;

public class Login extends ActionBarActivity {
	ProgressDialog pDialog;
	Intent intent;

	JSONParser jParser;
	Ip ip;
	DbHandler db;

	String un, pw;
	TextView error;
	Button login, create;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		error = (TextView) findViewById (R.id.error_txt);
		login = (Button) findViewById (R.id.login_btn);
		login.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				checkField();
			}
		});
		create = (Button) findViewById (R.id.create_acc_btn);
		create.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				intent = new Intent(Login.this, Register.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
	}

	public void checkField(){
		un = ((EditText) findViewById (R.id.username_et_al)).getText().toString().trim();
		pw = ((EditText) findViewById (R.id.password_et_al)).getText().toString().trim();
		if (un.equals("") && pw.equals("")){
			error.setTextColor(Color.RED);
			error.setText("Please Provide Details");
		}else if (un.equals("") || pw.equals("")){
			error.setTextColor(Color.RED);
			error.setText("Username/Password Wrong ... ");
		}else{
			new login().execute();
		}
	}

	class login extends AsyncTask<String, String, String>{

		public void onPreExecute(){
			pDialog = new ProgressDialog(Login.this);
			pDialog.setMessage("Please Wait, Logging In ... ");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			jParser = new JSONParser();
			ip = new Ip();

			un = ((EditText) findViewById (R.id.username_et_al)).getText().toString().trim();
			pw = ((EditText) findViewById (R.id.password_et_al)).getText().toString().trim();

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("tag", "login"));
			params.add(new BasicNameValuePair("username", un));
			params.add(new BasicNameValuePair("password", pw));
			try{
				JSONObject obj = jParser.makeHttpRequest(ip.getIp(), "POST", params);
				Log.d("Details", obj.toString());
				try{
					int success = obj.getInt("success");
					if (success == 1){
						intent = new Intent(Login.this, MainActivity.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(intent);

						Login.this.finish();
					}else{
						error.setTextColor(Color.RED);
						error.setText(" Failed to Login, Please try again ... ");
					}
				}catch(JSONException e){
					e.printStackTrace();
				}

			}catch(Exception e){
				e.printStackTrace();
			}
			return null;
		}

		public void onPostExecute(String file_url){
			pDialog.dismiss();
		}

	}

}
