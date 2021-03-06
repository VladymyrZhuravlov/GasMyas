package io.itteam.gasmyas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.medialablk.easytoast.EasyToast;

import java.util.concurrent.TimeUnit;

import io.itteam.gasmyas.json.AccessToken;
import io.itteam.gasmyas.rest.SignIn;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Code extends AppCompatActivity implements View.OnClickListener {

    private TextView timer;
    private Button signin;
    private EditText code;
    private String phone;
    private AccessToken accessToken = new AccessToken();
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);

        pref = getApplicationContext().getSharedPreferences("GasMyasPref", 0); // 0 - for private mode


        timer = (TextView) findViewById(R.id.timer);
        signin = (Button) findViewById(R.id.signin);
        code = (EditText) findViewById(R.id.code);
        signin.setOnClickListener(this);

        phone = getIntent().getExtras().getString("phone");

        countDownTimer = new CountDownTimer(180000, 1000) {
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

    @Override
    public void onClick(View view) {
        signIn(phone, code.getText().toString());
        countDownTimer.cancel();
    }

    private void signIn(String phone, String code) {
        SignIn api = RetroClient.getApiServiceSignIn();
        Call<AccessToken> call = api.getTask(phone, code);
        call.enqueue(new Callback<AccessToken>() {

            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                if (response.isSuccessful()) {
                    accessToken = response.body();
                        Toast.makeText(Code.this, "OK", Toast.LENGTH_SHORT).show();
                        editor = pref.edit();
                        editor.putString("accessToken", "Bearer " + accessToken.getAccessToken() );
                        editor.apply();
                        Intent intent = new Intent(Code.this, ButtonClick.class);
                        startActivity(intent);
                } else {
                    EasyToast.error(Code.this, "Code no valid!");
                    Intent intent = new Intent(Code.this, Start.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                EasyToast.error(Code.this, t.toString());
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
