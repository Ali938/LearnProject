package learn.coleo.com.learnproject.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import learn.coleo.com.learnproject.R;
import learn.coleo.com.learnproject.adapters.TaskAdapter;
import learn.coleo.com.learnproject.constants.Constants;
import learn.coleo.com.learnproject.data.Task;

public class ProjectTaskActivity extends AppCompatActivity {

    private boolean showMine = false;
    private TaskAdapter adapter;
    private ArrayList<Task> tasks;
    private ArrayList<Task> mine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dead_line);

        Bundle extra = getIntent().getExtras();
        tasks = (ArrayList<Task>) extra.getSerializable(Constants.SINGLE_PROJECT_DATA);
        mine = Constants.getUserTask(tasks, Constants.MINE);

        RecyclerView list = findViewById(R.id.task_list);
        adapter = new TaskAdapter(tasks, this);
        list.setAdapter(adapter);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(mLayoutManager);

        final ImageView mine = findViewById(R.id.mine_show);
        mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!showMine) {
                    mine.setImageResource(R.mipmap.mine_selected);
                } else {
                    mine.setImageResource(R.mipmap.mine);
                }
                updateTasks();
            }
        });
    }

    private void updateTasks() {
        showMine = !showMine;
        if (showMine) {
            adapter.setTasks(mine);
        } else {
            adapter.setTasks(tasks);
        }
        adapter.notifyDataSetChanged();
    }

}
