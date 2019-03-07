package learn.coleo.com.learnproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import learn.coleo.com.learnproject.adapters.ProjectPartsAdapter;
import learn.coleo.com.learnproject.data.Project;
import learn.coleo.com.learnproject.data.ProjectPart;

public class projectpartsActivity extends AppCompatActivity {

    private RecyclerView projectParts;
    private ProjectPartsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_parts);
        projectParts = findViewById(R.id.recyclerList_projects_parts);

        ArrayList<ProjectPart> parts = new ArrayList<>();

        ProjectPart projectPart1=new ProjectPart("d",1);
        projectPart1.setProgressPart(100);

        ProjectPart projectPart2=new ProjectPart("dgfg",2);
        projectPart2.setProgressPart(0);

        ProjectPart projectPart3=new ProjectPart("dfdsfssdfd",3);
        projectPart3.setProgressPart(50);

        ProjectPart projectPart4=new ProjectPart("dssssssss",4);
        projectPart4.setProgressPart(90);

        ProjectPart projectPart5=new ProjectPart("dad",5);
        projectPart5.setProgressPart(10);

        ProjectPart projectPart6=new ProjectPart("dzdv",6);
        projectPart6.setProgressPart(20);

        ProjectPart projectPart7=new ProjectPart("d,k",7);
        projectPart7.setProgressPart(30);

        ProjectPart projectPart8=new ProjectPart("d,k",8);
        projectPart8.setProgressPart(30);

        ProjectPart projectPart9=new ProjectPart("d,k",9);
        projectPart9.setProgressPart(30);

        ProjectPart projectPart10=new ProjectPart("d,k",10);
        projectPart10.setProgressPart(30);

        ProjectPart projectPart11=new ProjectPart("d,k",11);
        projectPart11.setProgressPart(30);

        ProjectPart projectPart12=new ProjectPart("d,k",12);
        projectPart12.setProgressPart(30);

        ProjectPart projectPart13=new ProjectPart("d,k",13);
        projectPart13.setProgressPart(30);

        ProjectPart projectPart14=new ProjectPart("ctryugjgjjvh",14);
        projectPart14.setProgressPart(30);

        parts.add(projectPart1);
        parts.add(projectPart2);
        parts.add(projectPart3);
        parts.add(projectPart4);
        parts.add(projectPart5);
        parts.add(projectPart6);
        parts.add(projectPart7);
        parts.add(projectPart8);
        parts.add(projectPart9);
        parts.add(projectPart10);
        parts.add(projectPart11);
        parts.add(projectPart12);
        parts.add(projectPart13);
        parts.add(projectPart14);
        adapter = new ProjectPartsAdapter(this,parts);
        projectParts.setAdapter(adapter);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        projectParts.setLayoutManager(mLayoutManager);

    }
}
