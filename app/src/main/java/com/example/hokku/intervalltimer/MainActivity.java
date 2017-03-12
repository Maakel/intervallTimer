package com.example.hokku.intervalltimer;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {


    TextView OnTime;
    TextView OffTime;


    Button Start;
    NumberPicker nPminOn;
    NumberPicker nPsecOn;
    NumberPicker nPminOff;
    NumberPicker nPsecOff;
    NumberPicker times;
    //Deklarerar int för numpickers.
    public static  int iMinOnTime = 0;
    public static int iSecOnTime= 0;
    public static int iMinOffTime = 0;
    public static int iSecOffTime = 0;
    public static int iTimes;
    public static int iTimesSet;


    public static long lOntime;                        //Variabel för atntalet minuter och sekunder som skall köras.
    long lOnTimeSecCalculate;                          //Beräkningsvariabel för metod att ta fram millisekunder från sekunder.
    long lOnTimeMinCalculate;                          //Beräkningsvariabel för att ta fram millisekunder från minuter.

    public static long lOfftime;                        //Variabel för atntalet minuter och sekunder som skall köras.
    long lOffTimeSecCalculate;                          //Beräkningsvariabel för metod att ta fram millisekunder från sekunder.
    long lOffTimeMinCalculate;                          //Beräkningsvariabel för att ta fram millisekunder från minuter.





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Döljer keyboard vid start.

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //Nollställer int vi start.
        iTimes=0;
        iTimesSet=0;


        //Sätter upp startknapp.
        Start = (Button) findViewById(R.id.button);

        // Sätter upp Text View.
        OnTime = (TextView) findViewById(R.id.textViewOnTime);
        OffTime = (TextView) findViewById(R.id.textViewOnTimeLeft);


        //Sätter upp Num picker.
        nPminOn = (NumberPicker) findViewById(R.id.numberPickerMinOn);
        nPsecOn = (NumberPicker) findViewById(R.id.numberPickerSecOn);
        nPminOff = (NumberPicker) findViewById(R.id.numberPickerMinOff);
        nPsecOff = (NumberPicker) findViewById(R.id.numberPickerSecOff);
        times = (NumberPicker) findViewById(R.id.numberPickerTimes);



        //Sätter max och min värde på numpicker
        nPminOn.setMaxValue(60);
        nPminOn.setMinValue(0);
        nPsecOn.setMaxValue(60);
        nPsecOn.setMinValue(0);
        nPminOff.setMaxValue(60);
        nPminOff.setMinValue(0);
        nPsecOff.setMaxValue(60);
        nPsecOff.setMinValue(0);
        times.setMaxValue(1000);
        times.setMinValue(0);
        times.setWrapSelectorWheel(false);

        //Sätter upp listeners för Num pickers.
        nPminOn.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                iMinOnTime = newVal;
            }
        });


        nPsecOn.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            iSecOnTime = newVal;
            }
        });

        nPminOff.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                iMinOffTime = newVal;
            }
        });

        nPsecOff.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                iSecOffTime = newVal;
            }
        });

        times.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //iTimes = newVal;
                iTimesSet =newVal;
            }
        });


    }

    //Räknar ut antalet millisekunder att köra timer on.
    public long calculateOntime(int minOn, int secOn){

        //Tar in sekund värdet och konverterar till millisekunder.
        lOnTimeSecCalculate = secOn * 1000;
        lOntime = lOnTimeSecCalculate;
        //Tar in minutvärdet och konverterar till millisekunder.
        if(MainActivity.iMinOnTime>0){
            lOnTimeMinCalculate = minOn *60000;
            lOntime = lOnTimeSecCalculate + lOnTimeMinCalculate;
        }
        return lOntime;
    }

    //Räknar ut antalet millisekunder att köra timer Off.
    public long calculateOfftime(int minOff, int secOff){

        //Tar in sekund värdet och konverterar till millisekunder.
        lOffTimeSecCalculate = secOff * 1000;
        lOfftime = lOffTimeSecCalculate;
        //Tar in minutvärdet och konverterar till millisekunder.
        if(MainActivity.iMinOffTime>0){
            lOffTimeMinCalculate = minOff*60000;
            lOfftime = lOffTimeSecCalculate + lOffTimeMinCalculate;
        }
        return lOfftime;
    }


    //Startar timer.
    public void startbutton(View view) {
        //Sätter antal körda intervaller till 0.
        iTimes =0;
        //Kör metod föt att ta reda på antal millisekunder som skall köras.
        calculateOntime(iMinOnTime,iSecOnTime);
        calculateOfftime(iMinOffTime,iSecOffTime);

        Intent intent = new Intent(this, mycounter.class);
        startActivity(intent);
    }

}