package FX_Launcher.UI;

import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import static FX_Launcher.Utils.BaseUtils.IsKeyPress;

public class UI_Browser extends UI_RootPane {

    private final WebView _view = new WebView();
    private final WebEngine _engine = _view.getEngine();

    public UI_Browser(float offsetWight, float offsetHeight) {
        super(offsetWight, offsetHeight);
        this.setLayoutX(55);

        _view.setPrefSize(974, 606);

        _engine.setJavaScriptEnabled(true);

        _view.setVisible(false);

        this.getChildren().addAll(_spinner, _view);
    }

    @Override
    void KeyPress(KeyEvent event) {
        if (IsKeyPress(event, KeyCode.F5)) {
            _engine.reload();
            updateUI();
        }
    }

    public void newsURL() {
        loadWebURL("https://zen.yandex.ru/");
    }

    public void loadWebURL(String url) {
        _engine.load(url);
        updateUI();
    }

    private void updateUI(){
        _engine.getLoadWorker().progressProperty().addListener((observableValue, oldValue, newValue) -> {
            Platform.runLater(()->{
                if (newValue.intValue() == 1) {
                    System.out.println("Loading done...");
                    _view.setVisible(true);
                    _spinner.setVisible(false);
                    _spinner.setProgress(0f);
                }else{
                    System.out.println(_spinner.isVisible());
                    if (!_spinner.isVisible()) {
                        _spinner.setVisible(true);
                    }
                    _spinner.setProgress(newValue.floatValue());
                }

            });
        });
    }
}
