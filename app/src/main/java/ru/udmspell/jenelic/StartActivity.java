package ru.udmspell.jenelic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class StartActivity extends ActionBarActivity{

    private final String TASKS_ARRAY_KEY = "custom_tasks";
    private final String TREE_KEY = "tree_key";
    private int clicks = 0;
    private int maxClicks = 6;
    private boolean tree = false;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        linearLayout = (LinearLayout) findViewById(R.id.main_layout);
    }

    public void onClickDefaultStart(View view) {
        Intent intent = new Intent(StartActivity.this, MainActivity.class);
        intent.putExtra(TASKS_ARRAY_KEY, getResources().getStringArray(R.array.tasks));
        intent.putExtra(TREE_KEY, tree);
        startActivity(intent);
    }

    public void onClickCustomStart(View view) {
        Intent intent = new Intent(StartActivity.this, CustomTasksActivity.class);
        intent.putExtra(TREE_KEY, tree);
        startActivity(intent);
    }

    public void onLogoClick(View view) {
        clicks++;

        if (clicks == maxClicks) {
            ((ImageView) view).setImageResource(R.drawable.tree);
            linearLayout.setBackgroundColor(getResources().getColor(R.color.ny_background_color));
            tree = true;
        }

    }
}
