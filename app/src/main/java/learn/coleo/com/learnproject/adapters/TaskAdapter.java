package learn.coleo.com.learnproject.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import learn.coleo.com.learnproject.R;
import learn.coleo.com.learnproject.activities.ProjectTaskActivity;
import learn.coleo.com.learnproject.activities.SingleTaskActivity;
import learn.coleo.com.learnproject.constants.Constants;
import learn.coleo.com.learnproject.data.Task;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyTaskHolder> {

    private ArrayList<Task> tasks;
    private Context context;

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskAdapter(ArrayList<Task> tasks, Context context) {
        this.tasks = tasks;
        this.context = context;
    }

    @NonNull
    @Override
    public MyTaskHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.item_recycler_view_task, viewGroup, false);
        return new MyTaskHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyTaskHolder myTaskHolder, int i) {
        final Task temp = tasks.get(i);
        myTaskHolder.owner.setText(temp.getOwner().getName());
        myTaskHolder.taskName.setText(temp.getTitle());
        myTaskHolder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (temp.getChildren().isEmpty()) {
                    intent = new Intent(context, SingleTaskActivity.class);
                    intent.putExtra(Constants.TASK_ID_DATA, temp.getId());
                } else {
                    intent = new Intent(context, ProjectTaskActivity.class);
                    intent.putExtra(Constants.SINGLE_PROJECT_DATA, temp.getChildren());
                }
                context.startActivity(intent);

            }
        });
    }


    @Override
    public int getItemCount() {
        return tasks.size();
    }

    class MyTaskHolder extends RecyclerView.ViewHolder {
        TextView taskName;
        TextView owner;
        ImageView details;

        MyTaskHolder(View itemView) {
            super(itemView);
            taskName = itemView.findViewById(R.id.task_name_text_view_id);
            owner = itemView.findViewById(R.id.task_owner_id);
            details = itemView.findViewById(R.id.more_info_button);
        }
    }


}
