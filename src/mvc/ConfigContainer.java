package mvc;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigContainer {

    private static final Path SETTINGS_FILE_PATH = Paths.get(
            System.getProperty("user.home"), ".eazi", "userSettings.properties");

    /**
     * Creates a new settings file in users home directory in case there is none.
     * Modify this to change any of the property names.
     */
    public static Properties makeDefaultConfigFile() {
        Properties prp = new Properties();

        try {
            // make new directory to file
            if(SETTINGS_FILE_PATH.getParent() != null) {
                Files.createDirectories(SETTINGS_FILE_PATH.getParent());
            }
            // make file
            Files.createFile(SETTINGS_FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }

        prp.setProperty("savePath", SETTINGS_FILE_PATH.toString());
        prp.setProperty("actionCircleFieldCount", "12");
        prp.setProperty("autoSaveButton", "true");
        prp.setProperty("intervalBox", "false");
        prp.setProperty("autoSaveInterval", "2");   // don't forget to cast to int
        prp.setProperty("hideLogButton", "false");
        prp.setProperty("colorFighter", "50,205,50");   // red,green,blue
        prp.setProperty("colorAlly", "30,144,255");
        prp.setProperty("colorEnemy", "255,0,0");
        prp.setProperty("simpleActions", "6");
        prp.setProperty("simpleActionsSurcharge", "3");
        prp.setProperty("simpleReactions", "0");
        prp.setProperty("simpleReactionsSurcharge", "3");
        prp.setProperty("freeActions", "0");

        try (FileOutputStream output = new FileOutputStream(SETTINGS_FILE_PATH.toFile())) {
            prp.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new InfoDialog("A new configurations file had been created for you!\n" +
                SETTINGS_FILE_PATH).showAndWait();
        return prp;
    }

    public static Path getSettingsFilePath() {
        return SETTINGS_FILE_PATH;
    }
}
