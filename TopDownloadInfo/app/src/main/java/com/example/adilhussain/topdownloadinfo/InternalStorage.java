package com.example.adilhussain.topdownloadinfo;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Adil Hussain on 7/31/2016.
 */
public class InternalStorage extends IntentService {

    private String mFileContent;
    public ArrayList<Application> applications;


    public InternalStorage() {
        super("");
        applications = new ArrayList<>();
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */


    public InternalStorage(String name) {
        super(name);
        if(applications == null){
            applications = new ArrayList<>();
        }
        applications = new ArrayList<>();
    }


    public ArrayList<Application> getApplications() {
        if(applications == null){
            applications = new ArrayList<>();
        }
        return applications;
    }

    @Override
    protected void onHandleIntent(Intent intent) {

//        android.os.Debug.waitForDebugger();
        String url = intent.getStringExtra("link");
        mFileContent = downloadXMLFile(url);
        process();

    }



    private String downloadXMLFile(String  urlPath){
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
            return temBuffer.toString();

        }catch (IOException e){
            Log.d("Download Data","IO Exception reading data"+e.getMessage());
            e.printStackTrace();
        }catch (SecurityException se){
            Log.d("Download Data","Internet not Available"+se.getMessage());
        }
        return  null;
    }



    //Read some specific data form all file.......
    public boolean process(){
        boolean status = true;
        Application currentRecord = new Application();
        boolean inEntry = false;
        String textValue = "";
        try {
            FileOutputStream fileOut = openFileOutput(appConstant.Top_10_Songs, Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(this.mFileContent));
            int eventType = xpp.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT){
                String tagName = xpp.getName();

                switch (eventType){
                    case XmlPullParser.START_TAG:
                        Log.d("Downloading","Starting Tag "+tagName);

                        if (tagName.equalsIgnoreCase("entry")){
                            inEntry = true;
                            currentRecord = null;
                            currentRecord = new Application();  // doing something here for with objects.........
                        }
                        break;

                    case  XmlPullParser.TEXT:
                        textValue = xpp.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (inEntry){
                            if (tagName.equalsIgnoreCase("entry")){
                                fileOut.write(currentRecord.getArtist().getBytes());
                                out.writeObject(currentRecord);
                                applications.add(currentRecord);
                                Log.d("Record Added","Record Name "+currentRecord.getName()+"Record Artist "+ currentRecord.getArtist()+"Record Release Date "+ currentRecord.getReleaseDate());
                                inEntry = false;
                            }else if (tagName.equalsIgnoreCase("name")){
                                currentRecord.setName(textValue);
                            }else if (tagName.equalsIgnoreCase("artist")){
                                currentRecord.setArtist(textValue);
                            }else if (tagName.equalsIgnoreCase("releaseDate")){
                                currentRecord.setReleaseDate(textValue);
                            }
                        }
                        Log.d("Downloading","Ending Tag "+tagName);
                        break;
                    default:
                }
                eventType = xpp.next();
            }
        }catch (Exception e){
            status = false;
            e.printStackTrace();
        }

        for (Application app: applications){
            Log.d("OutPut", "********************");
            Log.d("OutPut", "Name: "+ app.getName());
            Log.d("Output", "Artist "+ app.getArtist());
            Log.d("Output","Release Date "+ app.getReleaseDate());
        }

        Intent mIntent = new Intent();
        mIntent.setAction(MainActivity.DataBroadcaster.PROCESS_RESPONSE);
        Bundle b = new Bundle();
        b.putSerializable("mGottentFeedList",applications);
        mIntent.putExtras(b);
        sendBroadcast(mIntent);
        return true;
    }

}
