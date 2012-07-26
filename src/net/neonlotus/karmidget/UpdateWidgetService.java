package net.neonlotus.karmidget;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

public class UpdateWidgetService extends Service {
	private static final String LOG = "RedKarm//UpdateService//";
	//UserObject uo = UserObject.getInstance();
	String user = KarmaActivity.uname;
	String comment = KarmaActivity.ckarma;
	String link = KarmaActivity.lkarma;

	@Override
	public void onStart(Intent intent, int startId) {
		Log.i(LOG, "Called");
		// Create some random data

		AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this
				.getApplicationContext());

		int[] allWidgetIds = intent
				.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);

		ComponentName thisWidget = new ComponentName(getApplicationContext(),
				Karmidget.class);
		int[] allWidgetIds2 = appWidgetManager.getAppWidgetIds(thisWidget);
		Log.w(LOG, "From Intent" + String.valueOf(allWidgetIds.length));
		Log.w(LOG, "Direct" + String.valueOf(allWidgetIds2.length));

		for (int widgetId : allWidgetIds) {
			// Create some random data
			//int number = (new Random().nextInt(100));

			RemoteViews remoteViews = new RemoteViews(this
					.getApplicationContext().getPackageName(),
					R.layout.widget);
			//Log.w("WidgetExample", String.valueOf(number));
			// Set the text
			//remoteViews.setTextViewText(R.id.update,"Random: " + String.valueOf(number));
			remoteViews.setTextViewText(R.id.rName,"User: " + user);
			remoteViews.setTextViewText(R.id.rLink,"Link: " + comment);
			remoteViews.setTextViewText(R.id.rComment,"Comment: " + link);

			// Register an onClickListener
			Intent clickIntent = new Intent(this.getApplicationContext(),
					Karmidget.class);

			clickIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
			clickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,
					allWidgetIds);

			PendingIntent pendingIntent = PendingIntent.getBroadcast(
					getApplicationContext(), 0, clickIntent,
					PendingIntent.FLAG_UPDATE_CURRENT);
			remoteViews.setOnClickPendingIntent(R.id.layout, pendingIntent);
			appWidgetManager.updateAppWidget(widgetId, remoteViews);
		}
		stopSelf();

		// super.onStart(intent, startId);
	}



	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}