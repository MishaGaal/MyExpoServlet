package com.example.myexpo.util;


import java.util.List;
import java.util.function.Supplier;

public class Page<T> {
    List<T> expos;
    int count;


    public Page() {
    }


    public Page(List<T> expos, int count) {
        this.expos = expos;
        this.count = count;
    }

    public <X extends Throwable> Page<T> orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        if (expos != null) {
            return this;
        } else {
            throw exceptionSupplier.get();
        }
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
