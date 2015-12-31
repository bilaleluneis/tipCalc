package org.tutorial.bilaleluneis.tipcalc;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class CalcActivity extends Activity {

    // used to pass data on save instance of activity
    private static final String BILL_TOTAL = "BILL_TOTAL";
    private static final String CUSTOM_PERCENT = "CUSTOM_PERCENT";

    private double currentBillTotal;
    private int currentCustomPercent;
    private TextView customTipTextView;
    private EditText tip10EditText;
    private EditText tip15EditText;
    private EditText tip20EditText;
    private EditText total10EditText;
    private EditText total15EditText;
    private EditText total20EditText;
    private EditText billEditText;
    private EditText tipCustomEditText;
    private EditText totalCustomEditText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        reloadSavedValues(savedInstanceState);
        initUIElements();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    // Obtain references to UI elements
    private void initUIElements(){
        customTipTextView = (TextView) findViewById(R.id.customTipTextView);
        tip10EditText = (EditText) findViewById(R.id.tip10EditText);
        tip15EditText = (EditText) findViewById(R.id.tip15EditText);
        tip20EditText = (EditText) findViewById(R.id.tip20EditText);
        total10EditText = (EditText) findViewById(R.id.total10EditText);
        total15EditText = (EditText) findViewById(R.id.total15EditText);
        total20EditText = (EditText) findViewById(R.id.total20EditText);
        billEditText = (EditText) findViewById(R.id.billEditText);
        tipCustomEditText = (EditText) findViewById(R.id.tipCustomEditText);
        totalCustomEditText = (EditText) findViewById(R.id.totalCustomEditText);
    }

    //Reloads saved data if there are any
    private void reloadSavedValues(Bundle savedInstanceState){
        //check if app just started v.s was already running
        if(savedInstanceState == null){
            currentBillTotal = 0.0;
            currentCustomPercent= 18;
        }else{
            currentBillTotal = savedInstanceState.getDouble(BILL_TOTAL);
            currentCustomPercent = savedInstanceState.getInt(CUSTOM_PERCENT);
        }
    }

}
