package io.itteam.gasmyas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.medialablk.easytoast.EasyToast;

import io.itteam.gasmyas.json.activated.Activator;
import io.itteam.gasmyas.json.user.User;
import io.itteam.gasmyas.rest.GetActivated;
import io.itteam.gasmyas.rest.GetDeactivated;
import io.itteam.gasmyas.rest.GetUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ButtonClick extends AppCompatActivity implements View.OnClickListener {

    private TextView userText;
    private String token;
    private Button connectBtn, btnLogOut;
    private boolean isConnect = false;
    private ImageView statusImage;
    private EditText number;
    private LinearLayout btnSection;
    private Activator activator = new Activator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);

        number = (EditText) findViewById(R.id.numberSch);
        connectBtn = (Button) findViewById(R.id.connectBtn);
        btnLogOut = (Button) findViewById(R.id.btnLogOut);
        statusImage = (ImageView) findViewById(R.id.statusImage);
        btnSection = (LinearLayout) findViewById(R.id.btnSection);
        btnSection.setVisibility(View.INVISIBLE);
        connectBtn.setOnClickListener(this);
        btnLogOut.setOnClickListener(this);
        userText = (TextView) findViewById(R.id.userText);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("GasMyasPref", 0);
        token = pref.getString("accessToken", null);

        getUser();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.connectBtn:
                if (!activator.isConnection()) {
                    statusImage.setImageResource(R.drawable.round_warning);
                    activated(token, number.getText().toString());
                } else {
                    statusImage.setImageResource(R.drawable.round_warning);
                    deactivated(token, number.getText().toString());
                }
                break;
            case R.id.btnLogOut:
                SharedPreferences pref = getApplicationContext().getSharedPreferences("GasMyasPref", 0);
                pref.edit().putString("accessToken", null).apply();
                Intent intent = new Intent(ButtonClick.this, Start.class);
                startActivity(intent);
                break;
        }
    }

    private void getUser() {
        GetUser api = RetroClient.getUser();
        Call<User> call = api.getTask(token);
        call.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.message().equals("Unauthorized")){
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("GasMyasPref", 0);
                    pref.edit().putString("accessToken", null).apply();
                    Intent intent = new Intent(ButtonClick.this, Start.class);
                    startActivity(intent);
                }
                if (response.isSuccessful()) {
                    userText.setText(response.body().getOwner().getUsername());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                EasyToast.error(ButtonClick.this, t.toString());
            }
        });
    }

    public void activated(String token, String number) {
        GetActivated api = RetroClient.getApiServiceGetActivated();
        Call<Activator> call = api.activated(token, number);
        call.enqueue(new Callback<Activator>() {

            @Override
            public void onResponse(Call<Activator> call, Response<Activator> response) {
                if (response.isSuccessful()) {
                    activator = response.body();
                    statusImage.setImageResource(R.drawable.round_success);
                    btnSection.setVisibility(View.VISIBLE);
                    connectBtn.setText("Деактивировать");
                }
            }

            @Override
            public void onFailure(Call<Activator> call, Throwable t) {
                EasyToast.error(ButtonClick.this, t.toString());
            }
        });

    }

    public void deactivated(String token, String number) {
        GetDeactivated api = RetroClient.getApiServiceGetDeactivated();
        Call<Activator> call = api.deactivated(token, number);
        call.enqueue(new Callback<Activator>() {

            @Override
            public void onResponse(Call<Activator> call, Response<Activator> response) {
                if (response.isSuccessful()) {
                    activator = response.body();
                    statusImage.setImageResource(R.drawable.round_error);
                    btnSection.setVisibility(View.INVISIBLE);
                    connectBtn.setText("Активировать");
                }
            }

            @Override
            public void onFailure(Call<Activator> call, Throwable t) {
                EasyToast.error(ButtonClick.this, t.toString());
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
