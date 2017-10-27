package io.itteam.gasmyas;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.medialablk.easytoast.EasyToast;

import io.itteam.gasmyas.json.JsonResalt;
import io.itteam.gasmyas.rest.GetResalt;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Resalt extends AppCompatActivity {

    private TextView textResalt;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resalt);

        textResalt=(TextView) findViewById(R.id.textResalt);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("GasMyasPref", 0);
       token = pref.getString("accessToken", null);
       getResalt();
    }
    private void getResalt() {
        final ProgressDialog dialog;

        dialog = new ProgressDialog(Resalt.this);
        dialog.setTitle(getString(R.string.download));
        dialog.setMessage(getString(R.string.download));
        dialog.show();
        GetResalt api = RetroClientResalt.getGetresalt();
        Call<JsonResalt> call = api.getTask(token);
        call.enqueue(new Callback<JsonResalt>() {

            @Override
            public void onResponse(Call<JsonResalt> call, Response<JsonResalt> response) {
                dialog.dismiss();
                if (response.isSuccessful()) {
                   textResalt.setText(hexToAscii(response.body().getData()));
                }
            }

            @Override
            public void onFailure(Call<JsonResalt> call, Throwable t) {
                EasyToast.error(Resalt.this, t.toString());
            }
        });
    }
    private static String hexToAscii(String hexStr) {
        StringBuilder output = new StringBuilder("");

        for (int i = 0; i < hexStr.length(); i += 2) {
            String str = hexStr.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }

        return output.toString();
    }
}
