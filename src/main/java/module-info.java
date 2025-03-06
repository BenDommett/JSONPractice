module JSONPractice {
    requires com.fasterxml.jackson.databind;
    requires javafx.controls;
    exports JSON to com.fasterxml.jackson.databind;
    opens JSON to javafx.graphics;
    exports frontEnd to com.fasterxml.jackson.databind;
    opens frontEnd to javafx.graphics;
}