package learn.coleo.com.learnproject.activities;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;

import learn.coleo.com.learnproject.R;
import learn.coleo.com.learnproject.constants.Constants;
import learn.coleo.com.learnproject.data.Date;
import learn.coleo.com.learnproject.data.Task;
import learn.coleo.com.learnproject.server.ServerClass;

public class SingleTaskActivity extends AppCompatActivity {

    private TextView start, end, now;
    private TextView totalPoint, donePoint, volume;
    private TextView name, description;
    private RoundCornerProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_task);

        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt(Constants.TASK_ID_DATA);

        ServerClass.getTask(this,id);

        name = findViewById(R.id.taskName);
        description = findViewById(R.id.descriptionTextView);
        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        float progress = 0.2f;

        progressBar = findViewById(R.id.timesProgressBar);
        float temp = Constants.getHeightOfScreen(this) - Constants.dpToPx(this, 220f);
        progressBar.getLayoutParams().width = (int) (temp * 0.85);
        progressBar.getLayoutParams().height = (int) (Constants.dpToPx(this, 50));
        progressBar.setMax(1);
        progressBar.setProgress(progress);

        start = findViewById(R.id.start);
        now = findViewById(R.id.now);
        end = findViewById(R.id.end);

        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) now.getLayoutParams();
        params.verticalBias = progress;

        totalPoint = findViewById(R.id.pointValue);
        donePoint = findViewById(R.id.closedValue);
        volume = findViewById(R.id.volumeValue);

    }

    public void update(Task task, Date now) {
        float temp = task.getDeadLine().getDifrence(task.getCreated());
        progressBar.setMax(temp);
//        float nowDif = task.getDeadLine().getDifrence(now);
//        progressBar.setProgress(temp);
//        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) this.now.getLayoutParams();
//        params.verticalBias = nowDif / temp;
        start.setText(task.getCreated().getStringDate());
        end.setText(task.getDeadLine().getStringDate());
        totalPoint.setText("" + task.getPoint());
        volume.setText("" + task.getVolume());
        donePoint.setText("" + task.getDonePoint());
        name.setText(task.getTitle());
        description.setText(task.getDescription());

    }

}
