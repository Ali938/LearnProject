package learn.coleo.com.learnproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

import learn.coleo.com.learnproject.adapters.SingleProjectAdapter;
import learn.coleo.com.learnproject.constants.Constants;
import learn.coleo.com.learnproject.data.Task;
import learn.coleo.com.learnproject.server.ServerClass;

public class SingleProjectDetails extends AppCompatActivity {

    private SingleProjectAdapter singleProjectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_project);

        Bundle extra = getIntent().getExtras();
        assert extra != null;
        int id = extra.getInt(Constants.SINGLE_PROJECT_ID);
        String name = extra.getString(Constants.SINGLE_PROJECT_NAME);
        RecyclerView singleProjectList = findViewById(R.id.recyclerList_single_projects_id);

        TextView projectName = findViewById(R.id.single_project_name_id);
        projectName.setText(name);

        ArrayList<Task> arrayList = new ArrayList<>();

        singleProjectAdapter = new SingleProjectAdapter(this, arrayList);
        singleProjectList.setAdapter(singleProjectAdapter);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        singleProjectList.setLayoutManager(mLayoutManager);

        ServerClass.getProject(this, id);

    }

    public void changed() {
        singleProjectAdapter.notifyDataSetChanged();
    }

}
