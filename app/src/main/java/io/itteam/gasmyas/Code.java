package io.itteam.gasmyas;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class Code extends AppCompatActivity {

    private TextView timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);

        timer = (TextView) findViewById(R.id.timer);

        new CountDownTimer(180000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer.setText(""+String.format("%d мин, %d сек",
                        TimeUnit.MILLISECONDS.toMinutes( millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {
                Intent intent = new Intent(Code.this, Start.class);
                startActivity(intent);
            }
        }.start();

    }
}
