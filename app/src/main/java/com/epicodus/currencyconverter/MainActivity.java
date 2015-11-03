package com.epicodus.currencyconverter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private CurrencyConversion mCurrencyConversion;

    @Bind(R.id.amountField) EditText amountField;
    @Bind(R.id.sourceSpinner) Spinner sourceSpinner;
    @Bind(R.id.targetSpinner) Spinner targetSpinner;
    @Bind(R.id.rateResult) TextView rateResult;
    @Bind(R.id.conversionResult) TextView conversionResult;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        String source = "USD";
        String target = "EUR";
        String format = "json";
        String currencyUrl =  "https://currency-api.appspot.com/api/" +
        source + "/" + target + "." + format;

        if(isNetworkAvailable()) {

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(currencyUrl)
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {

                }

                @Override
                public void onResponse(Response response) throws IOException {
                    try {
                        String jsonData = response.body().string();
                        Log.v(TAG, jsonData);
                        if (response.isSuccessful()) {
                            mCurrencyConversion = getCurrencyDetails(jsonData);
                        } else {
                            alertUserAboutError();
                        }
                    } catch (IOException e) {
                        Log.e(TAG, "Exception caught: ", e);
                    }
                    catch (JSONException e) {
                        Log.e(TAG, "Exception caught: ", e);
                    }
                }
            });
        }
        else {
            Toast.makeText(this, "Network is unavailable", Toast.LENGTH_LONG).show();
        }
    }

    private CurrencyConversion getCurrencyDetails(String jsonData) throws JSONException {
        JSONObject conversionData = new JSONObject(jsonData);

        CurrencyConversion currencyConversion = new CurrencyConversion();
        currencyConversion.setSource(conversionData.getString("source"));
        currencyConversion.setTarget(conversionData.getString("target"));
        currencyConversion.setAmount(conversionData.getDouble("amount"));
        currencyConversion.setRate(conversionData.getDouble("rate"));

        return currencyConversion;

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    private void alertUserAboutError() {
        AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.show(getFragmentManager(), "error_dialog");
    }
}
