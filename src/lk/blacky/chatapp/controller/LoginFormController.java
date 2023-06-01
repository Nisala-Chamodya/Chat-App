package lk.blacky.chatapp.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.blacky.chatapp.client.Client;
import lk.blacky.chatapp.client.ClientManager;
import lk.blacky.chatapp.utill.Navigation;
import lk.blacky.chatapp.utill.Routes;

import java.io.IOException;
import java.net.Socket;


public class LoginFormController {


    public AnchorPane pane;
    public    JFXTextField txtUserName;









    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.CLIENT,pane);
        String userName=txtUserName.getText();
        ;
        Socket socket=new Socket("localhost",1235);
        Client client=new Client(socket,userName);

    }
}





