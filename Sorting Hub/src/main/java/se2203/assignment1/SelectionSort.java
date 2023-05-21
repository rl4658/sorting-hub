package se2203.assignment1;

import javafx.application.Platform;

public class SelectionSort extends SortingHubController implements SortingStrategy {
    //reference variable of array list - checked
    private final int[] list;
    //reference variable of SortingHubController class type - checked
    private final SortingHubController controller;

    //selection sort constructor
    public SelectionSort(int[] arr, SortingHubController sortingHubController) {
        this.list = arr;
        this.controller = sortingHubController;
    }

    //sort method that accepts int arrays - checked
    public void sort(int[] numbers) {
        try {
            //for (outerIndex ← 0 to outerIndex < n - 1)
            for (int i = 0; i < numbers.length - 1; i++) {
                //indexOfNextSmallest ← outerIndex
                int indexOfNextSmallest = i;
                //for (innerIndex ← outerIndex+1 to innerIndex < n - 1)
                for (int j = i + 1; j < numbers.length; j++) {
                    //if(list[innerIndex]<list[indexOfNextSmallest]
                    if (numbers[j] < numbers[indexOfNextSmallest]) {
                        //indexOfNextSmallest ← innerIndex
                        indexOfNextSmallest = j;
                    }
                }
                //temp ← list [outerIndex]
                int temp = numbers[i];
                //list [outerIndex] ← list [indexOfNextSmallest]
                numbers[i] = numbers[indexOfNextSmallest];
                //list [indexOfNextSmallest] ← temp
                numbers[indexOfNextSmallest] = temp;
                //run time lag and update the graph
                Platform.runLater(() -> controller.updateGraph(numbers));
                //stop compiling for 50 millis
                Thread.sleep(60);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    //method to run the class - checked
    public void run() {
        //sorts the list
        sort(list);
    }
}