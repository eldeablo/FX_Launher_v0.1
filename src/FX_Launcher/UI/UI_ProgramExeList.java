package FX_Launcher.UI;

import FX_Launcher.Controller.ExeFile;
import FX_Launcher.Main;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.List;

public class UI_ProgramExeList extends UI_RootPane {

    public UI_ProgramExeList(float offsetWight, float offsetHeight) {
        super(offsetWight, offsetHeight);

        this.setLayoutX(70);
        this.setLayoutY(50);

        VBox _listExe = new VBox();
        _listExe.setPrefSize(this.getPrefWidth(), this.getPrefHeight());
        _listExe.setSpacing(10f);

        TitledPane root_development_programsPane = new TitledPane();
        root_development_programsPane.setText("Dev programs");
        Pane _development_programsPane = new Pane();
        root_development_programsPane.setContent(_development_programsPane);

        TitledPane root_programsPane = new TitledPane();
        root_programsPane.setText("Programs");
        Pane _programsPane = new Pane();
        root_programsPane.setContent(_programsPane);

        loadExeFileUI(Main.listExeFileSp, _development_programsPane);
        loadExeFileUI(Main.listExeFileProg, _programsPane);

        _listExe.getChildren().addAll(root_development_programsPane, root_programsPane);

        this.getChildren().addAll(_listExe);
    }

    @Override
    void KeyPress(KeyEvent event) {

    }

    public void loadExeFileUI(List<ExeFile> listFile, Pane pane) {
        HBox column = new HBox();
        column.setLayoutX(10);
        column.setLayoutY(10);
        column.setSpacing(20f);
        for (ExeFile file : listFile) {
            column.getChildren().add(new UI_IconExe(file));
        }
        pane.getChildren().add(column);
    }
}
