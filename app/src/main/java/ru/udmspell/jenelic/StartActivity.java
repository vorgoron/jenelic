package ru.udmspell.jenelic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

public class StartActivity extends ActionBarActivity{

    private final String TASKS_ARRAY_KEY = "custom_tasks";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

    }

    public void onClickDefaultStart(View view) {
        Intent intent = new Intent(StartActivity.this, MainActivity.class);
        intent.putExtra(TASKS_ARRAY_KEY, getResources().getStringArray(R.array.tasks));
        startActivity(intent);
    }

    public void onClickCustomStart(View view) {
        Intent intent = new Intent(StartActivity.this, CustomTasksActivity.class);
        startActivity(intent);
    }
}
