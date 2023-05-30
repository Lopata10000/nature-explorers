module com.fanta.natureexplorers {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.fanta.natureexplorers to javafx.fxml;
    exports com.fanta.natureexplorers;
}