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
            LocalHDStorage.displayVolumes();
        }
    }
    public static void parseMessage(String msg) {
        if (msg.contains("install-drive")) {
            String hdName = msg.split(" ")[1];
            String hdSize = msg.split(" ")[2];
            HardDrive drive = new HardDrive(hdName,hdSize);
            if (LocalHDStorage.hasHD(hdName) != null) {
                // drive is not present in local storage
                System.out.println("Error! Disk already exists!");
            }
            else {
                LocalHDStorage.addDrive(drive);
                System.out.println("Drive successfully created!");
            }
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
//              drive is not occupied so set it to occupied now;
                LocalHDStorage.hasHD(driveName).setOccupied();
                // create physical volume below
                PhysicalVolume newPV = new PhysicalVolume(pvName, UUIDGenerator.generator(), LocalHDStorage.hasHD(driveName));
                LocalHDStorage.addPhysicalVolumes(newPV);
            }
        }
        if (msg.contains("pvlist")) {
            LocalHDStorage.displayVolumes();
        }
        if (msg.contains("vgcreate")) {
            String vgName = msg.split(" ")[1];
            String pvName = msg.split(" ")[2];
            if (LocalHDStorage.hasObject(pvName) instanceof PhysicalVolume) {
                PhysicalVolume foundPV = (PhysicalVolume) LocalHDStorage.hasObject(pvName);
                if (foundPV.isOccupied()) {
                    // drive is not present in local storage
                    System.out.println("Error! Physical Volume does not exist or is occupied");
                }
                else {
                    // vg free to be used for pv'
                    VolumeGroup vg = new VolumeGroup(vgName,UUIDGenerator.generator(),foundPV);
                    foundPV.setOccupied(vg);
                    LocalHDStorage.displayVolumes();
                    // create physical volume below
                    System.out.println("Successfully created Volume Group [" + vg.getName() + "]");
                }
            }


        }
        /*if (msg.contains("vgextend")) {
            String vgName = msg.split(" ")[1];
            String pvName = msg.split(" ")[2];

            if (LocalHDStorage.hasPV(pvName) == null || LocalHDStorage.hasPV(pvName).isOccupied()) {
                // drive is not present in local storage
                System.out.println("Error! Physical Volume does not exist or is occupied");
            }
//            else if () volume group does not exist
            else {
                // vg free to be used for pv'
                VolumeGroup vg = new VolumeGroup(vgName,UUIDGenerator.generator(),LocalHDStorage.hasPV(pvName));
                LocalHDStorage.hasPV(pvName).setOccupied(vg);
                // create physical volume below
                System.out.println("Successfully created Volume Group [" + vg.getName() + "]");
            }
        }
        if (msg.contains("lvcreate")) {
            String lvName = msg.split(" ")[1];
            String lvSize = msg.split(" ")[2];
            String vgName = msg.split(" ")[3];
        }*/
    }

}
