package learn.coleo.com.learnproject.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.gigamole.navigationtabstrip.NavigationTabStrip;

import learn.coleo.com.learnproject.R;
import learn.coleo.com.learnproject.activities.main_page_fragment.Profile;
import learn.coleo.com.learnproject.activities.main_page_fragment.Projects;
import learn.coleo.com.learnproject.activities.main_page_fragment.Tasks;

public class MainActivity extends AppCompatActivity {

    private String TAG = "main_ACTIVITY";
    public static String USERNAME_DATA = "user from login";
    private final Context context = this;

    final FragmentManager fm = getSupportFragmentManager();
    final Fragment fragment1 = new Profile();
    final Fragment fragment2 = new Projects();
    final Fragment fragment3 = new Tasks();
    Fragment active = fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final NavigationTabStrip navigationTabStrip = findViewById(R.id.navigation);
        navigationTabStrip.setOnTabStripSelectedIndexListener(new NavigationTabStrip.OnTabStripSelectedIndexListener() {
            @Override
            public void onStartTabSelected(String title, int index) {
                Log.i(TAG, "onPageSelected: ");
                switch (index) {
                    case (0): {
                        fm.beginTransaction().hide(active).show(fragment1).commit();
                        active = fragment1;
                        break;
                    }
                    case (1): {
                        fm.beginTransaction().hide(active).show(fragment2).commit();
                        active = fragment2;
                        break;
                    }
                    case (2): {
                        fm.beginTransaction().hide(active).show(fragment3).commit();
                        active = fragment3;
                        break;
                    }
                    default: {
                        Toast.makeText(context, "ohoh", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onEndTabSelected(String title, int index) {

            }
        });

        fm.beginTransaction().add(R.id.main_container, fragment1, "1").hide(fragment1).commit();
        fm.beginTransaction().add(R.id.main_container, fragment2, "2").commit();
        fm.beginTransaction().add(R.id.main_container, fragment3, "3").hide(fragment3).commit();

        navigationTabStrip.onPageSelected(1);
//        navigationTabStrip.setSelected(true);

    }

    public void projectChanged(){
        ((Projects) fragment2).changed();
    }
    public void tasksChanged(){
        ((Tasks) fragment3).changed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
