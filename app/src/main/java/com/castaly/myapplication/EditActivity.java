package com.castaly.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class EditActivity  extends AppCompatActivity {

    EditText editText;
    String string;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        string = getIntent().getStringExtra("string");
        index = getIntent().getIntExtra("index", 0);

        editText = (EditText) findViewById(R.id.editText);
        editText.setText(string);
    }

    public void SaveEdit(View view) {
        String string = editText.getText().toString();

        if (MainActivity.IsStringNullOrEmpty(string))
        {
            return;
        }

        Intent data = new Intent();
        data.putExtra("string", string);
        data.putExtra("index", index);

        setResult(0, data);
        finish();
    }
}
