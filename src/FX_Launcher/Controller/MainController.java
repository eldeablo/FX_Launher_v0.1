package FX_Launcher.Controller;

import FX_Launcher.UI.UI_Browser;
import FX_Launcher.UI.UI_ProgramExeList;
import FX_Launcher.UI.UI_RootPane;
import FX_Launcher.Utils.FileUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.ResourceBundle;

import static FX_Launcher.Utils.BaseUtils.*;

public class MainController implements Initializable {
    private final UI_RootPane[] _UIRootPanes = new UI_RootPane[2];
    private final PlayerMusicVideo _playerMusicVideo = new PlayerMusicVideo(null);

    //UI menu
    @FXML
    private VBox _vboxController;
    @FXML
    private JFXButton _programButton;
    @FXML
    private JFXButton _gameButton;
    @FXML
    private JFXButton _youtubeButton;


    //UI music
    @FXML
    private Pane _musicPane;
    @FXML
    private JFXButton _playPause;
    @FXML
    private JFXButton _back;
    @FXML
    private JFXButton _next;
    @FXML
    private JFXSpinner _processMusic;
    @FXML
    private JFXButton _listMusicDir;
    @FXML
    private Label _timeLabel;

    //TopBarInfo
    @FXML
    private JFXButton _home;
    @FXML
    private Label _textInfo;


    //RootMenu
    @FXML
    private BorderPane _rootPane;

    public MainController() {
        _UIRootPanes[0] = new UI_ProgramExeList(0, 150);
        _UIRootPanes[1] = new UI_Browser(55, 150);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Ui home
        _home.setGraphic(new ImageView(FileUtils.getLoadFileResources("image/icon_home.png")));
        //Ui menu
        _programButton.setGraphic(new ImageView(FileUtils.getLoadFileResources("image/icon_programme.png")));

        _gameButton.setGraphic(new ImageView(FileUtils.getLoadFileResources("image/icon_game.png")));

        _youtubeButton.setGraphic(new ImageView(FileUtils.getLoadFileResources("image/icon_youtube.png")));

        //Ui music
        _playPause.setGraphic(new ImageView(FileUtils.getLoadFileResources("image/icon_pause.png")));

        _back.setGraphic(new ImageView(FileUtils.getLoadFileResources("image/icon_back.png")));

        _next.setGraphic(new ImageView(FileUtils.getLoadFileResources("image/icon_next.png")));

        _listMusicDir.setGraphic(new ImageView(FileUtils.getLoadFileResources("image/icon_menu.png")));

        _timeLabel.setText("00:00");

    }


    @FXML
    public void MouseEvent(MouseEvent event) {
        String type = event.getEventType().toString();
        if ("MOUSE_ENTERED".equals(type)) {
            showVision(event.getSource());
        } else if ("MOUSE_EXITED".equals(type)) {
            hideVision(event.getSource());
        } else if ("MOUSE_CLICKED".equals(type)) {
            musicButtonClick(event.getSource());
            menuButtonClick(event.getSource());
        } else {
            System.out.println(event.getSource());
        }
    }

    /**
     * show vision component
     */
    private void showVision(Object eventObject) {
        if (IsObjectEquals(eventObject, _vboxController)) {
            _vboxController.setPrefWidth(195);
            _programButton.setText("Программы");
            _gameButton.setText("Игры");
            _youtubeButton.setText("Видео ютуб");
        } else {
            System.out.println("Not enable");
        }
    }

    /**
     * hide vision component
     */
    private void hideVision(Object eventObject) {
        if (IsObjectEquals(eventObject, _vboxController)) {
            _vboxController.setPrefWidth(50);
            _programButton.setText("");
            _gameButton.setText("");
            _youtubeButton.setText("");
        } else {
            System.out.println("Not enable");
        }
    }

    /**
     * Music button event click
     */
    private void musicButtonClick(Object eventObject) {
        //Play and pause music
        if (IsObjectEquals(eventObject, _playPause)) {
            if (_playerMusicVideo.isStatusPlayer(MediaPlayer.Status.PLAYING)) {
                _playPause.setGraphic(new ImageView(FileUtils.getLoadFileResources("image/icon_pause.png")));
                _playerMusicVideo.pauseMusic();
            } else if (_playerMusicVideo.isStatusPlayer(MediaPlayer.Status.PAUSED)) {
                _playPause.setGraphic(new ImageView(FileUtils.getLoadFileResources("image/icon_play.png")));
                _playerMusicVideo.playMusic();
            }
        }
        //Back music
        else if (IsObjectEquals(eventObject, _back)) {
            _playerMusicVideo.backMusic();
            _playerMusicVideo.UpdateUI(_processMusic, _timeLabel);
        }
        //Next music
        else if (IsObjectEquals(eventObject, _next)) {
            _playerMusicVideo.nextMusic();
            _playerMusicVideo.UpdateUI(_processMusic, _timeLabel);
        }
        //Add music list
        else if (IsObjectEquals(eventObject, _listMusicDir)) {
            _playerMusicVideo.setListLocalMusic(FileUtils.getLoadFileMusicDir());
            _playerMusicVideo.newPlayMusic();
            _playPause.setGraphic(new ImageView(FileUtils.getLoadFileResources("image/icon_pause.png")));
            _playerMusicVideo.UpdateUI(_processMusic, _timeLabel);
        }
    }

    /**
     * Menu button event click
     */
    private void menuButtonClick(Object eventObject) {
        if (IsObjectEquals(eventObject, _programButton)) {
            VisibleAllComponent(_rootPane, _UIRootPanes);
            AddIsVisibleComponent(_rootPane, _UIRootPanes[0]);
        }
        //Game
        /*else if (IsObjectEquals(eventObject, _gameButton)) {
            AddIsVisibleComponent(_rootPane, _UIRootPanes[1]);
        }*/
        //Browser
        else if (IsObjectEquals(eventObject, _youtubeButton)) {
            UI_Browser browser = (UI_Browser) _UIRootPanes[1];
            VisibleAllComponent(_rootPane, _UIRootPanes);
            AddIsVisibleComponent(_rootPane, browser);
            browser.newsURL();
        }
    }

}
