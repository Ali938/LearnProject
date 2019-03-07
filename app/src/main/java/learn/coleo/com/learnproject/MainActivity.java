package learn.coleo.com.learnproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import learn.coleo.com.learnproject.adapters.ProjectAdapter;
import learn.coleo.com.learnproject.data.Project;
import learn.coleo.com.learnproject.data.ProjectPart;

public class MainActivity extends AppCompatActivity {

    private RecyclerView projectList;
    private ProjectAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        projectList = findViewById(R.id.recyclerList_projects_id);

        ArrayList<Project> projects = new ArrayList<>();
        projects.add(new Project("learn",0));
        projects.get(0).getParts().add(new ProjectPart("Android",11));
        projects.get(0).getParts().add(new ProjectPart("IOS",12));
        projects.get(0).getParts().add(new ProjectPart("DATA_BASE",13));
        projects.get(0).getParts().add(new ProjectPart("Design",14));
        projects.get(0).getParts().add(new ProjectPart("Server",15));
        projects.add(new Project("pasmand",1));
        projects.add(new Project("arosi",2));
        projects.add(new Project("abjo",3));
        projects.add(new Project("parkners",4));
        projects.add(new Project("temp project",5));
        projects.add(new Project("last project",6));
        projects.add(new Project("learn",0));
        projects.add(new Project("pasmand",1));
        projects.add(new Project("arosi",2));
        projects.add(new Project("abjo",3));
        projects.add(new Project("parkners",4));
        projects.add(new Project("temp project",5));
        projects.add(new Project("last project",6));
        projects.add(new Project("learn",0));
        projects.add(new Project("pasmand",1));
        projects.add(new Project("arosi",2));
        projects.add(new Project("abjo",3));
        projects.add(new Project("parkners",4));
        projects.add(new Project("temp project",5));
        projects.add(new Project("last project",6));

        adapter = new ProjectAdapter(this,projects);
        projectList.setAdapter(adapter);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        projectList.setLayoutManager(mLayoutManager);

    }
}
