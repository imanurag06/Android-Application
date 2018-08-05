package package_name;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ContactList extends AppCompatActivity {
    ListView lv;
    Cursor c;
    List l=new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        lv=(ListView)findViewById(R.id.lv);
        c=getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,null,null,null,ContactsContract.Contacts.DISPLAY_NAME);
        while (c.moveToNext()){
            int i=c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            l.add(c.getString(i));
        }
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,l);
        lv.setAdapter(adapter);
    }
}
