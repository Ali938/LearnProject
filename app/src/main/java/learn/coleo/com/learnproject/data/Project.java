package learn.coleo.com.learnproject.data;

import java.io.Serializable;
import java.util.ArrayList;

public class Project implements Serializable {

    private int id;
    private String name;
    private User admin;
    private int price;
    private ArrayList<Task> tasks;

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
