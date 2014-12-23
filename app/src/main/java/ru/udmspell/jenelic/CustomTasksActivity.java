package ru.udmspell.jenelic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class CustomTasksActivity extends ActionBarActivity {

    private final String TASKS_ARRAY_KEY = "custom_tasks";

    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_tasks);
        listView = (ListView) findViewById(R.id.listViewTasks);
        editText = (EditText) findViewById(R.id.addTaskEdit);
        arrayAdapter = new ArrayAdapter<>(this, R.layout.list_item);
        listView.setAdapter(arrayAdapter);
    }

    public void onClickAddTask(View view) {
        String newTask = editText.getText().toString();
        if (newTask.equals("")) {
            return;
        }
        arrayAdapter.add(newTask);
        arrayAdapter.notifyDataSetChanged();
        editText.setText("");
    }

    public void onClickNext(View view) {
        if (arrayAdapter.getCount() == 0) {
            return;
        }
        Intent intent = new Intent(CustomTasksActivity.this, MainActivity.class);

        int tasksCount = arrayAdapter.getCount();
        String[] strings = new String[tasksCount];

        for (int i = 0; i < tasksCount; i++)
            strings[i] = arrayAdapter.getItem(i);

        intent.putExtra(TASKS_ARRAY_KEY, strings);

        startActivity(intent);
    }
}
