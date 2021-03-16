package FX_Launcher;

import FX_Launcher.Controller.ExeFile;
import FX_Launcher.Controller.FileLoader;
import FX_Launcher.Utils.FileUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
//710 code
    public static double _Width = 1024f;
    public static double _Height = 720f;
    public static List<ExeFile> listExeFileSp = new ArrayList<>();
    public static List<ExeFile> listExeFileProg = new ArrayList<>();
    public static List<ExeFile> listExeFileGame = new ArrayList<>();
    public static Stage primaryStage;


    private final String loadXML = FileUtils.getLoadFileResources("ui/main_core.fxml");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FileLoader fileLoader = new FileLoader();
        fileLoader.LoadFileSp();
        fileLoader.LoadFileProgram();
        fileLoader.LoadFileGame();

        Main.primaryStage = primaryStage;

        FXMLLoader loader = new FXMLLoader(new URL(loadXML));

        Parent root = (Parent) loader.load();
        primaryStage.setTitle("FX_Launcher");
        primaryStage.setScene(new Scene(root, _Width, _Height));
        primaryStage.setFullScreen(false);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
