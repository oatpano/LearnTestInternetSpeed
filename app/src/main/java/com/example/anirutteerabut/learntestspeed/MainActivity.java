package com.example.anirutteerabut.learntestspeed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.internet_speed_testing.InternetSpeedBuilder;
import com.example.internet_speed_testing.ProgressionModel;

public class MainActivity extends AppCompatActivity {

    int countTestSpeed = 0;
    int LIMIT = 3;

    private RecyclerView recyclerView;
    private Adapter adapter;
    private SpeedModel speedModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);

        adapter = new Adapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        InternetSpeedBuilder builder = new InternetSpeedBuilder(this);
        builder.setOnEventInternetSpeedListener(new InternetSpeedBuilder.OnEventInternetSpeedListener() {
            @Override
            public void onDownloadProgress(int count, ProgressionModel progressModel) {

            }

            @Override
            public void onUploadProgress(int count, ProgressionModel progressModel) {

            }

            @Override
            public void onTotalProgress(int count, ProgressionModel progressModel) {
                adapter.setDataList(count, progressModel);

            }
        });
        builder.start("http://2.testdebit.info/fichiers/1Mo.dat", 2);

    }

}
