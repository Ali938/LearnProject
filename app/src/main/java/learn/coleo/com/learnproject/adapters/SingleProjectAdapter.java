package learn.coleo.com.learnproject.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import learn.coleo.com.learnproject.R;
import learn.coleo.com.learnproject.SingleProjectDetails;
import learn.coleo.com.learnproject.data.ProjectPart;
import learn.coleo.com.learnproject.data.Task;
import learn.coleo.com.learnproject.data.Work;

public class SingleProjectAdapter extends RecyclerView.Adapter<SingleProjectAdapter.MyProjectPart> {

    class MyProjectPart extends RecyclerView.ViewHolder {
        TextView projectPartName;

        CheckBox taskCheckBox;

        TextView projectPartPercent;
        ProgressBar seekBarProgressProject;

        MyProjectPart(View itemView, int type) {
            super(itemView);
            if (type == PROJECT_PART) {
                projectPartName = itemView.findViewById(R.id.item_name_single_project_part);
                projectPartPercent = itemView.findViewById(R.id.progress_percent_text_view_single_project_part);
                seekBarProgressProject = itemView.findViewById(R.id.progressBar_progress_single_project);
            } else {
                projectPartName = itemView.findViewById(R.id.item_detail_title);
                taskCheckBox = itemView.findViewById(R.id.item_detail_checkbox);
            }

        }
    }

    private ArrayList<Work> works;
    private Context context;

    public SingleProjectAdapter(Context context, ArrayList<Work> projectParts) {
        this.works = projectParts;
        this.context = context;
    }

    private final int PROJECT_PART = 0;

    @Override
    public int getItemViewType(int position) {
        int TASK = 1;
        if (works.get(position) instanceof ProjectPart) {
            return PROJECT_PART;
        } else
            return TASK;
    }

    @NonNull
    @Override
    public MyProjectPart onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == PROJECT_PART) {
            View v = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.item_recycler_view_single_project_parts, parent, false);
            return new MyProjectPart(v, viewType);
        } else {
            View v = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.item_recycle_project_details, parent, false);
            return new MyProjectPart(v, viewType);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyProjectPart holder, int position) {
        final Work temp = works.get(position);
        if (temp instanceof ProjectPart) {
            ProjectPart part = (ProjectPart) temp;
            holder.projectPartName.setText(part.getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SingleProjectDetails.class);

                    context.startActivity(intent);
                }
            });

            holder.seekBarProgressProject.setProgress(part.getProgressPart());
            holder.projectPartPercent.setText(part.getProgressPart() + "%");
        } else {
            final Task task = (Task) temp;
            holder.projectPartName.setText(task.getName());
            holder.taskCheckBox.setChecked(task.isDone());
            holder.taskCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    task.setDone(isChecked);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return works.size();
    }

}
