package learn.coleo.com.learnproject;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import learn.coleo.com.learnproject.adapters.ProjectAdapter;
import learn.coleo.com.learnproject.data.Project;
import learn.coleo.com.learnproject.server.ServerClass;

public class MainActivity extends AppCompatActivity {

    private ProjectAdapter adapter;
    public static String USERNAME_DATA = "user from login";
    private final Context context = this;
    private final Activity activity = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Bundle extra = getIntent().getExtras();
        String username = extra.getString(USERNAME_DATA,"not found");
        TextView usernameText = findViewById(R.id.username_TextView_id);
        usernameText.setText(username);

        RecyclerView projectList = findViewById(R.id.recyclerList_projects_id);

        ArrayList<Project> projects = new ArrayList<>();

        adapter = new ProjectAdapter(this, projects);
        projectList.setAdapter(adapter);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        projectList.setLayoutManager(mLayoutManager);

        ServerClass.getProjects(this, projects);

    }

    public void changed() {
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        final ImageView profile = findViewById(R.id.profile_imageView_id);
        final AnimatedVectorDrawable drawable = (AnimatedVectorDrawable) this.getDrawable(R.drawable.menu_icon_animatable);
        profile.setImageDrawable(drawable);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            drawable.reset();
        }
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawable.start();
//                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity,
//                        profile, getString(R.string.profile_icon_transition));
                final Intent intent = new Intent(context, ProfileActivity.class);
//                startActivity(intent, options.toBundle());
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(intent);
                    }
                },500);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
