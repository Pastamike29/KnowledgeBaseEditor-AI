module com.example.knowledge_basejavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.j;
    requires java.sql;

    opens com.example.knowledge_basejavafx to javafx.fxml;
    exports com.example.knowledge_basejavafx;
    exports com.example.knowledge_basejavafx.Model;
    opens com.example.knowledge_basejavafx.Model to javafx.fxml;
    exports com.example.knowledge_basejavafx.Connection;
    opens com.example.knowledge_basejavafx.Connection to javafx.fxml;
}