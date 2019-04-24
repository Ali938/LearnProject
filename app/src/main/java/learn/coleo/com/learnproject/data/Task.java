package learn.coleo.com.learnproject.data;

import java.io.Serializable;
import java.util.ArrayList;

public class Task implements Serializable {

    private String title;
    private String description;
    private User owner;
    private int point;
    private int volume;
    private Date deadLine;
    private Date created;
    private Date updated;
    private Date closed;
    private ArrayList<Task> children;


}
