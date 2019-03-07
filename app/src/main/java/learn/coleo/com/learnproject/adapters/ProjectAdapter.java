package learn.coleo.com.learnproject.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import learn.coleo.com.learnproject.R;
import learn.coleo.com.learnproject.data.Project;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.MyProjectHolder> {

    class MyProjectHolder extends RecyclerView.ViewHolder{
        TextView projectName;
        public MyProjectHolder(View itemView) {
            super(itemView);
            projectName = itemView.findViewById(R.id.item_name_project);
        }
    }

    private ArrayList<Project> projects;
    private Context context;
    public ProjectAdapter(Context context, ArrayList<Project> projects) {
        this.projects = projects;
        this.context = context;
    }

    @NonNull
    @Override
    public MyProjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_recycler_view_main, parent, false);
        return new MyProjectHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyProjectHolder holder, int position) {
        final Project temp = projects.get(position);
        holder.projectName.setText(temp.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"id : " + temp.getId(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }
}
