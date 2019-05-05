package learn.coleo.com.learnproject.activities.main_page_fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import learn.coleo.com.learnproject.R;
import learn.coleo.com.learnproject.adapters.TaskAdapter;
import learn.coleo.com.learnproject.constants.Constants;
import learn.coleo.com.learnproject.data.Task;
import learn.coleo.com.learnproject.server.ServerClass;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tasks extends Fragment {
    private boolean showMine = false;

    private TaskAdapter adapter;
    private ArrayList<Task> tasks;
    private ArrayList<Task> mine;

    public Tasks() {
        tasks = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dead_line, container, false);

        RecyclerView projectList = view.findViewById(R.id.task_list);
        adapter = new TaskAdapter(tasks, getContext());
        projectList.setAdapter(adapter);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        projectList.setLayoutManager(mLayoutManager);

        final ImageView mine = view.findViewById(R.id.mine_show);
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

        return view;
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

    @Override
    public void onResume() {
        super.onResume();
        ServerClass.getTasks(getContext(),tasks);
    }

    public void changed() {
        mine = Constants.getUserTask(tasks, Constants.MINE);
        adapter.notifyDataSetChanged();
    }

}
