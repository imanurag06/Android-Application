package package_name;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {

    String abc[]={"MainActivity","BluetoothTest","BatteryState","SongList","ContactList","MediaPlayerDemo","LifeCycle","BroadCastTest","ProximityTest","AutoComplete","PreferenceFromXml","FirstActivity","DBTest","Exit"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter arrayAdapter= new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,abc);
        setListAdapter(arrayAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if (abc[position].equalsIgnoreCase("exit")){
            finish();
        }

        try{
            String s = "package_name."+abc[position];
            Class c = Class.forName(s);
            Intent intent = new Intent(this,c);
            startActivity(intent);
        }
        catch (Exception e){

        }
    }
}
