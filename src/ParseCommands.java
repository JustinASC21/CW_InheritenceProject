import java.util.Arrays;

public class ParseCommands {
    public static void parseMessage(String msg) {
        if (msg.contains("install-drive")) {
            String hdName = msg.split(" ")[1];
            String hdSize = msg.split(" ")[2];
            HardDrive drive = new HardDrive(hdName,hdSize);
            LocalHDStorage.addDrive(drive);
            System.out.println("Drive successfully created!");
        }
        if (msg.contains("list-drives")) {
            LocalHDStorage.displayDrives();
        }
    }

}
