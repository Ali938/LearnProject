package learn.coleo.com.learnproject.data;

import java.io.Serializable;
import java.util.ArrayList;

public class Task implements Serializable {

    private int id;
    private String title;
    private String description;
    private User owner;
    private int point;
    private int volume;
    private int donePoint;
    private Date deadLine;
    private Date created;
    private Date updated;
    private Date closed;
    private ArrayList<Task> children;
    private int parentId = -1;

    public Task(int id, String title, String description, User owner, int point, int volume,
                Date deadLine, Date created, Date updated, Date closed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.owner = owner;
        this.point = point;
        this.volume = volume;
        this.deadLine = deadLine;
        this.created = created;
        this.updated = updated;
        this.closed = closed;
        this.children = new ArrayList<>();
    }

    public Task(int id, String title, String description, User owner) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.owner = owner;
    }

    public void addChild(Task child) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(child);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public void setClosed(Date closed) {
        this.closed = closed;
    }

    public void setChildren(ArrayList<Task> children) {
        this.children = children;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public User getOwner() {
        return owner;
    }

    public int getPoint() {
        return point;
    }

    public int getVolume() {
        return volume;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }

    public Date getClosed() {
        return closed;
    }

    public ArrayList<Task> getChildren() {
        return children;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public void setDonePoint(int donePoint) {
        this.donePoint = donePoint;
    }

    public int getDonePoint() {
        return donePoint;
    }
}
