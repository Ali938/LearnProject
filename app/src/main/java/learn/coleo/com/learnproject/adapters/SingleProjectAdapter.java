package learn.coleo.com.learnproject.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
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
import learn.coleo.com.learnproject.data.Task;

public class SingleProjectAdapter extends RecyclerView.Adapter<SingleProjectAdapter.MyProjectPart> {

    class MyProjectPart extends RecyclerView.ViewHolder {

        MyProjectPart(View itemView, int type) {
            super(itemView);
        }
    }

    private ArrayList<Task> tasks;
    private Context context;

    public SingleProjectAdapter(Context context, ArrayList<Task> projectParts) {
        this.tasks = projectParts;
        this.context = context;
    }

    @NonNull
    @Override
    public MyProjectPart onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.item_recycler_view_single_project_parts, parent, false);
        return new MyProjectPart(v, viewType);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyProjectPart holder, int position) {

    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

}
