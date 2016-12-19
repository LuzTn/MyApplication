package com.example.nouri.myapplication;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by nouri on 21/11/2016.
 */
public class CountryUpdate extends BroadcastReceiver {
    private blankActivity ba;
    private Nour nour;

    public CountryUpdate(blankActivity ba) {
        this.ba = ba;
    }

    public CountryUpdate() {
    }

    public CountryUpdate(Nour nour) {
        this.nour = nour;
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        // Get extra data included in the Intent
        Log.d("test", nour.getIntent().getAction());
        //on peut faire soit une notification soit on fait apparaitre par exemple un textview pour notifier la fin du téléchargement
//construction
        NotificationCompat.Builder b1 = new NotificationCompat.Builder(nour);


        b1.setSmallIcon(R.mipmap.ic_launcher);

        b1.setContentTitle("notification 2");
        b1.setContentText("téléchargement terminé!");

//affichage

        NotificationManager notificationManager = (NotificationManager) nour.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, b1.build());
        TextView notif = (TextView) nour.findViewById(R.id.notif);
        notif.setText("Téléchargement terminé!");


    }

    public JSONArray getCountriesFromFile() {
        try {
            InputStream is = null;
            is = new FileInputStream(nour.getCacheDir() + "/" + "countries.json");


            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            Log.d("json", is.toString());
            return new JSONArray(new String(buffer, "UTF-8"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONArray getCountriesFromFileba() {
        try {
            Log.i("test ","ba");
            InputStream is = null;
            is = new FileInputStream(ba.getCacheDir() + "/" + "countries.json");


            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            Log.d("json", is.toString());
            return new JSONArray(new String(buffer, "UTF-8"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
