package frontEnd;

import JSON.JSONWriter;
import JSON.UserName;
import JSON.readJSON;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;

public class MainScreen {

    private int counter = 0;
    private int arraylength;
    private  ArrayList<UserName> users = new ArrayList<>();
    public Label[] UserInfromation() throws IOException {
        readJSON read = new readJSON();
        UserName user;
        Label[] labels = new Label[6];
        users = read.jsonArrayReader();
        arraylength = users.size();
        user = users.get(counter);
        // this is the username
        Label userLabel = new Label("Username : " + user.getuserName());
        labels[0] = userLabel;
        // this is the age of the user
        Label ageLabel = new Label("Age of user : " +  user.getAge());
        labels[1] = ageLabel;
        // this is the users height infroamtion
        Label hieghtLabel = new Label("Hight of user : " + user.getHeight());
        labels[2] = hieghtLabel;
        // the users country
        Label countryLabel = new Label("Country of user : " + user.getCountry());
        labels[3] = countryLabel;
        // Film label
        Label filmLabel = new Label("User favret Film : " + user.getFilm());
        labels[4] = filmLabel;
        // number of time watched the film
        Label watchesLabel = new Label("Number of watches : " + user.getWatches());
        labels[5] = watchesLabel;

        return labels;
    }

    public Button nextUser(){
        Button NextU = new Button("Next user");
        nextUserAction(NextU);
        NextU.getStyleClass().add("next-user-Button");
        return NextU;
    }
    public Button LastUser(){
        Button lastUser = new Button("previous user ");
        lastUserAction(lastUser);
        lastUser.getStyleClass().add("previous-user-Button");
        return lastUser;
    }
    private VBox layoutMenu = new VBox();
    Stage primaryStage = new Stage();
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        String CSSfilePath = "C:/Users/bendo/IdeaProjects/JSONPractice/src/main/java/Style/StylSheet.css";
        File cssFile = new File(CSSfilePath);

        layoutMenu.getStylesheets().add(cssFile.toURI().toString());


        stage.setTitle("User Information");

        Scene sceneManu = new Scene(layoutMenu,  800, 600);
        userInfromatiom();
        layoutMenu.getChildren().add(nextUser());
        layoutMenu.getChildren().add(LastUser());
        layoutMenu.getChildren().add(gridlayout( watchesUpdate() , enter() ));
        layoutMenu.getChildren().add(creatingNewUsere());

        stage.setScene(sceneManu);

        stage.show();
    }

    private void nextUserAction(Button button){
        button.setOnAction(e -> {

            try {

                if(counter < arraylength - 1){
                    layoutMenu.getChildren().clear();
                    counter++;
                    userInfromatiom();
                    layoutMenu.getChildren().add(nextUser());
                    layoutMenu.getChildren().add(LastUser());
                    layoutMenu.getChildren().add(gridlayout( watchesUpdate() , enter() ));
                    layoutMenu.getChildren().add(creatingNewUsere());
                }else{
                    Label lastUser = new Label("this is the last user ");
                    lastUser.getStyleClass().add("label-endOfUsers");
                    layoutMenu.getChildren().add(lastUser);
                }



            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });
    }
    private void lastUserAction(Button button){
        button.setOnAction(e -> {

            try {

                if( counter > 0){
                    layoutMenu.getChildren().clear();
                    counter--;
                    userInfromatiom();
                    layoutMenu.getChildren().add(nextUser());
                    layoutMenu.getChildren().add(LastUser());
                    layoutMenu.getChildren().add(gridlayout( watchesUpdate() ,enter() ));
                    layoutMenu.getChildren().add(creatingNewUsere());
                }else{
                    Label lastUser = new Label("this is the last user ");
                    lastUser.getStyleClass().add("label-endOfUsers");
                    layoutMenu.getChildren().add(lastUser);
                }



            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });
    }
    private void userInfromatiom() throws IOException {
        Label[] labels = UserInfromation();
        for(int i = 0; i < 6; i++){

            Label temp = labels[i];
            layoutMenu.getChildren().add(temp);
        }
    }
    private TextField updata = new TextField();
    private TextField watchesUpdate(){

        Label label = new Label(" Update whatchs");
        label.getStyleClass().add("label-watches-update");
        layoutMenu.getChildren().add(label);
        updata.getStyleClass().add("text-field");

        return updata;
    }

    private Button enter() {
        Button button4 = new Button("Enter");

        enterButtonAction(button4);
        return button4;
    }

    private void enterButtonAction(Button button){
        JSONWriter json = new JSONWriter();
        button.setOnAction(e -> {
            try {
                if (updata.getText() == null || updata.getText().trim().isEmpty()) {
                    System.out.println("Error: Input is empty!");

                }else{
                    int finalupdate = Integer.parseInt(updata.getText());
                    json.jsonWriter(users , counter , finalupdate );
                    layoutMenu.getChildren().clear();
                    userInfromatiom();
                    layoutMenu.getChildren().add(nextUser());
                    layoutMenu.getChildren().add(LastUser());
                    layoutMenu.getChildren().add(gridlayout( watchesUpdate() ,enter() ));


                }


            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
    private GridPane gridlayout( TextField textField,Button button){
        GridPane gridPane = new GridPane();
        gridPane.getStyleClass().add("grid-style");

        ColumnConstraints colum0 = new ColumnConstraints();

        gridPane.setHgap(10);
        gridPane.setVgap(10);

        colum0.setPrefWidth(200);
        RowConstraints row0 = new RowConstraints();
        row0.setPrefHeight(100);
        gridPane.getColumnConstraints().add(colum0);
        gridPane.getRowConstraints().add(row0);
        gridPane.add(textField, 0, 0);

        gridPane.add(button, 1, 0);
        return gridPane;
    }
    private Button creatingNewUsere(){
        Button newUser = new Button("New User");
        newUser.getStyleClass().add("user-button");
        newuserAction(newUser);
        return newUser;
    }
    private void newuserAction(Button button){
        CreateNewUser newUser = new CreateNewUser();
        button.setOnAction(e -> {
            try {
                newUser.newUserStage(primaryStage);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }


}
