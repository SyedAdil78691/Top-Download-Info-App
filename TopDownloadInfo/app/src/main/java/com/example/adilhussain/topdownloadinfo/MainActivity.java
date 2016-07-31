package com.example.adilhussain.topdownloadinfo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Security;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String mFileContent;
    private ListView XMLListView;
    ParseApplication parseApplication;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        XMLListView = (ListView)findViewById(R.id.XMLlistView);

        navigationView = (NavigationView)findViewById(R.id.nav_view);
        fillMenu(navigationView.getMenu());




//        DownloadData downloadData= new DownloadData();
//        downloadData.execute("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=10/xml");



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Log.d("id" , ""+id);
        if (id == 0) {
            parseApplication = new ParseApplication(mFileContent);
            parseApplication.process();
            Log.d("Before Array Adpater", "Better way");
            CustomerAdapter arrayAdapter = new CustomerAdapter(this, parseApplication.getApplications());
            Log.d("Put Data into XMLListVi", "Better way");
            XMLListView.setAdapter(arrayAdapter);

            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void fillMenu (Menu menu)
    {
        for (int i=0; i< appConstant.Source.length ; i++){
            menu.add(R.id.sourcesGroup, i , i, appConstant.Source[i]);
        }
    }







//
//
//
//    private class DownloadData extends AsyncTask<String,Void,String>{
//
//
//        @Override
//        protected String doInBackground(String... params) {
//
//            mFileContent = downloadXMLFile(params[0]);
//            if (mFileContent == null)
//                Log.d("Download Data", "Error Downloading");
//            return mFileContent;
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//            Log.d("Download Data ","Result was "+result);
//
//        }
//
////        private String downloadXMLFile(String  urlPath){
////            StringBuilder temBuffer = new StringBuilder();
////            try{
////                URL url = new URL(urlPath);
////                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
////                int response = connection.getResponseCode();
////                Log.d("Download Data", "Response code was "+response);
////                InputStream is = connection.getInputStream();
////                InputStreamReader isr = new InputStreamReader(is);
////                int charRead;
////                char [] inputBuffer = new char[500];
////
////                while (true){
////                    charRead = isr.read(inputBuffer);
////                    if (charRead <= 0){
////                        break;
////                    }
////                    temBuffer.append(String.copyValueOf(inputBuffer, 0, charRead));
////
////                }
////                return temBuffer.toString();
////
////            }catch (IOException e){
////                Log.d("Download Data","IO Exception reading data"+e.getMessage());
////                e.printStackTrace();
////            }catch (SecurityException se){
////                Log.d("Download Data","Internet not Available"+se.getMessage());
////            }
////        return  null;
////        }
//    }
}
