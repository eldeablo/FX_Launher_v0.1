package FX_Launcher.UI;

import FX_Launcher.Controller.ExeFile;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Objects;

import static FX_Launcher.Utils.FileUtils.getFileIcon;

public class UI_IconExe extends VBox {

    private final Label _nameRunFile = new Label();

    private final ExeFile _runFile;
    private boolean _isEnable;

    public UI_IconExe(ExeFile _runFile) {
        this._runFile = _runFile;
        this.setPrefSize(100, 100);


        ImageView iconImage = new ImageView();
        iconImage.setFitWidth(100);
        iconImage.setFitHeight(100);
        iconImage.setImage(getFileIcon(_runFile.getExeFile(0)));


        Label nameFile = new Label(_runFile.getName());
        nameFile.setAlignment(Pos.CENTER);
        nameFile.setPrefSize(100, 50);


        _nameRunFile.setWrapText(true);
        _nameRunFile.setAlignment(Pos.CENTER);
        _nameRunFile.setPrefSize(100, 50);


        this.setOnMouseClicked(e -> runExeFile());
        this.getChildren().addAll(iconImage, nameFile, _nameRunFile);
    }

    /**
     * Run exe app
     */
    private void runExeFile() {
        if (!_isEnable) {
            Runtime runtime = Runtime.getRuntime();
            Process process;
            try {
                process = runtime.exec("cmd /c \"" + _runFile.getExeFile(0).getAbsolutePath() + "\"");
                _nameRunFile.setText("Runnable is " + _runFile.getName());
                OnExitExeFile(Objects.requireNonNull(process));
                _isEnable = true;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * CallBack isClose exe app
     */
    private void OnExitExeFile(Process processCalBack) {
        processCalBack.onExit().thenRunAsync(() -> {
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).thenApply((name) -> {
            Platform.runLater(() -> _nameRunFile.setText(""));
            _isEnable = false;
            return name;
        });
    }
}
