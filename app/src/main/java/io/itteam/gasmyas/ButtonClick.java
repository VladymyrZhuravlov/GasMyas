package io.itteam.gasmyas;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

    private TextView userText, seekText;
    private String token;
    private Button connectBtn, btnLogOut, btnShow1, deconnectBtn;
    private boolean isConnect = false;
    private ImageView statusImage;
    private EditText number;
    private LinearLayout btnSection;
    private Activator activator = new Activator();
    private Spinner putSpinner, getSpinner;
    //    private SeekBar seekBar;
    private ConstraintLayout test;
    private ConstraintLayout timeZone;
    private int cooficient = 0;
    private double doubleCooficient = 0;
    private boolean standart = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);

        number = (EditText) findViewById(R.id.numberSch);
        connectBtn = (Button) findViewById(R.id.connectBtn);
        deconnectBtn = (Button) findViewById(R.id.deconnectBtn);
        btnLogOut = (Button) findViewById(R.id.btnLogOut);
        statusImage = (ImageView) findViewById(R.id.statusImage);
        btnSection = (LinearLayout) findViewById(R.id.btnSection);
        btnShow1 = (Button) findViewById(R.id.btnShow1);
        putSpinner = (Spinner) findViewById(R.id.putSpinner);
        getSpinner = (Spinner) findViewById(R.id.getSpinner);
//        seekBar = (SeekBar) findViewById(R.id.seekBar);
        test = (ConstraintLayout) findViewById(R.id.test);
        timeZone = (ConstraintLayout) findViewById(R.id.timeZone);
//        btnSection.setVisibility(View.INVISIBLE);
        connectBtn.setOnClickListener(this);
        deconnectBtn.setOnClickListener(this);
        btnShow1.setOnClickListener(this);
        btnLogOut.setOnClickListener(this);
        userText = (TextView) findViewById(R.id.userText);
        seekText = (TextView) findViewById(R.id.seekText);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("GasMyasPref", 0);
        token = pref.getString("accessToken", null);
//        Log.e("Lol kek", token);

//        seekBar.setOnSeekBarChangeListener(this);


//        putSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId) {
//                String[] choose = getResources().getStringArray(R.array.commands);
//                switch (choose[selectedItemPosition]){
//                    case "---":
//                        test.setVisibility(View.GONE);
//                        timeZone.setVisibility(View.GONE);
//                        break;
//                    case "КОЛ. ЗНАКОВ ОКРУГЛЕНИЯ":
//                        standart = true;
//                        cooficient=0;
//                        test.setVisibility(View.VISIBLE);
//                        timeZone.setVisibility(View.GONE);
//                        seekBar.setMax(4);
//                        break;
//                    case "МОЛ. КОН-ЦИЯ УГЛ ГАЗА (%)":
//                        standart = true;
//                        cooficient=0;
//                        test.setVisibility(View.VISIBLE);
//                        timeZone.setVisibility(View.GONE);
//                        seekBar.setMax(15);
//                        break;
//                    case "МОЛ. КОН-ЦИЯ АЗОТА (%)":
//                        standart = true;
//                        cooficient=0;
//                        test.setVisibility(View.VISIBLE);
//                        timeZone.setVisibility(View.GONE);
//                        seekBar.setMax(15);
//                        break;
//                    case "РАСЧЕТНЫЙ ЧАС ДЛЯ ПОЧАСОВКИ":
//                        standart = true;
//                        cooficient = 1;
//                        test.setVisibility(View.VISIBLE);
//                        timeZone.setVisibility(View.GONE);
//                        seekBar.setMax(23);
//                        break;
//                    case "НОВАЯ ДАТА":
//                        standart = true;
//                        test.setVisibility(View.GONE);
//                        timeZone.setVisibility(View.VISIBLE);
//                        break;
//                    case "НОВОЕ ВРЕМЯ":
//                        standart = true;
//                        test.setVisibility(View.GONE);
//                        timeZone.setVisibility(View.VISIBLE);
//                        break;
//                    case "ПЛОТНОСТЬ ГАЗА":
//                        standart = false;
//                        doubleCooficient = 0.66;
//                        test.setVisibility(View.VISIBLE);
//                        timeZone.setVisibility(View.GONE);
//                        seekBar.setMax(14);
//                        break;
//                    default:
//                        test.setVisibility(View.GONE);
//                        timeZone.setVisibility(View.GONE);
//                        break;
//                }
//
//            }
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });

//        getUser();

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
            case R.id.deconnectBtn:
                    statusImage.setImageResource(R.drawable.round_warning);
                    deactivated(token, number.getText().toString());
                break;
            case R.id.btnLogOut:
                SharedPreferences pref = getApplicationContext().getSharedPreferences("GasMyasPref", 0);
                pref.edit().putString("accessToken", null).apply();
                Intent intent = new Intent(ButtonClick.this, Start.class);
                startActivity(intent);
                break;
            case R.id.btnShow1:
                Intent intent1 = new Intent(ButtonClick.this, Resalt.class);
                startActivity(intent1);
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
        final ProgressDialog dialog;

        dialog = new ProgressDialog(ButtonClick.this);
        dialog.setTitle(getString(R.string.download));
        dialog.setMessage(getString(R.string.download));
        dialog.show();
        GetActivated api = RetroClientResalt.getApiServiceGetActivated();
        Call<Activator> call = api.activated(token, number);
        call.enqueue(new Callback<Activator>() {

            @Override
            public void onResponse(Call<Activator> call, Response<Activator> response) {
                dialog.dismiss();
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
        final ProgressDialog dialog;

        dialog = new ProgressDialog(ButtonClick.this);
        dialog.setTitle(getString(R.string.download));
        dialog.setMessage(getString(R.string.download));
        dialog.show();
        GetDeactivated api = RetroClientResalt.getApiServiceGetDeactivated();
        Call<Activator> call = api.deactivated(token, number);
        call.enqueue(new Callback<Activator>() {

            @Override
            public void onResponse(Call<Activator> call, Response<Activator> response) {
                dialog.dismiss();
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

//    @Override
//    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//        if (standart){
//            seekText.setText(String.valueOf( seekBar.getProgress()+ cooficient));
//        }else {
//            seekText.setText(String.format("%.2f", ((double) seekBar.getProgress()/100)+ doubleCooficient));
//        }
//    }
//
//    @Override
//    public void onStartTrackingTouch(SeekBar seekBar) {
//
//    }
//
//    @Override
//    public void onStopTrackingTouch(SeekBar seekBar) {
//
//
//    }
}
