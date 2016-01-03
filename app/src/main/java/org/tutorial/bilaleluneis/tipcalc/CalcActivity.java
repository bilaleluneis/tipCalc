package org.tutorial.bilaleluneis.tipcalc;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class CalcActivity extends Activity implements SeekBar.OnSeekBarChangeListener, TextWatcher{

    // used to pass data on save instance of activity
    private static final String BILL_TOTAL = "BILL_TOTAL";
    private static final String CUSTOM_PERCENT = "CUSTOM_PERCENT";
    private static final String TAG =  CalcActivity.class.getName();

    private double   currentBillTotal;
    private int      currentCustomPercent;
    private TextView customTipTextView;
    private SeekBar  customSeekBar;
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
        outState.putDouble(BILL_TOTAL,currentBillTotal);
        outState.putInt(CUSTOM_PERCENT,currentCustomPercent);
    }


    private void initUIElements(){
        //obtain reference for each UI element from activity_calc.xml
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
        customSeekBar = (SeekBar) findViewById(R.id.customSeekBar);

        //attach event listener to UI elements that requires them
        billEditText.addTextChangedListener(this);
        customSeekBar.setOnSeekBarChangeListener(this);
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

    // update 10, 15 and 20 percent tip EditTexts
    private void updateStandard(){
        // calculate 10% tip and total
        double tenPercentTip = currentBillTotal * .1;
        double tenPercentTotal = currentBillTotal + tenPercentTip;
        tip10EditText.setText(String.format("%.02f",tenPercentTip));
        total10EditText.setText(String.format("%.02f",tenPercentTotal));

        //calculate 15% tip and total
        double fifteenPercentTip = currentBillTotal * .15;
        double fifteenPercentTotal = currentBillTotal + fifteenPercentTip;
        tip15EditText.setText(String.format("%.02f",fifteenPercentTip));
        total15EditText.setText(String.format("%.02f",fifteenPercentTotal));

        //calculate 20% tip and total
        double twentyPercentTip = currentBillTotal * .20;
        double twentyPercentTotal = currentBillTotal + twentyPercentTip;
        tip20EditText.setText(String.format("%.02f",twentyPercentTip));
        total20EditText.setText(String.format("%.02f", twentyPercentTotal));
    }

    private void updateCustom(){
        customTipTextView.setText(currentCustomPercent + "%");
        double customTipAmount = currentBillTotal * currentCustomPercent * .01;
        double customTotalAmount = currentBillTotal + customTipAmount;
        tipCustomEditText.setText(String.format("%.02f",customTipAmount));
        totalCustomEditText.setText(String.format("%.02f",customTotalAmount));
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        currentCustomPercent = seekBar.getProgress();
        updateCustom();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        try{
            currentBillTotal = Double.parseDouble(s.toString());
        }catch(NumberFormatException ex){
            Log.e(TAG, "onTextChanged: ", ex);
            currentBillTotal = 0.0;
        }finally{
            updateStandard();
            updateCustom();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {}

}
