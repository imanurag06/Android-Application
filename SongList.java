package package_name;

import android.content.Intent;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SongList extends AppCompatActivity {
    ListView lv;
    List<String> songloc=new ArrayList<>();
    List song=new ArrayList();
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);
        lv=(ListView)findViewById(R.id.songlist);
        c=getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,null,null,null);
        while(c.moveToNext()){
            if(Integer.parseInt(c.getString(c.getColumnIndex(MediaStore.Audio.Media.DURATION)))>30000){
                song.add(c.getString(c.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME)));
                songloc.add(c.getString(c.getColumnIndex(MediaStore.Audio.Media.DATA)));

            }
        }
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,song);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(SongList.this,MediaPlayerDemo.class);
                intent.putExtra("song",songloc.get(position));
                startActivity(intent);

            }
        });
    }
}
