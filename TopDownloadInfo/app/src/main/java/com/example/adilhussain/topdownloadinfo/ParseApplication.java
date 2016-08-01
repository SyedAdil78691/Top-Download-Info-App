package com.example.adilhussain.topdownloadinfo;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by Adil Hussain on 7/5/2016.
 */
public class ParseApplication {
    private String XMLData;
    public ArrayList<Application> applications;


    public ParseApplication(String XMLData) {
        this.XMLData = XMLData;
        if(applications == null){
            applications = new ArrayList<>();
        }
    }

    public ArrayList<Application> getApplications() {
        if(applications == null){
            applications = new ArrayList<>();
        }
        return applications;
    }


//    public boolean process(){
//        boolean status = true;
//        Application currentRecord = new Application();
//        boolean inEntry = false;
//        String textValue = "";
//
//        try {
//            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
//            factory.setNamespaceAware(true);
//            XmlPullParser xpp = factory.newPullParser();
//            xpp.setInput(new StringReader(this.XMLData));
//            int eventType = xpp.getEventType();
//
//            while (eventType != XmlPullParser.END_DOCUMENT){
//                String tagName = xpp.getName();
//                switch (eventType){
//                    case XmlPullParser.START_TAG:
//                        Log.d("Downloading","Starting Tag "+tagName);
//
//                        if (tagName.equalsIgnoreCase("entry")){
//                            inEntry = true;
//                            currentRecord = null;
//                            currentRecord = new Application();
//                        }
//                        break;
//
//                    case  XmlPullParser.TEXT:
//                        textValue = xpp.getText();
//                        break;
//
//                    case XmlPullParser.END_TAG:
//                        if (inEntry){
//                            if (tagName.equalsIgnoreCase("entry")){
//                                applications.add(currentRecord);
//                                Log.d("Record Added","Record Name "+currentRecord.getName()+"Record Artist "+ currentRecord.getArtist()+"Record Release Date "+ currentRecord.getReleaseDate());
//                                inEntry = false;
//                            }else if (tagName.equalsIgnoreCase("name")){
//                                currentRecord.setName(textValue);
//                            }else if (tagName.equalsIgnoreCase("artist")){
//                                currentRecord.setArtist(textValue);
//                            }else if (tagName.equalsIgnoreCase("releaseDate")){
//                                currentRecord.setReleaseDate(textValue);
//                            }
//
//                        }
//                        Log.d("Downloading","Ending Tag "+tagName);
//                        break;
//                    default:
//                }
//                eventType = xpp.next();
//            }
//        }catch (Exception e){
//            status = false;
//            e.printStackTrace();
//        }
//
//        for (Application app: applications){
//            Log.d("OutPut", "********************");
//            Log.d("OutPut", "Name: "+ app.getName());
//            Log.d("Output", "Artist "+ app.getArtist());
//            Log.d("Output","Release Date "+ app.getReleaseDate());
//        }
//        return true;
//    }
}
