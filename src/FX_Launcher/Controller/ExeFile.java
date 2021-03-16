package FX_Launcher.Controller;

import java.io.File;

public class ExeFile {

    private final File[] exeFile = new File[2];
    private final String name;

    public ExeFile(String nameExeFile, String nameUninstallFile, String name) {
        this.name = name;

        exeFile[0] = new File(nameExeFile);
        exeFile[1] = new File(nameUninstallFile);

    }

    public File getExeFile(int index) {
        return exeFile[index];
    }


    public String getName() {
        return name;
    }
}
