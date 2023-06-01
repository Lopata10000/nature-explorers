module com.fanta.natureexplorers {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires java.sql;
    requires org.flywaydb.core;
    requires com.zaxxer.hikari;
    requires java.validation;
    requires java.persistence;
    requires com.jfoenix;
    requires javafx.media;
    requires jjwt.api;

    exports com.fanta.natureexplorers.controller.main to
            javafx.fxml;
    exports com.fanta.natureexplorers.controller.authentication to
            javafx.fxml;
    exports com.fanta.natureexplorers.controller.tours to
            javafx.fxml;
    exports com.fanta.natureexplorers;
    exports com.fanta.natureexplorers.enumrole;
    exports com.fanta.natureexplorers.controller.tablecontroller to
            javafx.fxml;
    exports com.fanta.natureexplorers.validator;

    opens com.fanta.natureexplorers.controller.tablecontroller to
            javafx.fxml;
    opens com.fanta.natureexplorers to
            javafx.fxml;
    opens db.migration;
    opens com.fanta.natureexplorers.entity;
    opens com.fanta.natureexplorers.enumrole to
            javafx.fxml;
    opens com.fanta.natureexplorers.controller.authentication to
            javafx.fxml;
    opens com.fanta.natureexplorers.controller.tours to
            javafx.fxml;
    opens com.fanta.natureexplorers.validator to
            javafx.fxml;
    opens com.fanta.natureexplorers.controller.main to
            javafx.fxml;
}
