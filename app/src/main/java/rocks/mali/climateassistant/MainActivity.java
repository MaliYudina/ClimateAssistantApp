package rocks.mali.climateassistant;

import rocks.mali.climateassistant.GetJson.*;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    // labels with updated text information
    private TextView lblTemp;
    private TextView lblHum;
    private TextView lblPress;
    private TextView lblAlt;

    // edited text - comment for data labels
    private EditText txtTempCom;
    private EditText txtTempHum;
    private EditText txtTempPress;
    private EditText txtTempAlt;

    private Button btnRefresh;

    private String theJSON;
    private String theTemp;
    private String theHum;
    private String theAlt;
    private String thePress = "745 kPa";

    @SuppressLint("SetTextI18n")
    public void showData() throws JSONException {

        JSONObject JsonResult = GetJson.jsonReturn();
        Iterator metersKey = JsonResult.keys();
        String humResult = JsonResult.getJSONObject("humidity").getString("perc");
        String tempResult = JsonResult.getJSONObject("temperature").getString("c");
        String altResult = JsonResult.getJSONObject("altitude").getString("m");
        String pressResult = JsonResult.getJSONObject("pressure").getString("mmHg");
        lblTemp.setText(tempResult + "°C");
        lblHum.setText(humResult + " %");
        lblPress.setText(pressResult + " mmHg");
        lblAlt.setText(altResult + " m");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 27) {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        lblTemp = (TextView) findViewById(R.id.lblTemp);
        lblHum = (TextView) findViewById(R.id.lblHum);
        lblPress = (TextView) findViewById(R.id.lblPress);
        lblAlt = (TextView) findViewById(R.id.lblAlt);
        btnRefresh = (Button) findViewById(R.id.btnRefresh);
        try {
            showData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        btnRefresh.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getJSON();
//            }
//
//        });

    }

}