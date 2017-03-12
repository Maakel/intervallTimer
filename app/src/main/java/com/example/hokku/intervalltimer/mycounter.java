package com.example.hokku.intervalltimer;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class mycounter extends AppCompatActivity {

    TextView tvOnTimeLeft;
    TextView tvOffTimeLeft;
    TextView tvTimesLeft;
    TextView tvDone;
    int test;


    //Mediaplayer
    MediaPlayer mp1;
    MediaPlayer mp2;

    //Status på timer.
    int status=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycounter);

        //Deklarerar TextView.
        tvOnTimeLeft = (TextView) findViewById(R.id.textViewOnTimeLeft);
        tvOffTimeLeft = (TextView) findViewById(R.id.textViewOffTimeLeft);
        tvTimesLeft = (TextView) findViewById(R.id.textViewIntervallLeft);
        tvDone = (TextView) findViewById(R.id.textViewDone);

        // Sätter färgen på texten i tv
        tvOnTimeLeft.setTextColor(Color.parseColor("#66ff33"));
        tvOffTimeLeft.setTextColor(Color.parseColor("#ff3300"));
        tvTimesLeft.setTextColor(Color.parseColor("#000000"));
        tvDone.setTextColor(Color.parseColor("#000000"));

        // Def av mediaplayer för beep sound.

        mp1 = MediaPlayer.create(this, R.raw.beeb5_1);
        mp2 = MediaPlayer.create(this, R.raw.beep0);

        //Kontrollerar om timern skall köras.

        if (status == 0 && MainActivity.iTimesSet>0 ){
            onTime();
        }

    }





    public void onTime () {
        //Plussar på antal gånger.
        MainActivity.iTimes++;
        tvTimesLeft.setText((MainActivity.iTimes) + "/" + MainActivity.iTimesSet );

        //Sätter satus för att intervalltimer är på.
        status = 1;


        new CountDownTimer(MainActivity.lOntime , 500) {
            //TODO lägga till ljud när aktivitet startar.
            //TODO Lägga till ljud efter halva tiden har gått.
            //TODO Är denna runnable så man skall göra ?
            //TODO Stoppa mediaspelar när man spelat klart.


            public void onTick(long millisUntilFinished) {

                long seconds = millisUntilFinished/1000;


                tvOnTimeLeft.setText(""+String.format("%02d : %02d",
                    TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));

                if(millisUntilFinished < 5000 && millisUntilFinished > 1000 ) {
                    new Runnable() {
                        @Override
                        public void run() {
                            playsound5to1();
                        }

                    }.run();

                }
                if(millisUntilFinished < 1000) {
                    new Runnable() {
                        @Override
                        public void run() {
                            playsound0();
                        }
                    }.run();

                }
            }

            public void onFinish() {
                tvOnTimeLeft.setText("00:00");

                offTime();
            }
        }.start();

    }

    public void offTime(){
        new CountDownTimer(MainActivity.lOfftime, 500) {

            public void onTick(long millisUntilFinished) {
                long seconds = millisUntilFinished/1000;

                tvOffTimeLeft.setText(""+String.format("%02d : %02d",
                        TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));

            }
            public void onFinish() {

                tvOffTimeLeft.setText("00:00");
                if (MainActivity.iTimes != MainActivity.iTimesSet){
                    onTime();

                }
                else {
                    tvOffTimeLeft.setText("00:00");
                    tvDone.setText("Finished!");
                    tvDone.setBackgroundColor(Color.parseColor("#66ff33"));
                    //Sätter status på timer till 0.
                    status = 0;
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

