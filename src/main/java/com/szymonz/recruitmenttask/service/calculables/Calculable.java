package com.szymonz.recruitmenttask.service.calculables;

public interface Calculable<T> {
    T getValue() throws Exception;
}
