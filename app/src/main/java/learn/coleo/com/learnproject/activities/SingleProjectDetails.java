package learn.coleo.com.learnproject.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import learn.coleo.com.learnproject.R;
import learn.coleo.com.learnproject.adapters.SingleProjectAdapter;

public class SingleProjectDetails extends AppCompatActivity {

    private SingleProjectAdapter singleProjectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_project);

//        Bundle extra = getIntent().getExtras();
//        assert extra != null;
//        int id = extra.getInt(Constants.SINGLE_PROJECT_ID);
//        String name = extra.getString(Constants.SINGLE_PROJECT_NAME);
//        RecyclerView singleProjectList = findViewById(R.id.recyclerList_single_projects_id);
//
//        TextView projectName = findViewById(R.id.single_project_name_id);
//        projectName.setText(name);
//
//        ArrayList<Task> arrayList = new ArrayList<>();
//
//        singleProjectAdapter = new SingleProjectAdapter(this, arrayList);
//        singleProjectList.setAdapter(singleProjectAdapter);
//        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        singleProjectList.setLayoutManager(mLayoutManager);
//
//        ServerClass.getProject(this, id);

        PieChart chart = findViewById(R.id.chart);

        chart.setCenterText("city text");
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

    public void changed() {
        singleProjectAdapter.notifyDataSetChanged();
    }

}
