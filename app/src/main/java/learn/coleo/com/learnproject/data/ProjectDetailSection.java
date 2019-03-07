package learn.coleo.com.learnproject.data;

import java.io.Serializable;

public class ProjectDetailSection implements Serializable {
    private boolean isDone;
    private String title;
    private int id;

    public ProjectDetailSection(String title, boolean isDone, int id){
        this.title = title;
        this.isDone = isDone;
        this.id = id;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public boolean isDone() {return isDone;}
    public void setDone(boolean done) {isDone = done;}
}
