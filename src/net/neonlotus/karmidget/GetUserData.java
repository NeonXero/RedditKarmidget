package net.neonlotus.karmidget;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by IntelliJ IDEA.
 * User: RyanThomas
 * Date: 12/8/11
 * Time: 5:40 PM
 * To change this template use File | Settings | File Templates.
 */

public class GetUserData extends AsyncTask<String, Integer, JSONObject> {

	private Context context;
	//private ProgressDialog dialog;


	public GetUserData(Context context){
		this.context = context;
	}
	/*@Override
	protected void onPreExecute() {
		dialog = new ProgressDialog(context);
		dialog.setMessage("Please wait...");
		dialog.setIndeterminate(true);
		dialog.show();
		super.onPreExecute();
	}*/

	@Override
	protected JSONObject doInBackground(String... params) {
		String input = params[0];
//some code for background work
		return null;
	}

	/*@Override
	protected void onPostExecute(JSONObject result) {
		if (dialog.isShowing()) {
			dialog.dismiss();
		}
		super.onPostExecute(result);
	}*/


	/*//test dialog thing
	private final ProgressDialog dialog = new ProgressDialog(context);
	//More dialog?
	protected void onPreExecute() {
		this.dialog.setMessage("Pulling reddit data");
		this.dialog.show();
	}

	protected JSONObject doInBackground(String...urls) {
		return null;//stuff... hmm

	}
	//dialog..
	protected void onPostExecute(final Boolean result) {
		if (this.dialog.isShowing()) {
			this.dialog.dismiss();
		}
		if (result.booleanValue()) {
			//also show register success dialog
		}
	}*/


	//HTTP Connection, from Praeda..
	public void connect (String url) {
		
		HttpClient httpclient = new DefaultHttpClient();

		//Prepare a request object
		HttpGet httpget = new HttpGet(url);

		//Execute the request
		HttpResponse response;
		try {
			response = httpclient.execute(httpget);
			//Examine the response status
			//-- Log.i("Praeda", response.getStatusLine().toString());
			if (response.getStatusLine().getStatusCode() == 200) {
				//Get a hold of the response entity
				HttpEntity entity = response.getEntity();
				// If the response does not enclose an entity, there is no need to worry about connection release
				if (entity != null) {
					//Simple JSON response read
					InputStream instream = entity.getContent();
					String result = convertStreamToString(instream);
					//-- Log.i("Praeda",result);
					
					//Simple JSONObject creation
					JSONObject json = new JSONObject(result);
					//--Log.i("Praeda","<jsonobject>\n"+json.toString()+"\n</jsonobject>");
					
					//Simple JSONObject parsing
					JSONObject data = json.optJSONObject("data");
					KarmaActivity.uname = data.optString("name");
					KarmaActivity.lkarma = data.optString("link_karma");
					KarmaActivity.ckarma = data.optString("comment_karma");
					
					//Closing input stream will trigger connection release
					instream.close();
				}
			} else {
				toastError();

			}
		} catch (ClientProtocolException e) {
			toastError();
			e.printStackTrace();
		} catch (IOException e) {
			toastError();
			e.printStackTrace();
		} catch (JSONException e) {
			toastError();
			e.printStackTrace();
		}
	}

	//Stream to string method NO NEED TO CHANGE THIS
	private static String convertStreamToString(InputStream is) {
		//		To convert the InputStream to String we use the BufferedReader.readLine()
		//		method. We iterate until the BufferedReader return null which means
		//		there's no more data to read. Each line will appended to a StringBuilder
		//		and returned as String.

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;

		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
			} finally {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		return sb.toString();
	} //End streamtostring

	public void toastError (){
	    Toast.makeText(context, "Connection has been lost", Toast.LENGTH_LONG).show();
	}
}

