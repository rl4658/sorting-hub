package se2203.assignment1;

import java.lang.Runnable;

public interface SortingStrategy extends Runnable {
    //operations - checked
    void sort(int[] numbers);
}