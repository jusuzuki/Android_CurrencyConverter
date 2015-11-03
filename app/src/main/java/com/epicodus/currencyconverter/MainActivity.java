package com.epicodus.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String source = "USD";
        String target = "EUR";
        String format = "json";
        String currencyUrl =  "https://currency-api.appspot.com/api/" +
        source + "/" + target + "." + format;
    }
}
