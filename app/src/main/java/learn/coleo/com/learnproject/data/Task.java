package learn.coleo.com.learnproject.data;

import java.io.Serializable;

public class Task extends Work implements Serializable {

    private boolean Done;

    public Task(String name, int id,boolean Done) {
        super(name, id);
        this.Done = Done;
    }

    public void setDone(boolean done) {
        Done = done;
    }

    public boolean isDone() {
        return Done;
    }
}
