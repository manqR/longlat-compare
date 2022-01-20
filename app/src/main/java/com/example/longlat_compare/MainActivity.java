package com.example.longlat_compare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {



    Button btn;
    TextView res, shiftLat, shiftLong, siteLat, siteLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.submit);
        res = (TextView) findViewById(R.id.result);
        shiftLat = (EditText) findViewById(R.id.shiftLat);
        shiftLong = (EditText) findViewById(R.id.shiftLong);
        siteLat = (EditText) findViewById(R.id.siteLat);
        siteLong = (EditText) findViewById(R.id.siteLong);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    double shift_longitude = Double.parseDouble(shiftLong.getText().toString());
                    double shift_latitude =  Double.parseDouble(shiftLat.getText().toString());
                    double site_longitude = Double.parseDouble(siteLong.getText().toString());
                    double site_latitude = Double.parseDouble(siteLat.getText().toString());


                    double result = distance(site_latitude,site_longitude,shift_latitude,shift_longitude);
                    String resString = new DecimalFormat("##.##").format(result);
                    res.setText("Result Distance (km) : "+resString);
                }catch (Exception ex){
                    Toast.makeText(getApplicationContext(), "Please Check your input \n"+ ex.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }
        });

    }


    private double distance(double lat1, double lng1, double lat2, double lng2) {

        double earthRadius = 6371; // 3958.75 in miles, change to 6371 for kilometer output

        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);

        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);

        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        double dist = earthRadius * c;

        return dist;
    }
}