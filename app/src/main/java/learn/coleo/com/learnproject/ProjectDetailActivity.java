package learn.coleo.com.learnproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

import learn.coleo.com.learnproject.adapters.ProjectDetailAdapter;
import learn.coleo.com.learnproject.data.ProjectDetailSection;

public class ProjectDetailActivity extends AppCompatActivity {

    private TextView title;
    private RecyclerView list;
    private ProjectDetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);
        title = findViewById(R.id.project_detail_title);
        list = findViewById(R.id.project_detail_list);
        ArrayList<ProjectDetailSection> detailSections = new ArrayList<>();
        for (int i = 0; i <= 15; i++) {
            detailSections.add(new ProjectDetailSection("Detail #" + i, i % 2 == 0, i));
        }
        adapter = new ProjectDetailAdapter(this, detailSections);
        list.setAdapter(adapter);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        list.setLayoutManager(mLayoutManager);

    }
}
