package solent.ac.uk;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class MainApp extends Application {

    private static final Logger log = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {

        log.info("Starting Hello JavaFX and Maven demonstration application");
        /*
        String fxmlFile = "/fxml/hello.fxml";
        log.debug("Loading FXML for main view from: {}", fxmlFile);
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));

        log.debug("Showing JFX scene");
        Scene scene = new Scene(rootNode, 400, 200);
        scene.getStylesheets().add("/styles/styles.css");

        stage.setTitle("Hello JavaFX and Maven");
        stage.setScene(scene);
        stage.show();
         */
// http://www.java2s.com/Tutorials/Java/JavaFX_How_to/GridPane/Layout_number_pad_with_GridPane.htm
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 180, 250);

        StringBuffer cardString = new StringBuffer();

        Text message = new Text(25, 25, "enter amount");

        message.setId("message");

        String[] keys
                = {
                    "1", "2", "3",
                    "4", "5", "6",
                    "7", "8", "9",
                    "*", "0", "#"
                };

        class NumericButtonAction implements EventHandler<ActionEvent> {
            String str = null;
            NumericButtonAction(String s) {
                str = s;
            }
            @Override
            public void handle(ActionEvent e) {
                cardString.append(str);
                message.setText(cardString.toString());
            }
       }
 
        GridPane numPad = new GridPane();
        for (int i = 0; i < 12; i++) {
            Button button = new Button(keys[i]);
            button.getStyleClass().add("num-button");
            numPad.add(button, i % 3, (int) Math.ceil(i / 3));
            button.setOnAction(new NumericButtonAction(keys[i]));
        }

        GridPane controls = new GridPane();
        Button enter = new Button("Enter");
        enter.setId("enter-button");
        enter.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        controls.add(enter, 2, 0);

        enter.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                log.info("enter pressed");
            }
        });
                
        Button clear = new Button("Clear");
        clear.setId("clear-button");
        clear.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        controls.add(clear, 0, 0);
        
        clear.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                if(cardString.length()>0) cardString.deleteCharAt(cardString.length()-1);
                message.setText(cardString.toString());
            }
        });
        
        Button cancel = new Button("Cancel");
        cancel.setId("cancel-button");
        cancel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        controls.add(cancel, 1, 0);
        
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                cardString.delete(0, cardString.length());
                message.setText(cardString.toString());
            }
        });

        GridPane.setColumnSpan(enter, 1);
        GridPane.setColumnSpan(clear, 1);
        GridPane.setColumnSpan(cancel, 1);
        // GridPane.setHgrow(enter, Priority.ALWAYS);

        root.setTop(message);
        root.setCenter(numPad);
        root.setBottom(controls);
        stage.setScene(scene);
        stage.show();
    }
}
