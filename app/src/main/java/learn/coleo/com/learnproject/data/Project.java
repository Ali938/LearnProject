package learn.coleo.com.learnproject.data;

import java.io.Serializable;
import java.util.ArrayList;

public class Project implements Serializable {

    private int id;
    private String name;
    private User admin;
    private int price;
    private ArrayList<Task> tasks;
    private Date create;
    private Date start;
    private Date finished;
    private int totalPoint;
    private int donePoint;

    public Project(int id, String name, User admin, int price, ArrayList<Task> tasks
            , Date create, Date start, Date finished, int totalPoint, int donePoint) {
        this.id = id;
        this.name = name;
        this.admin = admin;
        this.price = price;
        this.tasks = tasks;
        this.create = create;
        this.start = start;
        this.finished = finished;
        this.totalPoint = totalPoint;
        this.donePoint = donePoint;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public void setFinished(Date finished) {
        this.finished = finished;
    }

    public Date getCreate() {
        return create;
    }

    public Date getStart() {
        return start;
    }

    public Date getFinished() {
        return finished;
    }

    public Project(int id, String name, int totalPoint, int donePoint) {
        this.id = id;
        this.name = name;
        this.totalPoint = totalPoint;
        this.donePoint = donePoint;
    }

    public int getMaxPercent() {
        return 100;
    }

    public int getDonePercent() {
        if (totalPoint <= 0) {
            return 5;
        } else {
            if (donePoint <= 0) {
                return 5;
            }
            return (totalPoint / donePoint);
        }
    }

    public void setTotalPoint(int totalPoint) {
        this.totalPoint = totalPoint;
    }

    public void setDonePoint(int donePoint) {
        this.donePoint = donePoint;
    }

    public int getTotalPoint() {
        return totalPoint;
    }

    public int getDonePoint() {
        return donePoint;
    }

    public Project(int id, String name, User admin, int price) {
        this.id = id;
        this.name = name;
        this.admin = admin;
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User getAdmin() {
        return admin;
    }

    public int getPrice() {
        return price;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
