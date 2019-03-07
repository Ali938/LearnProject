package learn.coleo.com.learnproject.data;

import java.io.Serializable;
import java.util.ArrayList;

public class Project implements Serializable {

    private String name;
    private int id;
    private ArrayList<ProjectPart> parts;

    public Project(String name, int id) {
        this.name = name;
        this.id = id;
        parts = new ArrayList<>();
    }

    public ArrayList<ProjectPart> getParts() {
        return parts;
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

}
