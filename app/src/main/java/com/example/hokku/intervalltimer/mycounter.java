package com.example.hokku.intervalltimer;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class mycounter extends AppCompatActivity {

    TextView tvOnTimeLeft;
    TextView tvOffTimeLeft;
    TextView tvTimesLeft;
    TextView tvDone;

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

        if (status == 0 ){
            onTime();
        }
    }







    public void onTime () {
        //Plussar på antal gånger.
        MainActivity.iTimes++;
        tvTimesLeft.setText((MainActivity.iTimes) + "/" + MainActivity.iTimesSet );

        //Sätter satus för att intervalltimer är på.
        status = 1;
        //Tar in minutvärdet och konverterar till sekunder.

        new CountDownTimer(MainActivity.iSecOnTime * 1000, 500) {



            public void onTick(long millisUntilFinished) {

                long seconds = millisUntilFinished/1000;

                tvOnTimeLeft.setText(String.format("%02d", seconds / 60)
                        + ":" + String.format("%02d", seconds % 60));

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
        new CountDownTimer(MainActivity.iSecOffTime * 1000, 500) {

            public void onTick(long millisUntilFinished) {
                long seconds = millisUntilFinished/1000;
                tvOffTimeLeft.setText(String.format("%02d", seconds / 60)
                        + ":" + String.format("%02d", seconds % 60));
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

