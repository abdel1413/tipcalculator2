package com.example.tipcalculator2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ShareCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DecimalFormat form;
    TextView tv;
    SeekBar sb;
    EditText amount;
    TextView tip;
    TextView total;
    TextView totalSpin;
    Spinner spinner;
    RadioGroup radioGroup;
    ArrayAdapter<CharSequence> adapter;
    double barProgress;
    double amountDouble;
    int spinNumber;
    String amountString;
    String shareButton;
    public static final String TAG = "MainActivity";
    String radioText;
    androidx.appcompat.widget.Toolbar toolbar;
    private double billAmount = 0.0;
    private double numOfP = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.numPeopleSpin);
        adapter = ArrayAdapter.createFromResource(this, R.array.numbers, R.layout.spinner_layout);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
            spinner.setAdapter(adapter);
        }


        radioGroup =(RadioGroup) findViewById(R.id.radioGroup);
        totalSpin = (TextView) findViewById(R.id.totalSpinResultTv);
        form = new DecimalFormat("0.00");
        sb = (SeekBar) findViewById(R.id.seekBar);
        tv = (TextView) findViewById(R.id.PercentageTextView);
        amount = (EditText) findViewById(R.id.EnterAmountPlainText);
        tip = (TextView) findViewById(R.id.TipTotalTextView);
        total = (TextView) findViewById(R.id.TotalAmountTextView);
        radioGroup.check(R.id.no);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {



                RadioButton checkedRadioButton = findViewById(checkedId);

                String text = checkedRadioButton.getText().toString();
                radioText = "" + text;

                Log.d("SELECTED RADIO IS: ", "" + radioText);
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
            }
        });


        amount.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    //String Version to detect if it is empty and double version to make calculations
                    amountString = s.toString();
                    amountDouble = Double.parseDouble(s.toString());
                } catch (NumberFormatException e) {
                }
                calculate();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        //start SeekBar functions
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


                tv.setText(progress + "" + "%");
                barProgress = progress;


                calculate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });
    }

    // this is a compilation of references etc...
    public void widgetsReferences(){

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tip Calculator");

    }


    public void calculate() {


        try{


            switch (radioText.toLowerCase()) {


                case "total":

                    try {


                        if (amountString.isEmpty() || amountString.equals("0") || amountString.startsWith("0")) {

                            tip.setText("$0.00");
                            total.setText("$0.00");
                            amountDouble = 0.00;
                            sb.setProgress(0);
                        }
                        double tipTotal;
                        tipTotal = (amountDouble * barProgress) / 100;

                        Log.e("", "What is it?" + amountDouble);


                        tip.setText("$" + form.format(tipTotal));
                        double totalString = Math.ceil(amountDouble + tipTotal);
                        total.setText("$" + form.format(totalString));
                        totalSpin.setText("$" + form.format(totalString / spinNumber));

                        shareButton = shareMsg(form.format(amountDouble), form.format(tipTotal), form.format(totalString),
                                form.format(totalString / spinNumber));

                    } catch (NullPointerException e) {
                        sb.setProgress(0);
                    }
                    break;


                case "tip": //tip
                    try {


                        if (amountString.isEmpty() || amountString.equals("0") || amountString.startsWith("0")) {

                            tip.setText("$0.00");
                            total.setText("$0.00");
                            amountDouble = 0.00;
                            sb.setProgress(0);
                        }
                        double tipTotal;
                        tipTotal = Math.ceil((amountDouble * barProgress) / 100);

                        Log.e("", "What is it?" + amountDouble);

                        tip.setText("$" + form.format(tipTotal));
                        double totalString = amountDouble + tipTotal;
                        total.setText("$" + form.format(totalString));
                        totalSpin.setText("$" + form.format(totalString / spinNumber));

                        shareButton = shareMsg(form.format(amountDouble), form.format(tipTotal), form.format(totalString),
                                form.format(totalString / spinNumber));

                    } catch (NullPointerException e) {
                        sb.setProgress(0);
                    }
                    break;

                case "no": //no
                    try {


                        if (amountString.isEmpty() || amountString.equals("0") || amountString.startsWith("0")) {

                            tip.setText("$0.00");
                            total.setText("$0.00");
                            amountDouble = 0.00;
                            sb.setProgress(0);
                        }
                        double tipTotal;
                        tipTotal = (amountDouble * barProgress) / 100;

                        Log.e("", "What is it?" + amountDouble);


                        tip.setText("$" + form.format(tipTotal));
                        double totalString = amountDouble + tipTotal;
                        total.setText("$" + form.format(totalString));
                        totalSpin.setText("$" + form.format(totalString / spinNumber));

                        shareButton = shareMsg(form.format(amountDouble), form.format(tipTotal), form.format(totalString),
                                form.format(totalString / spinNumber));


                    } catch (NullPointerException e) {
                        sb.setProgress(0);
                    }
                    break;
            }
        }catch (NullPointerException e) {
            radioText = "no";
        }
    }


    public void checkButton(View view) {

        calculate();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String item = parent.getItemAtPosition(position).toString();
        spinNumber = Integer.parseInt(item);
        calculate();
        Log.d("TAG", " " + spinNumber);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.share) {
            Log.d(TAG, "action settings selected");
            android.content.Intent intent = new android.content.Intent();
            intent.setAction(android.content.Intent.ACTION_SEND);
            intent.putExtra(android.content.Intent.EXTRA_TEXT, "The Bill Amount " +
                    total.getText() + " Tip " + tip.getText() +
                    " Total Price " + form.format(billAmount) + " Split by " + (int) numOfP);
            intent.setType("text/plain");
            android.content.Intent chooser = android.content.Intent.createChooser(intent, "Share with");
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(chooser);
            }
            return true;
        } else if (id == R.id.info) {
            Log.d(TAG, "action alert selected");

            Dialog dialog  = new Dialog();
            dialog.show(getSupportFragmentManager(),"dialog");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void sendText() {
        Log.d(TAG, "inside sendText method");
        String theText = shareButton;
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("Choose something")
                .setText(theText)
                .startChooser();
    }


    private String shareMsg(String bill, String tip, String total , String perPerson){

        return "Bill = $" + bill +  "\n" + "Tip = $" + tip + "\nTotal = $" + total +
                "\nPer Person = $" + perPerson;
    }
}