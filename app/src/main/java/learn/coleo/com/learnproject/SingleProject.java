package learn.coleo.com.learnproject;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import learn.coleo.com.learnproject.adapters.ProjectAdapter;
import learn.coleo.com.learnproject.adapters.SingleProjectAdapter;
import learn.coleo.com.learnproject.data.Project;

public class SingleProject extends AppCompatActivity {

    private TextView projectName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_project);

        Bundle extra = getIntent().getExtras();
        Project project = (Project) extra.getSerializable(Constants.SINGLE_PROJECT_DATA);
        RecyclerView singleProjectList = findViewById(R.id.recyclerList_single_projects_id);

        projectName = findViewById(R.id.single_project_name_id);
        projectName.setText(project.getName());

        SingleProjectAdapter singleProjectAdapter = new SingleProjectAdapter(this,project.getParts());
        singleProjectList.setAdapter(singleProjectAdapter);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        singleProjectList.setLayoutManager(mLayoutManager);


    }
}
