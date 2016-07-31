package com.example.adilhussain.topdownloadinfo;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Adil Hussain on 7/31/2016.
 */
public class InternalStorage extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public InternalStorage(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String  urlPath = intent.getDataString();
        StringBuilder temBuffer = new StringBuilder();
        try{
            URL url = new URL(urlPath);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            int response = connection.getResponseCode();
            Log.d("Download Data", "Response code was "+response);
            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            int charRead;
            char [] inputBuffer = new char[500];

            while (true){
                charRead = isr.read(inputBuffer);
                if (charRead <= 0){
                    break;
                }
                temBuffer.append(String.copyValueOf(inputBuffer, 0, charRead));

            }

        }catch (IOException e){
            Log.d("Download Data","IO Exception reading data"+e.getMessage());
            e.printStackTrace();
        }catch (SecurityException se){
            Log.d("Download Data","Internet not Available"+se.getMessage());
        }
    }

}
