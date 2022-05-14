package com.example.lucas;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class HelloController {
  @FXML
    private MediaView mediaView;
  @FXML
    private MediaPlayer lucas;

  @FXML
  private Slider sliderVolume;

  @FXML
  private Slider sliderVideo;

    public void initialize() {
        String video = getClass().getResource("A-Reece.mp4").toExternalForm();
        Media media = new Media(video);
        lucas = new MediaPlayer(media);
        mediaView.setMediaPlayer(lucas);

        sliderVolume.setValue(lucas.getVolume()*100);
        sliderVolume.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                lucas.setVolume(sliderVolume.getValue()/100);
            }
        });

        lucas.setOnReady(new Runnable() {
            @Override
            public void run() {
                Duration total = media.getDuration();
                sliderVideo.setMax(total.toSeconds());
            }
        });


        lucas.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observableValue, Duration oldValue, Duration newValue) {

                sliderVideo.setValue(newValue.toSeconds());
            }
        });


        sliderVideo.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                lucas.seek(Duration.seconds(sliderVideo.getValue()));
            }
        });


        sliderVideo.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                lucas.seek(Duration.seconds(sliderVideo.getValue()));
            }
        });


    }



    public void playVideo(MouseEvent event) {
        lucas.play();
    }

    public void pauseVideo(MouseEvent event) {
        lucas.pause();
    }
    public void stopVideo(MouseEvent event) {
        lucas.stop();
    }

    @FXML
    void forwardVideo(MouseEvent event) {
        lucas.seek(lucas.getCurrentTime().add(Duration.seconds(+10)));
    }

    @FXML
    void backwardVideo(MouseEvent event) {
        lucas.seek(lucas.getCurrentTime().add(Duration.seconds(-10)));
    }





}