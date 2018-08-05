package package_name;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FirstActivity extends AppCompatActivity {
    SharedPreferences sp;
    TextView tv;
    RelativeLayout rl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        sp= PreferenceManager.getDefaultSharedPreferences(this);
        tv=(TextView)findViewById(R.id.textView);
        String c=sp.getString("bg",null);
        String n=sp.getString("user","mobile");
        tv.setText(c+"\n"+n);
        rl=(RelativeLayout)findViewById(R.id.activity_first);
    }

    @Override
    protected void onStart() {
        super.onStart();
        switch (sp.getString("bg",null)){
            case "YELLOW":
                rl.setBackgroundColor(Color.YELLOW);
                break;
            case "RED":
                rl.setBackgroundColor(Color.RED);
                break;
            case "GRAY":
                rl.setBackgroundColor(Color.GRAY);
                break;
            case "GREEN":
                rl.setBackgroundColor(Color.GREEN);
                break;
            case "BLUE":
                rl.setBackgroundColor(Color.BLUE);
                break;
             default :
                rl.setBackgroundColor(Color.WHITE);
                break;
        }
    }
}
