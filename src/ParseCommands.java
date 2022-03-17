import java.util.Arrays;

public class ParseCommands {
    public static void test() {
        HardDrive hd = new HardDrive("test","100g");
        HardDrive hd1 = new HardDrive("another","100g");
        HardDrive hd2 = new HardDrive("epic","100g");
        HardDrive hd3 = new HardDrive("tpooo","100g");
        LocalHDStorage.addDrive(hd);
        LocalHDStorage.addDrive(hd1);
        LocalHDStorage.addDrive(hd2);
        LocalHDStorage.addDrive(hd3);
        LocalHDStorage.displayDrives();
        if (LocalHDStorage.hasHD("another") == null || LocalHDStorage.hasHD("another").isOccupied()) {
            // drive is not present in local storage
            System.out.println("Error! Disk does not exist or is occupied");
        }
        else {
            System.out.println("Is this drive occupied? : " + LocalHDStorage.hasHD("another").isOccupied());
            LocalHDStorage.hasHD("another").setOccupied();
            System.out.println("Is this drive occupied? : " + LocalHDStorage.hasHD("another").isOccupied());
            PhysicalVolume newPV = new PhysicalVolume("pvName", UUIDGenerator.generator(), LocalHDStorage.hasHD("another"));
            LocalHDStorage.addPhysicalVolumes(newPV);
            LocalHDStorage.displayPhysicalVol();
        }
    }
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
        if (msg.contains("pvcreate")) {
            String pvName = msg.split(" ")[1];
            String driveName = msg.split(" ")[2];
            if (LocalHDStorage.hasHD(driveName) == null || LocalHDStorage.hasHD(driveName).isOccupied()) {
                // drive is not present in local storage
                System.out.println("Error! Disk does not exist or is occupied");
            }
            else {
                System.out.println("Is this drive occupied? : " + LocalHDStorage.hasHD(driveName).isOccupied());
                LocalHDStorage.hasHD(driveName).setOccupied();
                System.out.println("Is this drive occupied? : " + LocalHDStorage.hasHD(driveName).isOccupied());
                PhysicalVolume newPV = new PhysicalVolume(pvName, UUIDGenerator.generator(), LocalHDStorage.hasHD(driveName));
                LocalHDStorage.displayPhysicalVol();
            }


        }
    }

}
