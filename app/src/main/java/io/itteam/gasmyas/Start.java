package io.itteam.gasmyas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.medialablk.easytoast.EasyToast;

import io.itteam.gasmyas.json.PhoneNumber;
import io.itteam.gasmyas.json.PostCode;
import io.itteam.gasmyas.rest.PostRegistration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Start extends AppCompatActivity {

    private EditText phoneNumber;
    private Button getCodeBtn;
    private String phoneText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        getCodeBtn = (Button) findViewById(R.id.getCodeBtn);
        getCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.getCodeBtn:

                        phoneText = "38" + phoneNumber.getText().toString();
                        postCode(phoneText);

                }
            }
        });
    }

    private void postCode(final String phoneNum) {
        PhoneNumber phoneNumber = new PhoneNumber(phoneNum);
        PostRegistration api = RetroClient.getCode();
        Call<PostCode> call = api.getCode(phoneNumber);
        call.enqueue(new Callback<PostCode>() {
            @Override
            public void onResponse(Call<PostCode> call, Response<PostCode> response) {
                if (response.isSuccessful()) {
                    Log.e("Lol Kek", "Code: " + response.body().getSmsCode());
                    EasyToast.success(Start.this, response.body().getSmsCode(), Toast.LENGTH_LONG);
                    Intent intent = new Intent(Start.this, Code.class);
                    intent.putExtra("phone", phoneText);
                    startActivity(intent);
                } else {
                    EasyToast.error(Start.this, "This phone " + phoneNum + " number is not allowed.");
                }
            }

            @Override
            public void onFailure(Call<PostCode> call, Throwable t) {
            }
        });
    }
}
