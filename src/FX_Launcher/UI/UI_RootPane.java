package FX_Launcher.UI;

import FX_Launcher.Main;
import com.jfoenix.controls.JFXSpinner;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public abstract class UI_RootPane extends Pane {

    protected JFXSpinner _spinner = new JFXSpinner(0.1);

    private boolean _isVisibility = false;

    public UI_RootPane(float offsetWight,float offsetHeight){
        this.setPrefSize(Main._Width - offsetWight,Main._Height - offsetHeight);
        this.setOnKeyPressed(this::KeyPress);
        _spinner.setPrefSize(Main._Width - offsetWight,Main._Height - offsetHeight);
    }


    abstract void KeyPress(KeyEvent event);

    public boolean IsVisibility(){
        return _isVisibility;
    }

    public void setVisibility(boolean visibility){
        this._isVisibility = visibility;
    }
}
