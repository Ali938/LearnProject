package learn.coleo.com.learnproject;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.util.TimerTask;

import oupson.apng.ApngAnimator;
import oupson.apng.utils.ApngAnimatorOptions;

public class Splash extends AppCompatActivity {

    String TAG = "splash";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView imageView = findViewById(R.id.animation_image_id);

        File image = new File("../res/drawable/hello.png");
        if (image == null){
            Log.i(TAG, "onCreate: ");
        }else {
            ApngAnimator animator = new ApngAnimator(this).loadInto(imageView);
            ApngAnimatorOptions options = new ApngAnimatorOptions(ImageView.ScaleType.CENTER_CROP);
            animator.load(image, 1f, options);
        }
//        final Context context = this;
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(context,LoginActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        };
//
//        new Handler().postDelayed(runnable,3000);

    }
}
