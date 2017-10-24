package io.itteam.gasmyas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class ButtonClick extends AppCompatActivity {

    EditText number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);

        number = (EditText)findViewById(R.id.numberSch);
    }

    public void btnClick(){

    }
}
