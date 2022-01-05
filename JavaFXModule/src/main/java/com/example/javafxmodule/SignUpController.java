package com.example.javafxmodule;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import slack.service.UserService;

import java.io.IOException;


public class SignUpController {
    @FXML
    private Label welcomeText;

    @FXML
    private TextField idInput;

    @FXML
    private TextField passwordInput;

    @FXML
    private TextField usernameInput;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField firstNameInput;

    @FXML
    private TextArea warningArea;

    private boolean warningIsDisplayed = false;

    @FXML
    private ImageView logo;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void initialize(){
        logo.setImage(new Image("file:JavaFXModule/src/main/java/com/example/javafxmodule/images/ShlagLogo2.png"));
    }

    //Fonction lancée au clic du bouton s'inscrire
    @FXML
    protected void pressButton(ActionEvent event){
        System.out.println("Identifiant : "+idInput.getText());
        System.out.println("Mot de passe : "+passwordInput.getText());
    }


    //Switch sur la fenêtre de connexion au click du bouton "Je suis déjà inscrit"
    @FXML
    public void switchToSignIn(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SignUpController.class.getResource("SignIn.fxml"));

        //Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(),1920,1080);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void signUp(ActionEvent event) throws IOException{
        String email = idInput.getText();
        String password = passwordInput.getText();
        String username = usernameInput.getText();
        String name = nameInput.getText();
        String firstName = firstNameInput.getText();



        if(email != "" && password != "" && username != "" && name != "" && firstName != ""){
            //Si aucun champ n'est vide on inscris l'utilisateur
            UserService.inscription(username, password, email, name, firstName);

            //On le renvoie ensuite à la page de connexion pour qu'il s'authentifie
            FXMLLoader fxmlLoader = new FXMLLoader(SignUpController.class.getResource("SignIn.fxml"));
            //Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(),1920,1080);

            stage.setScene(scene);
            stage.show();

        } else if(warningIsDisplayed == false) {
            warningArea.appendText("Veuillez remplir tout les champs");
            warningIsDisplayed = true;
        }

    }

}