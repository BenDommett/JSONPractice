package frontEnd;

import JSON.JSONWriter;
import JSON.UserName;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class CreateNewUser {
    VBox layoutNewUser = new VBox();
    Stage primaryStage = new Stage();
    public void newUserStage(Stage stage ) throws IOException{
        primaryStage = stage;
        stage.setTitle("Create new user");
        String CSSfilePath = "C:/Users/bendo/IdeaProjects/JSONPractice/src/main/java/Style/StylSheet.css";
        File cssFile = new File(CSSfilePath);

        layoutNewUser.getStylesheets().add(cssFile.toURI().toString());
        Scene sceneManu = new Scene(layoutNewUser,  800, 600);
        layoutNewUser.getChildren().add(textAndLabelsLayout());
        layoutNewUser.getChildren().add(enterbutton());
        stage.setScene(sceneManu);
        stage.show();
    }

    private GridPane textAndLabelsLayout(){
        addNewUserinfo();
        Label[] labels = getLabelsList();
        TextField[] textFields = getTextFildList();
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        for(int x = 0; x < labels.length; x++){

            labels[x].getStyleClass().add("newuser-Labels");
            textFields[x].getStyleClass().add(" newUser-TextBox");
                gridPane.add(labels[x] , 0,  x  );

                gridPane.add(textFields[x] , 1 ,  x );
        }
        
        return gridPane;
    }

    Label[] newUserLabels = new Label[6];
    TextField[] newUserText = new TextField[6];
    private void addNewUserinfo(){
        //username infromation
        Label newUserLabel = new Label(" Enter username : ");
        TextField newUser = new TextField();
        newUserLabels[0] = newUserLabel;
        newUserText[0] = newUser;

        //users age
        Label newUsersAge = new Label("Enter age : ");
        TextField newUserAge = new TextField();
        newUserLabels[1] = newUsersAge;
        newUserText[1] = newUserAge;

        // users height
        Label newUserHight = new Label("Enter user height : ");
        TextField newUserHieghtText = new TextField();
        newUserLabels[2] = newUserHight;
        newUserText[2] = newUserHieghtText;

        // users country
        Label newUserCountry = new Label("Enter user country : ");
        TextField newUserCountryText = new TextField();
        newUserLabels[3] = newUserCountry;
        newUserText[3] = newUserCountryText;

        // users film
        Label newUserFilm = new Label("Enter new user film : ");
        TextField newUserFilmText = new TextField();
        newUserLabels[4] = newUserFilm;
        newUserText[4] = newUserFilmText;
        // users watches
        Label newUserWatches = new Label("Enter user Watches : ");
        TextField newUserWatchsText = new TextField();
        newUserLabels[5] = newUserWatches;
        newUserText[5] = newUserWatchsText;

    }
    private TextField[] getTextFildList(){
        return newUserText;
    }
    private Label[] getLabelsList(){
        return newUserLabels;
    }

    private Button enterbutton(){
        Button enter = new Button("Enter");
        enterButtonAction(enter);
        enter.getStyleClass().add("newUser-enter-Button");
        return enter;
    }
    private void enterButtonAction(Button button){
        button.setOnAction(r -> {
            UserName newUser = new UserName();
            JSONWriter JSON = new JSONWriter();
            newUser.setuserName(newUserText[0].getText());
            newUser.setAge(Integer.parseInt(newUserText[1].getText()));
            newUser.setHeight(Double.parseDouble(newUserText[2].getText()));
            newUser.setCountry(newUserText[3].getText());
            newUser.setFilm(newUserText[4].getText());
            newUser.setWatches(Integer.parseInt(newUserText[5].getText()));

            try {
                JSON.JSONWriterAddNewUser(newUser);
                MainScreen screen = new MainScreen();
                screen.start(primaryStage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        });
    }

}
