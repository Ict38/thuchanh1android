package com.example.realthuchanh.model;

public class DuAn {
    private String name,st,dl;
    private boolean finish;

    public DuAn() {
    }

    public DuAn(String name, String st, String dl, boolean finish) {
        this.name = name;
        this.st = st;
        this.dl = dl;
        this.finish = finish;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public String getDl() {
        return dl;
    }

    public void setDl(String dl) {
        this.dl = dl;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }
}
