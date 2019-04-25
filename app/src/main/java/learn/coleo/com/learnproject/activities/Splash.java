package learn.coleo.com.learnproject.activities;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;
import android.widget.TextView;

import learn.coleo.com.learnproject.R;
import learn.coleo.com.learnproject.constants.Constants;

public class Splash extends AppCompatActivity {

    String TAG = "splash";
    private ImageView imageView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Context context = this;

        imageView = findViewById(R.id.animation_image_id);
        textView = findViewById(R.id.animation_text_app_name_id);
//        RotateAnimation anim = new RotateAnimation(0f, 360f,0f,50f);
//        anim.setInterpolator(new LinearInterpolator());
//        anim.setRepeatCount(Animation.INFINITE);
//        anim.setDuration(1000);
//
//        imageView.startAnimation(anim);

        int height = Constants.getHeightOfScreen(this);
        float temp = (((float) height) / 2) * 3;

        final ObjectAnimator animationMove = ObjectAnimator.ofFloat(imageView, "translationY", temp);
        animationMove.setDuration(1000);

//        Animation fadeIn = new AlphaAnimation(0, 1);
//        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
//        fadeIn.setDuration(1000);

        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
        fadeOut.setStartOffset(1500);
        fadeOut.setDuration(1700);

        final AnimationSet animation = new AnimationSet(false); //change to false
//        animation.addAnimation(fadeIn);
        animation.addAnimation(fadeOut);

        textView.setAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                animationMove.start();
            }
        }, 1000);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(context,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        };
        new Handler().postDelayed(runnable, 3000);





    }

}
