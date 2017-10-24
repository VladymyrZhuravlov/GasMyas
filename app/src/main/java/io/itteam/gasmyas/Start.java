package io.itteam.gasmyas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Start extends AppCompatActivity {

    private EditText phoneNumber;
    private Button getCodeBtn;

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
                        Intent intent = new Intent(Start.this, Code.class);
                        intent.putExtra("phone", "38" + phoneNumber.getText().toString());
                        startActivity(intent);
                }
            }
        });
    }
}
