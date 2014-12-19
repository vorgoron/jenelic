package ru.udmspell.jenelic;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;


public class MainActivity extends ActionBarActivity {

    ImageView jenelic;
    Animation animationRotate;
    int curentDegree = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jenelic = (ImageView) findViewById(R.id.jenelic);

        animationRotate = AnimationUtils.loadAnimation(this, R.anim.rotate_anim);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void startRotation(View view) {
//        jenelic.startAnimation(animationRotate);

        RotateAnimation rAnim = new RotateAnimation(curentDegree, curentDegree + 1000,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f);

        curentDegree = (curentDegree + 1000) % 360;

        rAnim.setDuration(3000L);
        rAnim.setFillEnabled(true);
        rAnim.setFillAfter(true);
        rAnim.setInterpolator(new DecelerateInterpolator());

        jenelic.startAnimation(rAnim);
    }
}
