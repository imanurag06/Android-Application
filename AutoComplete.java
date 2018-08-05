package package_name;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;

public class AutoComplete extends AppCompatActivity implements View.OnClickListener {

    AutoCompleteTextView atv;
    MultiAutoCompleteTextView matv;
    Button b;


    String name[]={"S1","S2","S3","S4","S5"};
    String branch[]={"cse","civil","ee","ece","cse"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete);
        b=(Button)findViewById(R.id.button);
        atv=(AutoCompleteTextView)findViewById(R.id.auto);
        matv=(MultiAutoCompleteTextView)findViewById(R.id.multi);
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,name);
        atv.setAdapter(adapter);
        b.setOnClickListener(this);
        atv.setThreshold(1);
        matv.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }

    @Override
    public void onClick(View v) {
        String s;
        s=atv.getText().toString();
        for (int i=0;i<=4;i++){
            if(s.equalsIgnoreCase(name[i])){
                matv.setText(branch[i]);
            }
        }

    }
}
