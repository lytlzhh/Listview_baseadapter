package com.example.llw.listview_baseadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.llw.listview_baseadapter.Myadapter;
import com.example.llw.listview_baseadapter.R;
import com.example.llw.listview_baseadapter.my_get_set;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview_id);

        List<my_get_set> list = new ArrayList<>();
        for (int i=0;i<40;i++)
        {
            list.add(new my_get_set(R.mipmap.ic_launcher,"标题","内容"));
        }
        listView.setAdapter(new Myadapter(list,this));

    }

}