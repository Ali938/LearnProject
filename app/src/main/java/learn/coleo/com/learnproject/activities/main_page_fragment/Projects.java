package learn.coleo.com.learnproject.activities.main_page_fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import learn.coleo.com.learnproject.R;
import learn.coleo.com.learnproject.adapters.ProjectAdapter;
import learn.coleo.com.learnproject.data.Project;
import learn.coleo.com.learnproject.server.ServerClass;

/**
 * A simple {@link Fragment} subclass.
 */
public class Projects extends Fragment {


    private ProjectAdapter adapter;
    private ArrayList<Project> projects;

    public Projects() {
        projects = new ArrayList<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_projects, container, false);

        RecyclerView projectList = view.findViewById(R.id.recyclerList_projects_id);
        adapter = new ProjectAdapter(getContext(), projects);
        projectList.setAdapter(adapter);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        projectList.setLayoutManager(mLayoutManager);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ServerClass.getProjects(getContext(), projects);
    }

    public void changed() {
        adapter.notifyDataSetChanged();
    }
}
