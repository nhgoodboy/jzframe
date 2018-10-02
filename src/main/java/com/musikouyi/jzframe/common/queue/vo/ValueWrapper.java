package com.musikouyi.jzframe.common.queue.vo;

/**
 * Create with IDEA
 * Author: YJZ
 * DateTime: 2018/10/1 21:06
 **/
public class ValueWrapper<T> {

    private T value;

    public ValueWrapper() {
    }

    public ValueWrapper(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
