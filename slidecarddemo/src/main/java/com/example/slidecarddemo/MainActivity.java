package com.example.slidecarddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    MyRecyclerView recyclerView;
    ArrayList<String> datas = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initDatas();

        recyclerView = (MyRecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setAdapter(new MyAdapter(datas,this));
        recyclerView.setLayoutManager(new MyLayoutManager());
    }

    private void initDatas() {
        for(char i='A';i<'Z';i++){
            String j = String.valueOf(i);
            Log.d("TAG", "initDatas: "+j);
            datas.add(j);
        }
    }
}
