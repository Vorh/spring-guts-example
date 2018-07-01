package com.vorh.spring.bpp.screensaver;

/**
 * Created by vorh on 7/1/18.
 */
public class Pair<T, T1> {
    private T key;
    private T1 value;

    public Pair(T key, T1 value) {
        this.key = key;
        this.value = value;
    }

    public T getKey() {
        return key;
    }

    public T1 getValue() {
        return value;
    }
}
