package ru.udmspell.jenelic;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends Activity {

    private final String LOG_TAG = "jenelic_log";
    private final String TASKS_ARRAY_KEY = "custom_tasks";
    private final String TREE_KEY = "tree_key";

    private final int START_RANDOM_INT = 1000;
    private final int INTERVAL_RANDOM_INT = 4000;

    private int currentDegree = 0;
    private TextView taskText;
    private ImageView jenelic;
    private String[] tasksArray;
    private ArrayList<Integer> completeTasks = new ArrayList<>();
    private View sendButton;
    private boolean gameOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tasksArray = getIntent().getStringArrayExtra(TASKS_ARRAY_KEY);
        taskText = (TextView) findViewById(R.id.text_task);
        jenelic = (ImageView) findViewById(R.id.jenelic);
        sendButton = findViewById(R.id.sendOpinion);

        boolean tree = getIntent().getBooleanExtra(TREE_KEY, false);
        if (tree) {
            jenelic.setImageResource(R.drawable.tree_2);
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.main_layout);
            linearLayout.setBackgroundColor(getResources().getColor(R.color.ny_background_color));
        }

        jenelic.setRotation(-60);
    }

    public void onClickJenelic(View view) {

        if (gameOver) {
            return;
        }

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
                if (tasksArray.length == completeTasks.size()) {
                    taskText.setText(getString(R.string.game_over));
                    gameOver = true;
                    sendButton.setVisibility(View.VISIBLE);
                    return;
                }
                Random randomTask = new Random();
                int pos = randomTask.nextInt(tasksArray.length);
                Log.i(LOG_TAG, "first = " + pos);
                while (completeTasks.contains(pos)) {
                    pos = randomTask.nextInt(tasksArray.length);
                    Log.i(LOG_TAG, "next = " + pos);
                }
                completeTasks.add(pos);
                taskText.setText(tasksArray[pos]);
                Animation animAlpha = AnimationUtils.loadAnimation(MainActivity.this, R.anim.alpha);
                taskText.startAnimation(animAlpha);
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

    public void OnClickSend(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //Try Google play
        intent.setData(Uri.parse("market://details?id=ru.udmspell.jenelic"));
        if (!MyStartActivity(intent)) {
            //Market (Google play) app seems not installed, let's try to open a webbrowser
            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=ru.udmspell.jenelic"));
            if (!MyStartActivity(intent)) {
                //Well if this also fails, we have run out of options, inform the user.
                Toast.makeText(this, "Ӧз луы усьтыны Play Маркет приложениез, малпандэс кельтон понна пуктэ сое.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean MyStartActivity(Intent aIntent) {
        try
        {
            startActivity(aIntent);
            return true;
        }
        catch (ActivityNotFoundException e)
        {
            return false;
        }
    }
}
