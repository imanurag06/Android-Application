package package_name;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.TelephonyManager;
import android.widget.Toast;


public class incomingCalls extends BroadcastReceiver {
    NotificationManager nm;
    Notification.Builder nb;
    public void onReceive(Context context, Intent intent) {
        Bundle bundle=intent.getExtras();
        if(bundle!=null){
            String action=bundle.getString(TelephonyManager.EXTRA_STATE);
        if(action.equals(TelephonyManager.EXTRA_STATE_RINGING)){
            Toast.makeText(context, "You have Incoming Call"+bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER), Toast.LENGTH_SHORT).show();
          nm= (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
            nb=new Notification.Builder(context);
            String text="WELCOME ";
            nb.setContentTitle("YOU HAVE MESSAGE");
            nb.setContentText(text);
            Intent i=new Intent(context,NotificationResult.class);
            nb.setSmallIcon(android.R.drawable.sym_call_missed);
            nb.setDefaults(Notification.DEFAULT_ALL);
            nb.setAutoCancel(true);
            PendingIntent pi=PendingIntent.getActivity(context,0,i,PendingIntent.FLAG_UPDATE_CURRENT);
            nb.setContentIntent(pi);
            nm.notify(0,nb.build());

        }
        }
    }
}
