package com.example.myexpo.util;


import java.util.List;

public class Page<T> {
    List<T> expos;
    int count;


    public Page() {
    }


    public Page(List<T> expos, int count) {
        this.expos = expos;
        this.count = count;
    }


    public List<T> getExpos() {
        return expos;
    }

    public void setExpos(List<T> expos) {
        this.expos = expos;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int size() {
        return count;
    }

}
