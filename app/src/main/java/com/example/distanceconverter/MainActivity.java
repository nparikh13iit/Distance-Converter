package com.example.distanceconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radio_conv_type;
    private RadioButton mtk, ktm;
    private int conv_type;
    private String output;
    private TextView valueTxt,ansTxt,outVal,convHistBox;
    private EditText inpVal;
    private static final String TAG = "MainActivity1";
    private String historyvalues = new String("");

    private void getVals(){
        inpVal = findViewById(R.id.inpVal);
        valueTxt = findViewById(R.id.valueTxt);
        ansTxt = findViewById(R.id.ansTxt);
        convHistBox = findViewById((R.id.convHistBox));
        outVal = findViewById(R.id.outVal);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getVals();
        conv_type = 1;
        valueTxt.setText(getString(R.string.miles_value));
        ansTxt.setText(getString(R.string.kilometers_value));
        convHistBox.setMovementMethod(new ScrollingMovementMethod());
    }

    public void conversionSelection(View v){
        switch(v.getId()){
            case R.id.convmtkBtn:
                conv_type = 1;
                valueTxt.setText(getString(R.string.miles_value));
                ansTxt.setText(getString(R.string.kilometers_value));
                Log.d(TAG, "conversionSelection: "+ conv_type);
               break;
            case R.id.convktmBtn:
                conv_type = 2;
                valueTxt.setText(getString(R.string.kilometers_value));
                ansTxt.setText(getString(R.string.miles_value));
                Log.d(TAG, "conversionSelection: "+ conv_type);
                break;
        }
    }

    public void conversion(View v){
        if (inpVal.getText().toString().equals("")){
            inpVal.setError("Enter value");
        }
        else {
            double mile, kilo;
            String hist_val = "";
            Log.d(TAG, "conversion: "+conv_type);
            switch(conv_type){
                case 1:
                    mile = Double.parseDouble(inpVal.getText().toString());
                    kilo = mile * 1.60934;
                    output = String.format("%.1f",kilo);
                    outVal.setText(output);
                    hist_val = Double.toString(mile) + " Mi" + " ==> "+output+" Km\n";
                    break;
                case 2:
                    kilo = Double.parseDouble(inpVal.getText().toString());
                    mile = kilo * 0.621371;
                    output = String.format("%.1f",mile);
                    outVal.setText(output);
                    hist_val = Double.toString(kilo) + " Km" + " ==> "+output+" Mi\n";
                    break;
            }

            inpVal.setText("");
            historyvalues = hist_val + historyvalues;
            convHistBox.setText(historyvalues);
        }
    }

    public void clearHist(View w){
        historyvalues = "";
        convHistBox.setText("");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        outState.putString("histBox",historyvalues);
        outState.putString("valueTxt",valueTxt.getText().toString());
        outState.putString("outVal",outVal.getText().toString());

        outState.putString("ansTxt",ansTxt.getText().toString());
        // Call this Last
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        // Call this First
        String a,b,c;

        super.onRestoreInstanceState(savedInstanceState);


        historyvalues = savedInstanceState.getString("histBox");
        a = savedInstanceState.getString("valueTxt");
        b = savedInstanceState.getString("ansTxt");
        c = savedInstanceState.getString("outVal");


        convHistBox.setText(historyvalues);
        valueTxt.setText(a);
        ansTxt.setText(b);
        outVal.setText(c);


    }

    }
