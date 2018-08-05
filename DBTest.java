package package_name;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DBTest extends AppCompatActivity {
    SQLiteDatabase sd;
    Cursor c;
    EditText id;
    EditText name;
    EditText city;
    EditText branch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbtest);
        id=(EditText)findViewById(R.id.sid);
        name=(EditText)findViewById(R.id.sname);
        city=(EditText)findViewById(R.id.scity);
        branch=(EditText)findViewById(R.id.sbranch);
        sd=openOrCreateDatabase("mydatabase",MODE_PRIVATE,null);
        String query="create table if not exists student (sid int primary key,sname varchar not null,branch varchar not null,city varchar not null)";
        sd.execSQL(query);
        c=sd.rawQuery("select * from student",null);
        Toast.makeText(this,"DATABASE CREATED !!!",Toast.LENGTH_SHORT);


    }
    public void addData(View v){
        int sid=Integer.parseInt(id.getText().toString().trim());
        String sname=name.getText().toString().trim();
        String sbranch=branch.getText().toString().trim();
        String scity=city.getText().toString().trim();
        String query="insert into student values("+sid+",'"+sname+"','"+sbranch+"','"+scity+"')";
        sd.execSQL(query);
        c=sd.rawQuery("select * from student",null);
        Toast.makeText(this, "DATA INSERTED ...!!!", Toast.LENGTH_SHORT).show();
        id.setText(null);
        name.setText(null);
        branch.setText(null);
        city.setText(null);
    }
    public void previousData(View v){
        if(c.moveToPrevious()){
            id.setText(" "+c.getInt(0));
            name.setText(c.getString(1));
            branch.setText(c.getString(2));
            city.setText(c.getString(3));
        }

    }
    public void deleteData(View v){
        int sid=Integer.parseInt(id.getText().toString().trim());
        String query="delete from Student where sid="+sid;
        sd.execSQL(query);
        c=sd.rawQuery("select * from student",null);
        Toast.makeText(this, "DATA DELETED ...!!!", Toast.LENGTH_SHORT).show();
        id.setText(null);
        name.setText(null);
        branch.setText(null);
        city.setText(null);

    }
    public void nextData(View v){
        if(c.moveToNext()){
            id.setText(" "+c.getInt(0));
            name.setText(c.getString(1));
            branch.setText(c.getString(2));
            city.setText(c.getString(3));
            Toast.makeText(this, " "+c.getPosition(), Toast.LENGTH_SHORT).show();
        }

    }
}
