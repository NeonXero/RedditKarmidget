package net.neonlotus.karmidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

public class Karmidget extends AppWidgetProvider
{
    private Context context;
    //RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		//called when widgets are deleted
		//see that you get an array of widgetIds which are deleted
		//so handle the delete of multiple widgets in an iteration
		super.onDeleted(context, appWidgetIds);
	}
	@Override
	public void onDisabled(Context context) {
		super.onDisabled(context);
		//runs when all of the instances of the widget are deleted from
		//the home screen
		//here you can do some setup
	}
	@Override
	public void onEnabled(Context context) {
		super.onEnabled(context);
		//runs when all of the first instance of the widget are placed
		//on the home screen
	}
	@Override
	public void onReceive(Context context, Intent intent) {
		//all the intents get handled by this method
		//mainly used to handle self created intents, which are not
		//handled by any other method


		//the super call delegates the action to the other methods

		//for example the APPWIDGET_UPDATE intent arrives here first
		//and the super call executes the onUpdate in this case
		//so it is even possible to handle the functionality of the
		//other methods here
		//or if you don't call super you can overwrite the standard
		//flow of intent handling
		super.onReceive(context, intent);
	}
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
						 int[] appWidgetIds) {
		//runs on APPWIDGET_UPDATE
		//here is the widget content set, and updated
		//it is called once when the widget created
		//and periodically as set in the metadata xml

		//the layout modifications can be done using the AppWidgetManager
		//passed in the parameter, we will discuss it later

		//the appWidgetIds contains the Ids of all the widget instances
		//so here you want likely update all of them in an iteration

		//we will use only the first creation run
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}

}

//private static final String ACTION_WIDGET_RECEIVER ="";

/** Called when the activity is first created. */
//    @Override
//    public void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
//    }

//public static final String UNAME_TEXT_UPDATE = "net.neonlotus.karmidget.NAME_CHANGED";
//
//    @Override
//    public void onReceive(Context context, Intent intent)
//    {
//        RemoteViews remoteViews = new RemoteViews(context.getPackageName (),R.layout.widget);
//
//        Bundle extras = intent.getExtras();
//        if (intent.getAction().equals(ACTION_WIDGET_RECEIVER)) {
//            //int day = extras.getInt("days");
//            String username = extras.getString("name");
//            remoteViews.setTextViewText(R.id.user_text_view, ""+username);
//        }
//        else {
//            super.onReceive(context, intent);
//        }
//        ComponentName cn = new ComponentName(context, Karmidget.class);
//        AppWidgetManager.getInstance(context).updateAppWidget(cn, remoteViews);
//    }
//}