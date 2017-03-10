package com.example.hokku.intervalltimer;

import android.content.Context;
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
    TextView tvOnTimeLeft;
    TextView tvOffTimeLeft;
    TextView tvTimesLeft;

    Button Start;
    NumberPicker nPminOn;
    NumberPicker nPsecOn;
    NumberPicker nPminOff;
    NumberPicker nPsecOff;
    NumberPicker times;
    //Deklarerar int för numpickers.
    int iMinOnTime = 0;
    int iSecOnTime= 0;
    int iMinOffTime = 0;
    int iSecOffTime = 0;
    int iTimes = 0;

    //Mediaplayer
    MediaPlayer mp1;
    MediaPlayer mp2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Döljer keyboard vid start.

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        //Sätter upp startknapp.
        Start = (Button) findViewById(R.id.button);

        // Sätter upp Text View.
        OnTime = (TextView) findViewById(R.id.textViewOnTime);
        OffTime = (TextView) findViewById(R.id.textViewOnTimeLeft);
        tvOnTimeLeft = (TextView) findViewById(R.id.textViewOnTimeLeft);
        tvOffTimeLeft = (TextView) findViewById(R.id.textViewOffTimeLeft);
        tvTimesLeft = (TextView) findViewById(R.id.textViewIntervallLeft);

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
                iTimes = newVal;
            }
        });

        // Def av mediaplayer för beep sound.

        mp1 = MediaPlayer.create(this, R.raw.beeb5_1);
        mp2 = MediaPlayer.create(this, R.raw.beep0);
    }



    public void startbutton(View view) {
        onTime();
    }


        public void onTime () {

            new CountDownTimer(iSecOnTime * 1000, 1000) {

                public void onTick(long millisUntilFinished) {

                    long seconds = millisUntilFinished/1000;

                    tvOnTimeLeft.setText(String.format("%02d", seconds / 60)
                            + ":" + String.format("%02d", seconds % 60));

                    if(millisUntilFinished < 5000 && millisUntilFinished > 1000 ){
                        playsound5to1();

                    }

                }

                public void onFinish() {
                    tvOnTimeLeft.setText("00:00");
                    playsound0();
                    offTime();



                }
            }.start();

            tvTimesLeft.setText("Intervaller kvar: " + (iTimes -1));
        }

    public void offTime(){

        new CountDownTimer(iSecOffTime * 1000, 1000) {

            public void onTick(long millisUntilFinished) {

                long seconds = millisUntilFinished/1000;

                tvOffTimeLeft.setText(String.format("%02d", seconds / 60)
                        + ":" + String.format("%02d", seconds % 60));
            }

            public void onFinish() {
                if (iTimes>0){
                  onTime();
                    iTimes--;
                }
                else {
                    tvOffTimeLeft.setText("done!");
                }
            }
        }.start();
    }

    public void playsound5to1(){
        mp1.start();
    }

    public void playsound0(){
        mp2.start();
    }
}