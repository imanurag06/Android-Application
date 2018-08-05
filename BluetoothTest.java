package package_name;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BluetoothTest extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    BluetoothAdapter ba;
    Button b;
    ListView lv;
    Switch s;
    Set<BluetoothDevice> s1;
    List<String>l=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_test);
        s=(Switch)findViewById(R.id.switch1);
        lv=(ListView)findViewById(R.id.lv);
        b=(Button)findViewById(R.id.b);
        ba=BluetoothAdapter.getDefaultAdapter();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ba.isEnabled()){
                    s1=ba.getBondedDevices();
                    for (BluetoothDevice b1:s1){
                        l.add(b1.getName()+"||"+b1.getAddress());
                    }
                    ArrayAdapter ad=new ArrayAdapter(BluetoothTest.this,android.R.layout.simple_expandable_list_item_1,l);
                    lv.setAdapter(ad);
                }
            }
        });
        if (ba==null){
            Toast.makeText(this, "OLD PHONE", Toast.LENGTH_SHORT).show();
        }
        else{
            s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        if (!ba.isEnabled()){
                            Intent i=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                            startActivity(i);
                            Toast.makeText(BluetoothTest.this, "Bluetooth Turning on..", Toast.LENGTH_SHORT).show();

                        }
                        else if (ba.isEnabled()){
                            ba.disable();
                        }
                    }
                }
            });

        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }
}
