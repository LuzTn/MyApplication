package com.example.nouri.myapplication;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.content.ContentValues.TAG;


public class GetCountriesService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_get_all_countries = "com.example.nouri.myapplication.action.get_all_countries";

    public GetCountriesService() {
        super("GetCountriesService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionGet_all_countries(Context context) {
        Intent intent = new Intent(context, GetCountriesService.class);
        intent.setAction(ACTION_get_all_countries);

        context.startService(intent);
    }



    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_get_all_countries.equals(action)) {
                Log.d(TAG,"Thread service name: "+ Thread.currentThread().getName());
                URL url = null;
               try {
                   url = new URL("http://binouze.fabrigli.fr/countries.json");
                   HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                   conn.setRequestMethod("GET");
                   if(HttpURLConnection.HTTP_OK == conn.getResponseCode()){
                       copyInputStreamToFile(conn.getInputStream(), new File(getCacheDir(), "countries.json")); // fichier dans le cache
                       Log.v("test","test");
                   }

               }
            catch (MalformedURLException e){
            e.printStackTrace();
            }
                catch (IOException e){
                    e.printStackTrace();
                }
        }
    }

        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(Nour.Countries_UPDATE) );
    }
    private void copyInputStreamToFile(InputStream in, File file){
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while ((len=in.read(buf))>0){
                out.write(buf,0,len);//lecture du fichier json un par un puisque il y a beaucoup d'éléments
            }
            out.close();
            in.close();
        } catch (Exception e){
            e.printStackTrace();
        }

    }


    }
