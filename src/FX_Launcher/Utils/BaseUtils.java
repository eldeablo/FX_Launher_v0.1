package FX_Launcher.Utils;

import FX_Launcher.UI.UI_RootPane;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

import java.io.File;
import java.util.List;

public class BaseUtils {


    /**
     * Equals multi object
     */
    public static boolean IsObjectEquals(Object... object) {
        return object[0].equals(object[1]);
    }

    /**
     * Add pane is rootPane and Get pane visible
     */
    public static void AddIsVisibleComponent(BorderPane rootPane, UI_RootPane addPane) {
        if (addPane.IsVisibility()) {
            rootPane.setCenter(null);
            addPane.setVisibility(false);
        } else {
            rootPane.setCenter(addPane);
            addPane.setVisibility(true);
        }
    }

    /**
     * Get array component and is all visible is component visible
     */
    public static void VisibleAllComponent(BorderPane root, UI_RootPane... visible) {
        for (UI_RootPane listVisible : visible) {
            if (listVisible.IsVisibility()) {
                root.setCenter(null);
                listVisible.setVisibility(false);
            }
        }
    }

    /**
     * Get KeyCode input
     **/
    public static boolean IsKeyPress(KeyEvent event, KeyCode inputKeyboards) {
        return event.getCode() == inputKeyboards;
    }

}
