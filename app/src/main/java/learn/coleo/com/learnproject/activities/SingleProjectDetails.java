package learn.coleo.com.learnproject.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import learn.coleo.com.learnproject.R;
import learn.coleo.com.learnproject.constants.Constants;
import learn.coleo.com.learnproject.data.Project;
import learn.coleo.com.learnproject.data.Task;
import learn.coleo.com.learnproject.server.ServerClass;

public class SingleProjectDetails extends AppCompatActivity {

    private TextView name;
    private TextView startDate;
    private TextView admin;
    private TextView description;
    private Button showTasks;
    private PieChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_project);

        name = findViewById(R.id.single_project_name_id);
        startDate = findViewById(R.id.start_date_single_project);
        admin = findViewById(R.id.admin_text_view);
        description = findViewById(R.id.single_project_description);
        showTasks = findViewById(R.id.task_of_single_projects_id);
        chart = findViewById(R.id.chart);
        Bundle extra = getIntent().getExtras();
        int id = extra.getInt(Constants.SINGLE_PROJECT_ID);

        ServerClass.getProject(this, id);

        makeChart();
    }

    public void changed(final Project project) {
        name.setText(project.getName());
        startDate.setText(project.getStart().getStringDate());
        admin.setText(project.getAdmin().getName());
//        description.setText(project);
        final Context context = this;
        chart.setCenterText(project.getName());
        showTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTasks.setEnabled(false);
                ServerClass.getProjectTasks(context,project.getId(),new ArrayList<Task>());
            }
        });
    }

    public void goTaskPage(ArrayList<Task> tasks){
        showTasks.setEnabled(true);
        Intent intent = new Intent(this, ProjectTaskActivity.class);
        intent.putExtra(Constants.SINGLE_PROJECT_DATA,tasks);
        startActivity(intent);
    }

    private void makeChart() {


        ArrayList<PieEntry> list = new ArrayList<>();
        list.add(new PieEntry(10, "abbas"));
        list.add(new PieEntry(20, "mmd"));
        list.add(new PieEntry(10, "reza"));
        list.add(new PieEntry(20, "kazem"));
        list.add(new PieEntry(50, "morteza"));
        PieDataSet dataSet = new PieDataSet(list, "temp chart");
        dataSet.setSliceSpace(5f);
        ArrayList<Integer> colors = new ArrayList<>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        chart.setUsePercentValues(true);
        PieData pieData = new PieData(dataSet);
        chart.setData(pieData);
        chart.notifyDataSetChanged();
    }

}
