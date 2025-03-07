package frontEnd;

import JSON.UserName;
import JSON.readJSON;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DeletUser {
    int counter = 0;
    VBox deletedUserLayout = new VBox();
    Stage primaryStage = new Stage();
    public void deletUserStage(Stage stage ) throws IOException{
        String CSSfilePath = "C:/Users/bendo/IdeaProjects/JSONPractice/src/main/java/Style/StylSheet.css";
        primaryStage = stage;
        File cssStyle = new File(CSSfilePath);

        deletedUserLayout.getStylesheets().add(cssStyle.toURI().toString());
        stage.setTitle("Delete User");

        Scene secen = new Scene(deletedUserLayout , 800 , 600);
         deletedUserLayout.getChildren().add(searchBar());
        stage.setScene(secen);
        stage.show();


    }
    private TextField sreachBox = new TextField();
    private GridPane searchBar(){

        Label searchLabel = new Label("search");
        Button enter = new Button("Enter" );
        enterAction(enter);
        GridPane grid = new GridPane();
        grid.getStyleClass().add("delete-user-grid");
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(searchLabel, 0, 0);
        grid.add(sreachBox , 1 ,0  );
        grid.add(enter, 2 ,0 );
        Button back = new Button("Back");
        back.getStyleClass().add("delete-user-backButton");
        backButtonAction(back);
        deletedUserLayout.getChildren().add(back);
        return grid;
    }

    private void enterAction(Button button){

        button.setOnAction(e -> {

            try {
                counter++;
                UserSearchedName(sreachBox.getText());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


        });
    }

    private void deleteAction(Button button){
        readJSON read = new readJSON();
        MainScreen screen = new MainScreen();
        button.setOnAction(e -> {
            try {

                read.jsonFindUser(sreachBox.getText());
                screen.start(primaryStage);

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
    private void UserSearchedName(String userName) throws IOException {
        if(counter > 0){
            deletedUserLayout.getChildren().clear();
            deletedUserLayout.getChildren().add(searchBar());
        }
       readJSON read = new readJSON();
        UserName user ;

        ArrayList<UserName> users = read.jsonArrayReader();

        Button DeleteUser = new Button("Delete User");
        DeleteUser.getStyleClass().add("delete-User-Button");
        deleteAction(DeleteUser);
        int index = 0;
       for(int i  = 0; i < users.size(); i++){
            user = users.get(i);
            if (user.getuserName().equals(userName)) {
                index = i;
                break;
            }
        }
       user = users.get(index);
        // this is the username
        Label userLabel = new Label("Username : " + user.getuserName());
        userLabel.getStyleClass().add("delete-Users-labels");
        // this is the age of the user
        Label ageLabel = new Label("Age of user : " +  user.getAge());
        ageLabel.getStyleClass().add("delete-Users-labels");
        // this is the users height infroamtion
        Label hieghtLabel = new Label("Hight of user : " + user.getHeight());
        hieghtLabel.getStyleClass().add("delete-Users-labels");
        // the users country
        Label countryLabel = new Label("Country of user : " + user.getCountry());
        countryLabel.getStyleClass().add("delete-Users-labels");
        // Film label
        Label filmLabel = new Label("User favret Film : " + user.getFilm());
        filmLabel.getStyleClass().add("delete-Users-labels");
        // number of time watched the film
        Label watchesLabel = new Label("Number of watches : " + user.getWatches());
        watchesLabel.getStyleClass().add("delete-Users-labels");

        deletedUserLayout.getChildren().add(userLabel);
        deletedUserLayout.getChildren().add(ageLabel);
        deletedUserLayout.getChildren().add(hieghtLabel);
        deletedUserLayout.getChildren().add(countryLabel);
        deletedUserLayout.getChildren().add(filmLabel);
        deletedUserLayout.getChildren().add(watchesLabel);
        deletedUserLayout.getChildren().add(DeleteUser);


    }

    private void backButtonAction(Button button){
        button.setOnAction(e -> {
            MainScreen screen = new MainScreen();

            try {
                screen.start(primaryStage);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

}
