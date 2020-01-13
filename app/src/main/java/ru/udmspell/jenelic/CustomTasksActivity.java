package ru.udmspell.jenelic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

public class CustomTasksActivity extends Activity {

    private final String TASKS_ARRAY_KEY = "custom_tasks";
    private final String TREE_KEY = "tree_key";

    private boolean tree;

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

        tree = getIntent().getBooleanExtra(TREE_KEY, false);
        if (tree) {
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.main_layout);
            linearLayout.setBackgroundColor(getResources().getColor(R.color.ny_background_color));
        }
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
        intent.putExtra(TREE_KEY, tree);

        startActivity(intent);
    }
}
