package learn.coleo.com.learnproject.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import learn.coleo.com.learnproject.ProjectDetailActivity;
import learn.coleo.com.learnproject.data.ProjectDetailSection;

import java.util.ArrayList;

import learn.coleo.com.learnproject.R;

public class ProjectDetailAdapter extends RecyclerView.Adapter<ProjectDetailAdapter.DetailHolder> {

    class DetailHolder extends RecyclerView.ViewHolder {
        TextView title;
        CheckBox checkBox;

        public DetailHolder(View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.item_detail_checkbox);
            title = itemView.findViewById(R.id.item_detail_title);
        }
    }

    public ProjectDetailAdapter(Context context, ArrayList<ProjectDetailSection> list) {
        this.context = context;
        this.projectDetailSections = list;
    }

    private ArrayList<ProjectDetailSection> projectDetailSections;
    private Context context;

    @NonNull
    @Override
    public DetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DetailHolder(
                LayoutInflater.from(parent.getContext()).
                        inflate(R.layout.item_recycle_project_details, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull DetailHolder holder, int position) {
        final ProjectDetailSection t = projectDetailSections.get(position);
        holder.title.setText(t.getTitle());
        holder.checkBox.setChecked(t.isDone());
        holder.itemView.setOnClickListener((v) -> {
            Log.i("LOL LOL", "IT IS DETAIL ***** : "+t.getId());
            ((ProjectDetailActivity)context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, "Detail : " + t.getId(), Toast.LENGTH_SHORT).show();
                }
            });
        });

    }

    @Override
    public int getItemCount() {
        return projectDetailSections.size();
    }


}
