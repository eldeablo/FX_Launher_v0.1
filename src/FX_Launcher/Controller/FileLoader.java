package FX_Launcher.Controller;


import FX_Launcher.Main;
import FX_Launcher.Utils.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static FX_Launcher.Utils.FileUtils.*;

public class FileLoader {
    private final Path fileSp = Paths.get("C:\\Program Files");
    private final Path fileGame = Paths.get("C:\\Games");
    private final List<Path> exeFile = new ArrayList<>();
    private final List<String> nameRunIsFile = new ArrayList<>();


    public FileLoader() {

    }

    /**
     * Load list file Sp
     */
    public void LoadFileGame() {
        try (Stream<Path> stream = Files.list(fileGame)) {
            stream.forEach(listFile -> {
                if (!isExeFile(listFile.toString())) {
                    Path path = Paths.get(listFile + "\\");
                    openFolderRoot(path);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        addGameFile();
    }

    /**
     * Load list file Sp
     */
    public void LoadFileSp() {
        try (Stream<Path> stream = Files.list(fileSp)) {
            stream.forEach(listFile -> {
                if (isFilterSpFolder(listFile)) {
                    Path path = Paths.get(listFile + "\\");
                    openFolderRoot(path);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        addExeFileSP();
    }

    /**
     * Load list file Sp
     */
    public void LoadFileProgram() {
        try (Stream<Path> stream = Files.list(fileSp)) {
            stream.forEach(listFile -> {
                if (isFilterProgramFolder(listFile) && isNotFolder(listFile)) {
                    Path path = Paths.get(listFile + "\\");
                    openFolderRoot(path);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        addExeFile();
    }

    /**
     * Open is folder
     */
    private void openFolderRoot(Path path) {

        try (Stream<Path> stream = Files.list(path)) {
            stream.forEach(listFile -> {
                if (isExeFile(StringUtils.getNameIsFile(listFile))) {
                    exeFile.add(listFile);
                } else {
                    if (isNotFolder(listFile)) {
                        Path paths = Paths.get(listFile + "\\");
                        openFolderList(paths);
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get list folder 2 programs
     */
    private void openFolderList(Path path) {

        try (Stream<Path> stream = Files.list(path)) {
            stream.forEach(listFile -> {
                if (isExeFile(StringUtils.getNameIsFile(listFile))) {
                    exeFile.add(listFile);
                } else {
                    if (isNotFolder(listFile)) {
                        Path paths = Paths.get(listFile + "\\");
                        openFolderBin(paths);
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Open folder bin
     */
    private void openFolderBin(Path path) {
        try (Stream<Path> stream = Files.list(path)) {
            stream.forEach(listFile -> {
                if (isExeFile(StringUtils.getNameIsFile(listFile))) {
                    exeFile.add(listFile);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addExeFileSP() {
        Path[] isSameFolder = new Path[2];
        exeFile.forEach(runFile -> {
            if (isRunFile(StringUtils.getNameIsFile(runFile))) {
                isSameFolder[0] = runFile;
            }
            if (isRunUninstallFile(StringUtils.getNameIsFile(runFile))) {
                isSameFolder[1] = runFile;
            }

            if (isExeFileSameFolder(isSameFolder[0], isSameFolder[1])) {

                String name = StringUtils.getPathNameFile(Path.of(isSameFolder[0].toString()));
                Main.listExeFileSp.add(new ExeFile(isSameFolder[0].toString(), isSameFolder[1].toString(), name));

                isSameFolder[0] = null;
                isSameFolder[1] = null;
            }

        });
        exeFile.clear();

    }

    private void addExeFile() {
        exeFile.forEach(runFile -> {
            if (!isRunFile(StringUtils.getNameIsFile(runFile)) && isFilterProgramFolder(runFile) && !isRunUninstallFile(StringUtils.getNameIsFile(runFile))) {
                if (isAppExeFileSize(runFile)) {
                    String name = StringUtils.getPathNameFile(Path.of(runFile.toString()));
                    Main.listExeFileProg.add(new ExeFile(runFile.toString(), "null", name));
                }
            }
        });
        exeFile.clear();
    }

    /**
     * Add game file list+
     */
    private void addGameFile() {
        Path[] isSameFolder = new Path[2];
        exeFile.forEach(runFile -> {

            if (isRunUninstallFile(StringUtils.getNameIsFile(runFile))) {
                isSameFolder[1] = runFile;
            } else {
                isSameFolder[0] = runFile;
            }

            if (isExeFileSameFolder(isSameFolder[0], isSameFolder[1])) {
                String name = StringUtils.getPathNameFile(Path.of(isSameFolder[0].toString()));
                Main.listExeFileGame.add(new ExeFile(isSameFolder[0].toString(), isSameFolder[1].toString(), name));

                isSameFolder[0] = null;
                isSameFolder[1] = null;
            }

        });
        exeFile.clear();
    }


    /**
     * Get exe run file
     */
    public void GetLoadIsRunExeFile() {
        String line;
        Process p;
        try {
            p = Runtime.getRuntime().exec("tasklist.exe /nh");
            BufferedReader input = new BufferedReader
                    (new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                if (!line.trim().equals("")) {
                    nameRunIsFile.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
