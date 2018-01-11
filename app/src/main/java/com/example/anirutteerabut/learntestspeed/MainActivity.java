package com.example.anirutteerabut.learntestspeed;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import fr.bmartel.speedtest.SpeedTestReport;
import fr.bmartel.speedtest.SpeedTestSocket;
import fr.bmartel.speedtest.inter.ISpeedTestListener;
import fr.bmartel.speedtest.model.SpeedTestError;

public class MainActivity extends AppCompatActivity {

    int countDownload = 0;
    int countUpload = 0;
    int LIMIT = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        startTestDownload();
        startTestUpload();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Adapter());

    }

    private void startTestDownload() {
        new SpeedDownloadTestTask().execute();
    }

    private void startTestUpload() {
        new SpeedUploadTestTask().execute();

    }

    public class SpeedDownloadTestTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {

            SpeedTestSocket speedTestSocket = new SpeedTestSocket();

            // add a listener to wait for speedtest completion and progress
            speedTestSocket.addSpeedTestListener(new ISpeedTestListener() {

                @Override
                public void onCompletion(SpeedTestReport report) {
                    // called when download/upload is finished
                    Log.v("speedtest Download" + countDownload, "[COMPLETED] rate in octet/s : " + report.getTransferRateOctet());
                    Log.v("speedtest Download" + countDownload, "[COMPLETED] rate in bit/s   : " + report.getTransferRateBit());

                    if (countDownload < LIMIT) {
                        startTestDownload();
                        countDownload++;
                    }

                }

                @Override
                public void onError(SpeedTestError speedTestError, String errorMessage) {
                    // called when a download/upload error occur
                }

                @Override
                public void onProgress(float percent, SpeedTestReport report) {
                    // called to notify download/upload progress
                    Log.v("speedtest Download" + countDownload, "[PROGRESS] progress : " + percent + "%");
                    Log.v("speedtest Download" + countDownload, "[PROGRESS] rate in octet/s : " + report.getTransferRateOctet());
                    Log.v("speedtest Download" + countDownload, "[PROGRESS] rate in bit/s   : " + report.getTransferRateBit());
                }
            });

            speedTestSocket.startDownload("http://2.testdebit.info/fichiers/1Mo.dat");

            return null;
        }
    }

    public class SpeedUploadTestTask extends AsyncTask<Integer, Void, String> {

        @Override
        protected String doInBackground(Integer... params) {

            SpeedTestSocket speedTestSocket = new SpeedTestSocket();

            // add a listener to wait for speedtest completion and progress
            speedTestSocket.addSpeedTestListener(new ISpeedTestListener() {

                @Override
                public void onCompletion(SpeedTestReport report) {
                    // called when download/upload is finished
                    Log.v("speedtest Upload" + countUpload, "[COMPLETED] rate in octet/s : " + report.getTransferRateOctet());
                    Log.v("speedtest Upload" + countUpload, "[COMPLETED] rate in bit/s   : " + report.getTransferRateBit());

                    if (countUpload < LIMIT) {
                        startTestUpload();
                        countUpload++;
                    }
                }

                @Override
                public void onError(SpeedTestError speedTestError, String errorMessage) {
                    // called when a download/upload error occur
                }

                @Override
                public void onProgress(float percent, SpeedTestReport report) {
                    // called to notify download/upload progress
                    Log.v("speedtest Upload" + countUpload, "[PROGRESS] progress : " + percent + "%");
                    Log.v("speedtest Upload" + countUpload, "[PROGRESS] rate in octet/s : " + report.getTransferRateOctet());
                    Log.v("speedtest Upload" + countUpload, "[PROGRESS] rate in bit/s   : " + report.getTransferRateBit());
                }
            });

            speedTestSocket.startDownload("http://2.testdebit.info/fichiers/1Mo.dat");

            return null;
        }
    }
}
