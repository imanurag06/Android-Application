package package_name;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class BatteryState extends AppCompatActivity {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery_state);
        tv=(TextView)findViewById(R.id.textView2);
        BroadcastReceiver b=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
             int b=intent.getExtras().getInt(BatteryManager.EXTRA_LEVEL);
                tv.setText(""+b);
            }

        };
        IntentFilter ifr =new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(b,ifr);
    }
}
