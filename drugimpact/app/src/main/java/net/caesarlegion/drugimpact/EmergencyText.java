package net.caesarlegion.drugimpact;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import net.caesarlegion.drugimpact.Model.DrugSafetyData;

import org.w3c.dom.Text;

/**
 * Implementation of App Widget functionality.
 */
public class EmergencyText extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.emergency_text);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
            Intent intent = SendText(context, DrugSafetyData.EMERGENCY_NUMBER, DrugSafetyData.EMERGENCY_MESSAGE);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.emergency_text);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            views.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    //This function will send an emergency text when called
    public static Intent SendText(Context context, String number, String message){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"+number));
        intent.putExtra("sms_body", message);
        return intent;
    }
}

