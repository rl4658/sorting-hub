package se2203.assignment1;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.*;

public class SortingHubController implements Initializable {
    //reference variable for array of rectangles - checked
    private int[] intArray;
    //reference variable for which sorting to use - checked
    private SortingStrategy sortingMethod;
    @FXML
    //reference variable for pane
    private Pane sortingStage;
    @FXML
    //reference variable label for array size
    private Label arraySize;
    @FXML
    //reference variable for slider
    private Slider sliderScale;
    @FXML
    //reference variable for combobox
    private ComboBox<String> comboBox;
    @FXML
    //reference variable for reset button
    private Button reset;
    @FXML
    //reference variable for sorting button
    private Button sorting;

    //METHOD TO SET THE SORT STRATEGY - checked
    public void setSortStrategy() {
        //if merge sort is selected
        if (comboBox.getSelectionModel().getSelectedItem().equals("Merge Sort")) {
            //call the sort method from the merge sort class
            sortingMethod = new MergeSort(intArray, this);
            //else if selection sort is selected
        } else if (comboBox.getSelectionModel().getSelectedItem().equals("Selection Sort")) {
            //call the sort method from the selection sort class
            sortingMethod = new SelectionSort(intArray, this);
        }
    }

    //METHOD TO CREATE RECTANGLES AND UPDATE GRAPH - checked
    public void updateGraph(int[] data) {
        //clear the pane
        sortingStage.getChildren().clear();
        //create rectangle objects as big as the array length
        Rectangle[] rectangle = new Rectangle[data.length];
        //create spacings variable
        int spacings = data.length - 1;
        //get the width of the rectangles with spacings left
        double width = (766.0 - spacings) / data.length;
        //for every index in the array
        for (int i = 0; i < data.length; i++) {
            //create a new rectangle with the integer
            rectangle[i] = new Rectangle();
            //set every height proportional to the index array size to the height of Pane
            rectangle[i].setHeight(intArray[i] / (double) data.length * 327.25);
            //set the width to the variable
            rectangle[i].setWidth(width);
            //set the x pointer of the rectangle
            rectangle[i].setX(i * width + i + 1.15);
            //set the y pointer of the rectangle
            rectangle[i].setY((327 - intArray[i] / (double) data.length * 327) + 1.5);
            //set the color of rectangle to red
            rectangle[i].setFill(Color.RED);
            //add all the rectangle to the Pane, size: 768*329
            sortingStage.getChildren().add(rectangle[i]);
        }
    }

    @Override
    //METHOD THAT INITIALIZES THE STAGE WHEN COMPILED
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //SLIDER AND LABEL INITIALIZATION
        sliderScale.setValue(64);
        arraySize.setText(String.valueOf(64));
        //Create an ArrayList of Strings.
        ArrayList<String> strArrayList = new ArrayList<>();
        strArrayList.add("Merge Sort");
        strArrayList.add("Selection Sort");
        //Convert the ArrayList to an ObservableList.
        ObservableList<String> strList = FXCollections.observableArrayList(strArrayList);
        //Populate the ListView control:\
        comboBox.getItems().setAll(strList);
        //COMBOBOX INITIALIZATION
        comboBox.setValue("Merge Sort");
        //call the method to set the sorting method
        setSortStrategy();
        //CREATE UNSORTED ARRAY FROM 1 to 64 and update the graph
        unsortedArray(64);
        updateGraph(intArray);
    }

    @FXML
        //slider method
    void detectSlider() {
        //variable to get value
        int arrayLabel = (int) sliderScale.getValue();
        //set the label to the array size of slider
        arraySize.setText(String.valueOf(arrayLabel));
        //un-sort the variable of detected array size
        unsortedArray(arrayLabel);
        //update the graph everytime the slider changes array size
        updateGraph(intArray);
    }

    @FXML
        //sort button method
    void sortButton() {
        //call the method to set the sorting method
        setSortStrategy();
        //create new thread for sorting method
        Thread thread = new Thread(sortingMethod);
        //run the thread object
        thread.start();
    }

    @FXML
        //reset button method
    void resetButton() {
        //set the slider value to 64
        sliderScale.setValue(64);
        //set the label to 64
        arraySize.setText(String.valueOf(64));
        //set the combobox to merge sort
        comboBox.setValue("Merge Sort");
        //un-sort the array with array size 64
        unsortedArray(64);
        //update the graph with the array
        updateGraph(intArray);
    }

    //un-sort random array method
    public void unsortedArray(int arraySize) {
        //create random reference variable
        Random random = new Random();
        //create a linked set integer variable
        Set<Integer> generator = new LinkedHashSet<>();
        //while the set size is less than the array length
        while (generator.size() < arraySize) {
            //create a random integer
            int rng = random.nextInt(arraySize) + 1;
            //add the integer to the set
            generator.add(rng);
        }
        //set reference array to the random set integers
        intArray = new int[generator.size()];
        int j = 0;
        //for every integer in the random set
        for (Integer i : generator) {
            //add it to the integer array
            intArray[j++] = i;
        }
    }
}