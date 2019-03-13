package learn.coleo.com.learnproject.data;

import java.io.Serializable;
import java.util.ArrayList;

public class ProjectPart extends Work implements Serializable {


    private int progressPart;

    public ProjectPart(String name, int id, int progressPart) {
        super(name, id);
        this.progressPart = progressPart;
    }

    public int getProgressPart() {
        return progressPart;
    }

    public void setProgressPart(int progressPart) {
        this.progressPart = progressPart;
    }
}
