package FX_Launcher.Utils;
import FX_Launcher.Main;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

import javax.swing.filechooser.FileSystemView;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

import static FX_Launcher.Utils.StringUtils.getPathNameFile;


public class FileUtils {

    private static final String[] whiteListSp = {"Unity Hub", "Android", "JetBrains", "bin", "Autodesk", "paint.net"};
    private static final String[] exeFileSp = {"idea64", "Unity Hub", "studio64", "3dsmax.", "PaintDotNet"};
    private static final String[] whiteListProg = {"Unity Hub", "Android", "JetBrains", "bin", "Autodesk", "Windows", "Microsoft",
            "Blender Foundation", "Adobe", "Git", "Intel", "nodejs", "OpenSSL-Win64", "Uninstall Information", "Unity", "NVIDIA Corporation"
            , "Corel", "Common Files", "Java", "Reference Assemblies", "Chaos Group"};

    /**
     * Get is exe file
     */
    public static boolean isExeFile(String name) {
        return name.endsWith(".exe");
    }

    /**
     * Get is run file
     */
    public static boolean isRunFile(String name) {
        for (String filterRun : exeFileSp) {
            if (name.startsWith(filterRun)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get is run uninstall file
     */
    public static boolean isRunUninstallFile(String name) {
        return name.startsWith("uninstall") || name.startsWith("Uninstall") || name.startsWith("unins000");
    }

    /**
     * Is filter Sp folder
     */
    public static boolean isFilterSpFolder(Path listFile) {
        for (String filter : whiteListSp) {
            if (listFile.endsWith(filter)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Is filter Program folder
     */
    public static boolean isFilterProgramFolder(Path listFile) {
        for (String filter : whiteListProg) {
            if (listFile.toString().contains(filter)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Is not file open folder
     */
    public static boolean isNotFolder(Path path) {
        return !path.toFile().isFile();
    }

    /**
     * Get icon File
     */
    public static Image getFileIcon(File file) {
        javax.swing.Icon jswingIcon = FileSystemView.getFileSystemView().getSystemIcon(file);
        BufferedImage bufferedImage = new BufferedImage(jswingIcon.getIconWidth(), jswingIcon.getIconHeight(),
                BufferedImage.TYPE_INT_ARGB);
        jswingIcon.paintIcon(null, bufferedImage.getGraphics(), 0, 0);
        return SwingFXUtils.toFXImage(bufferedImage, null);
    }

    /**
     * Is file same folder
     */
    public static boolean isExeFileSameFolder(Path exeFile, Path uninstallFile) {
        if (exeFile != null && uninstallFile != null) {
            return getPathNameFile(exeFile).equals(getPathNameFile(uninstallFile));
        }
        return false;
    }

    /**
     * Get app exe file of size
     */
    public static boolean isAppExeFileSize(Path path) {
        try {
            long Bytes = Files.size(path);
            long kilobytes = (Bytes / 1024);
            long megabytes = (kilobytes / 1024);

            if (megabytes > 1 && megabytes <= 3) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Get load file is resources dir
     */
    public static String getLoadFileResources(String path) {
        return Objects.requireNonNull(FileUtils.class.getClassLoader().getResource(path)).toExternalForm();
    }

    /**
     * Get music file is dir
     */
    public static List<File> getLoadFileMusicDir() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select music");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("MP3", "*.mp3"));
        return fileChooser.showOpenMultipleDialog(Main.primaryStage);
    }

    /**
     * Get Image url jFX
     **/
    public static Image getImageUrl(String url) {
        return new Image(url);
    }

}