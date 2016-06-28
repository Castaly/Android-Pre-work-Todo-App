package com.castaly.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText todoText;
    private ListView todoView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> todoList = new ArrayList<String>();
    private int todoListIndex = 0;
    private int RESULT_CODE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        todoText = (EditText) findViewById(R.id.todoText);
        todoView = (ListView) findViewById(R.id.todoView);
        arrayAdapter = new ArrayAdapter(this, R.layout.text_view, todoList);
        todoView.setAdapter(arrayAdapter);
        todoView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                LaunchEditMode(index);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        String string = data.getStringExtra("string");
        int index = data.getIntExtra("index", 0);
        todoList.set(index, string);

        arrayAdapter.notifyDataSetChanged();
    }

    public void LaunchEditMode(int index)
    {
        Intent i = new Intent(this, EditActivity.class);
        i.putExtra("string", todoList.get(index).toString());
        i.putExtra("index", index);

        startActivityForResult(i, RESULT_CODE);
    }

    public void AddTodoItem(View view) {
        String string = todoText.getText().toString();
        if(IsStringNullOrEmpty(string))
        {
            return;
        }

        todoText.setText("");
        todoList.add(string);
        arrayAdapter.notifyDataSetChanged();
    }

    public static Boolean IsStringNullOrEmpty(String string)
    {
        if (string == null)
        {
            return true;
        }

        if(string.trim().length() == 0)
        {
            return true;
        }

        return false;
    }
}
