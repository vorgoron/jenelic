package ru.udmspell.jenelic;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends ActionBarActivity {

    private final String TASKS_ARRAY_KEY = "custom_tasks";

    private final int START_RANDOM_INT = 1000;
    private final int INTERVAL_RANDOM_INT = 4000;

    private int currentDegree = 0;
    private TextView taskText;
    private ImageView jenelic;
    private String[] tasksArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tasksArray = getIntent().getStringArrayExtra(TASKS_ARRAY_KEY);
        taskText = (TextView) findViewById(R.id.text_task);
        jenelic = (ImageView) findViewById(R.id.jenelic);
        jenelic.setRotation(30);
    }

    public void onClickJenelic(View view) {

        Random random = new Random();
        int nextDegree = START_RANDOM_INT + random.nextInt(INTERVAL_RANDOM_INT);

        RotateAnimation rAnim = new RotateAnimation(currentDegree, nextDegree,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f);
        rAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                taskText.setText("");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Random randomTask = new Random();
                int pos = randomTask.nextInt(tasksArray.length);
                taskText.setText(tasksArray[pos]);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        currentDegree = (nextDegree) % 360;

        rAnim.setDuration(nextDegree + 10000);
        rAnim.setFillEnabled(true);
        rAnim.setFillAfter(true);
        rAnim.setInterpolator(new DecelerateInterpolator());

        jenelic.startAnimation(rAnim);
    }
}
