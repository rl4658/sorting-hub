package se2203.assignment1;

import javafx.application.Platform;

public class MergeSort extends SortingHubController implements SortingStrategy {
    //reference variable of array list - checked
    private final int[] list;
    //reference variable of SortingHubController class type - checked
    private final SortingHubController controller;

    //merge sort constructor
    public MergeSort(int[] arr, SortingHubController sortingHubController) {
        this.list = arr;
        this.controller = sortingHubController;
    }

    //sort method that accepts int arrays - checked
    public void sort(int[] numbers) {
        //calls merge sort method
        mergeSort(numbers, 0, numbers.length - 1);
    }

    @Override
    //method to run the class - checked
    public void run() {
        //sorts the list
        sort(list);
    }

    //left index and right index of the sub-array of array to be sorted
    public void mergeSort(int[] arraySize, int l, int r) {
        if (l < r) {
            //(left + right) / 2
            //for large l and r
            int m = l + (r - l) / 2;
            //sorts first and second halves recursively
            mergeSort(arraySize, l, m);
            mergeSort(arraySize, m + 1, r);
            //calls in place merge method
            mergeInPlace(arraySize, l, m, r);
        }
    }

    //in place merge sort that accepts int array, left, mid, and right
    public void mergeInPlace(int[] array, int l, int m, int r) {
        try {
            //have another start variable starting from index 1 after mid split
            int left2 = m + 1;
            //if the direct merge is already sorted, return that merge
            if (array[m] <= array[left2]) {
                return;
            }
            //two pointers to maintain start of both arrays to merge
            while (l <= m && left2 <= r) {
                //if element 1 is in right place, increase left index
                if (array[l] <= array[left2]) {
                    l++;

                } else {
                    //else, replace the integers in the array
                    int value = array[left2];
                    int index = left2;
                    //shift all the elements between element 1 and element 2, right by 1.
                    while (index != l) {
                        //while index is not left, replace index with left
                        array[index] = array[index - 1];
                        index--;
                    }
                    //add the left values into the array
                    array[l] = value;
                    //run time lag and update the graph
                    Platform.runLater(() -> controller.updateGraph(array));
                    //stop compiling for 50 millis
                    Thread.sleep(50);
                    //updates all the indexes
                    l++;
                    m++;
                    left2++;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}