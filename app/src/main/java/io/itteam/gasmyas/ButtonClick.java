package io.itteam.gasmyas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.medialablk.easytoast.EasyToast;

public class ButtonClick extends AppCompatActivity implements View.OnClickListener {

    private Button connectBtn;
    private boolean isConnect = false;
    private ImageView statusImage;
    private EditText number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);

        number = (EditText)findViewById(R.id.numberSch);
        connectBtn = (Button) findViewById(R.id.connectBtn);
        statusImage = (ImageView) findViewById(R.id.statusImage);
        connectBtn.setOnClickListener(this);

    }

    public void btnClick(){

    }

    @Override
    public void onClick(View view) {
        if (!number.getText().toString().equals("")) {
            if (isConnect) {
                statusImage.setImageResource(R.drawable.round_error);
                isConnect = false;
                connectBtn.setText("Активировать");
            } else {
                statusImage.setImageResource(R.drawable.round_success);
                isConnect = true;
                connectBtn.setText("Деактивировать");
            }
        } else {
            EasyToast.error(ButtonClick.this, "Введите номер!!!");
        }

    }
}
