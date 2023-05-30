package lk.blacky.chatapp.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import lk.blacky.chatapp.utill.Navigation;
import lk.blacky.chatapp.utill.Routes;

import java.io.IOException;

public class SplashFormController {
    public Rectangle rectContainer;
    public Label lblStatus;
    public Rectangle rectLoading;
    public AnchorPane pane;

    public void initialize(){
        Timeline timeline = new Timeline();

        KeyFrame keyFrame1= new KeyFrame(Duration.millis(500), actionEvent ->{
            lblStatus.setText("Initializing Application....");
            rectLoading.setWidth(rectContainer.getWidth()*0.3);
        });

        KeyFrame keyFrame2= new KeyFrame(Duration.millis(1000),actionEvent ->{
            lblStatus.setText("Loading Internal Resources....");
            rectLoading.setWidth(rectContainer.getWidth()*0.5);
        });
        KeyFrame keyFrame3= new KeyFrame(Duration.millis(1500),actionEvent ->{
            lblStatus.setText("Loading Images....");
            rectLoading.setWidth(rectContainer.getWidth()*0.6);
        });
        KeyFrame keyFrame4= new KeyFrame(Duration.millis(2000),actionEvent ->{
            lblStatus.setText("Loading UIs....");
            rectLoading.setWidth(rectContainer.getWidth()*0.8);
        });
        KeyFrame keyFrame5= new KeyFrame(Duration.millis(2500),actionEvent ->{
            lblStatus.setText("Welcome to Chat App v1.0.0");
            rectLoading.setWidth(rectContainer.getWidth());
        });
        KeyFrame keyFrame6= new KeyFrame(Duration.millis(2500),actionEvent ->{
            try {
                Navigation.navigate(Routes.LOGIN,pane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });



        timeline.getKeyFrames().addAll(keyFrame1,keyFrame2,keyFrame3,keyFrame4,keyFrame5,keyFrame6);
        timeline.playFromStart();

    }
}
