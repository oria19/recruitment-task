package com.szymonz.recruitmenttask.service.calculators;


public interface Calculator<T> {

    T calculate(T num1, T num2);
}
