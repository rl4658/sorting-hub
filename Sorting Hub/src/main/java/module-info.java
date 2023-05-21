module se2203.assignment1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens se2203.assignment1 to javafx.fxml;
    exports se2203.assignment1;
}