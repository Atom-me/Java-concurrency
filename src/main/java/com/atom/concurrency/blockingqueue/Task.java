package com.atom.concurrency.blockingqueue;

/**
 * @author Atom
 */
public class Task implements Comparable<Task> {

    private Integer taskId;
    private String taskName;


    public Task(int taskId, String taskName) {
        this.taskId = taskId;
        this.taskName = taskName;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                '}';
    }

    @Override
    public int compareTo(Task o) {
        return this.taskId.compareTo(o.getTaskId());
    }
}
