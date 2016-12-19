package com.example.nouri.myapplication;

import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class Nour extends AppCompatActivity {

    public static final String Countries_UPDATE = "com.octip.cours.inf4042_11.Countries_UPDATE";

    TextView dateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nour);
        TextView tv_hw = (TextView) findViewById(R.id.tv_hello_world);

        String now = DateUtils.formatDateTime(getApplicationContext(), (new java.util.Date()).getTime(), DateFormat.FULL);
        tv_hw.setText(now);
        Button btn_hw = (Button) findViewById(R.id.btn_hello_world);
        btn_hw.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), getString(R.string.btn), Toast.LENGTH_LONG).show();

            }
        });
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                Intent i = new Intent(Nour.this, blankActivity.class);
                startActivity(i);


            }
        });
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                Intent i = new Intent(Nour.this, blankActivity.class);
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=Londre")));


            }
        });
        DatePickerDialog dpd;

        dateView = (TextView) findViewById(R.id.textView3);
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
// TODO: ecrire la date dans un textview
                dateView.setText(new StringBuilder().append(dayOfMonth).append("/")
                        .append(month + 1).append("/").append(year));
            }
        };
        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        dpd = new DatePickerDialog(this, listener, mYear, mMonth, mDay);
        dpd.show();
//construction
        NotificationCompat.Builder b = new NotificationCompat.Builder(this);


        b.setSmallIcon(R.mipmap.ic_launcher);

        b.setContentTitle("notification");
        b.setContentText("test notification");

//affichage

        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, b.build());
        GetCountriesService.startActionGet_all_countries(this);
        IntentFilter intentFilter = new IntentFilter(Countries_UPDATE);
        CountryUpdate cu = new CountryUpdate(this);
        LocalBroadcastManager.getInstance(this).registerReceiver(cu, intentFilter);

       /* JSONArray array = cu.getCountriesFromFile();
        if (array != null) {
            Log.i("json", array.toString());
            Log.i("length", "equals" + array.length());

        }*/
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_file, menu);
        return true;
    }


}

