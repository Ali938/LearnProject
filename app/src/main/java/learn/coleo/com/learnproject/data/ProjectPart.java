package learn.coleo.com.learnproject.data;

import android.widget.Adapter;

import java.util.ArrayList;

public class ProjectPart {
    private String name;
    private int id;
    private int progressPart;

    public ProjectPart(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getProgressPart() {
        return progressPart;
    }

    public void setProgressPart(int progressPart) {
        this.progressPart = progressPart;
    }
}
