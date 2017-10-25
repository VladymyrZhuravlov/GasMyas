package io.itteam.gasmyas;

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

import io.itteam.gasmyas.json.user.User;
import io.itteam.gasmyas.rest.GetUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ButtonClick extends AppCompatActivity implements View.OnClickListener {

    private TextView userText;
    private String token;
    private Button connectBtn;
    private boolean isConnect = false;
    private ImageView statusImage;
    private EditText number;
    private LinearLayout btnSection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);

        number = (EditText)findViewById(R.id.numberSch);
        connectBtn = (Button) findViewById(R.id.connectBtn);
        statusImage = (ImageView) findViewById(R.id.statusImage);
        btnSection = (LinearLayout) findViewById(R.id.btnSection);
        btnSection.setVisibility(View.INVISIBLE);
        connectBtn.setOnClickListener(this);
        userText = (TextView) findViewById(R.id.userText);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        token = pref.getString("accessToken", null);

        getUser();
    }

    private void getUser() {
        GetUser api = RetroClient.getUser();
        Call<User> call = api.getTask(token);
        call.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
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

    @Override
    public void onClick(View view) {
            if (isConnect) {
                statusImage.setImageResource(R.drawable.round_error);
                isConnect = false;
                btnSection.setVisibility(View.INVISIBLE);
                connectBtn.setText("Активировать");
            } else {
                statusImage.setImageResource(R.drawable.round_success);
                btnSection.setVisibility(View.VISIBLE);
                isConnect = true;
                connectBtn.setText("Деактивировать");
            }
    }
}
