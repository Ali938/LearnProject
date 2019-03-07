package learn.coleo.com.learnproject.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import learn.coleo.com.learnproject.R;
import learn.coleo.com.learnproject.data.ProjectPart;

public class SingleProjectAdapter extends RecyclerView.Adapter<SingleProjectAdapter.MyProjectHolder> {

    class MyProjectHolder extends RecyclerView.ViewHolder{
        TextView projectPartName;
        TextView projectPartPercent;
        ProgressBar seekBarProgressProject;
        public MyProjectHolder(View itemView) {
            super(itemView);
            projectPartPercent = itemView.findViewById(R.id.progress_percent_text_view_single_project_part);
            projectPartName = itemView.findViewById(R.id.item_name_single_project_part);
            seekBarProgressProject=itemView.findViewById(R.id.progressBar_progress_single_project);
        }
    }

    private ArrayList<ProjectPart> projectParts;
    private Context context;

    public SingleProjectAdapter(Context context, ArrayList<ProjectPart> projectParts) {
        this.projectParts = projectParts;
        this.context = context;
    }

    @NonNull
    @Override
    public MyProjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_recycler_view_single_project_parts, parent, false);
        return new MyProjectHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyProjectHolder holder, int position) {
        final ProjectPart temp = projectParts.get(position);
        holder.projectPartName.setText(temp.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.seekBarProgressProject.setProgress(temp.getProgressPart());
        holder.projectPartPercent.setText(temp.getProgressPart() + "%");
    }

    @Override
    public int getItemCount() {
        return projectParts.size();
    }

}
