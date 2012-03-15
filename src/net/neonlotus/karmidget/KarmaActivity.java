package net.neonlotus.karmidget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.loopj.android.http.AsyncHttpClient;


public class KarmaActivity extends Activity {

    TextView user, link, comment;
    Button userbutton, donebutton;
    EditText user_edit;

    static String ckarma;
    static String uname;
    static String lkarma;

    String HAHAHA;
	AsyncHttpClient client = new AsyncHttpClient();
	/* "How to use"
	AsyncHttpClient client = new AsyncHttpClient();
	client.get("http://www.google.com", new AsyncHttpResponseHandler() {
	    @Override
	    public void onSuccess(String response) {
	        System.out.println(response);
	    }
	});
	 */

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		final GetUserData gud = new GetUserData();
        user = (TextView) findViewById(R.id.user_text_view);
        link = (TextView) findViewById(R.id.link_text_view);
        comment = (TextView) findViewById(R.id.comment_text_view);

        user_edit = (EditText) findViewById(R.id.userEdit);

        userbutton = (Button) findViewById(R.id.userbutton);
		donebutton = (Button) findViewById(R.id.configdone);
        userbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                HAHAHA = user_edit.getText().toString();
                if (HAHAHA.length()>0) {
					gud.connect("http://www.reddit.com/user/" + HAHAHA + "/about.json");
					user.setText(uname);
					link.setText(lkarma);
					comment.setText(ckarma);
				} else {
					Toast.makeText(getApplicationContext(),"Please enter a user",Toast.LENGTH_SHORT).show();
				}

            }
        });
		
		donebutton.setOnClickListener(new View.OnClickListener() {
			public void onClick (View v) {
				Toast.makeText(getApplicationContext(),"DONE",Toast.LENGTH_SHORT).show();

			}
		});
    }

    //@Override
    public void onUpdate() {
        //    Widget broadcast thing????
//            Intent intent = new Intent(Karmidget.UNAME_TEXT_UPDATE);
//            intent.putExtra("NewString", user.getText().toString());
//            getApplicationContext().sendBroadcast(intent);
        final String ACTION_WIDGET_RECEIVER = "ActionReceiverWidget";
        Intent i = new Intent(getApplicationContext(), KarmaActivity.class);
        i.setAction(ACTION_WIDGET_RECEIVER);
        i.putExtra("name",uname);
        sendBroadcast(i);
    }




}
