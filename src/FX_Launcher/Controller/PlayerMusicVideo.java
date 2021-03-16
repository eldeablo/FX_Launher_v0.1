package FX_Launcher.Controller;


import com.jfoenix.controls.JFXSpinner;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static FX_Launcher.Utils.StringUtils.getTimeIsSecond;

public class PlayerMusicVideo {
    private MediaPlayer _mediaPlayer;
    private List<File> _listLocalMusic = new ArrayList<>();
    private int _indexMusicList = 0;

    public PlayerMusicVideo(String defaultMusicData) {
        if (defaultMusicData != null) {
            Media _media = new Media(defaultMusicData);
            _mediaPlayer = new MediaPlayer(_media);
        }
    }

    public void playMusic() {
        if (_mediaPlayer != null) {
            _mediaPlayer.play();
        }
    }

    public void pauseMusic() {
        _mediaPlayer.pause();
    }

    public void backMusic() {
        if (_indexMusicList != 0) {
            _mediaPlayer.stop();
            _indexMusicList--;
            newPlayMusic();
        }
    }

    public void nextMusic() {
        if (_indexMusicList != _listLocalMusic.size() - 1) {
            if (_mediaPlayer != null)
                _mediaPlayer.stop();
            _indexMusicList++;
            newPlayMusic();
        }
    }

    public void newPlayMusic() {
        Media _media = new Media(_listLocalMusic.get(_indexMusicList).toURI().toString());
        _mediaPlayer = new MediaPlayer(_media);
        playMusic();
    }

    public List<File> getListLocalMusic() {
        return _listLocalMusic;
    }

    public void setListLocalMusic(List<File> listLocalMusic) {
        this._listLocalMusic = listLocalMusic;
    }

    public boolean isStatusPlayer(MediaPlayer.Status status) {
        return _mediaPlayer.getStatus().equals(status);
    }

    public void UpdateUI(JFXSpinner timeMusic, Label textTime) {
        _mediaPlayer.setOnEndOfMedia(() -> {
            nextMusic();
            UpdateUI(timeMusic, textTime);
        });

        _mediaPlayer.currentTimeProperty().addListener(observable ->
                Platform.runLater(() -> {
                            double currentTime = _mediaPlayer.getCurrentTime().toMillis();
                            double totalTime = _mediaPlayer.getTotalDuration().toMillis();

                            timeMusic.setProgress(currentTime / totalTime);

                            textTime.setText(getTimeIsSecond(totalTime - currentTime));
                        }
                )
        );
    }
}
