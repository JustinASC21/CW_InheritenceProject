import java.util.ArrayList;

public class LocalHDStorage {
    public static ArrayList<HardDrive> localHD = new ArrayList<HardDrive>();
    public static ArrayList<Volumes> externalVolumes = new ArrayList<Volumes>();

    public static void addDrive(HardDrive hd) {
        localHD.add(hd);
    }
    public static HardDrive hasHD(String name) {
        // if true, that means HD exists in local storage, if false then it doesnt exist or is being used
        for (int i = 0; i < localHD.size(); i++) {
            if (localHD.get(i).getName().equals(name)) {
                return localHD.get(i);
            }
        }
        return null;
    }
    public static Volumes hasObject(String name) {
        // if true, that means HD exists in local storage, if false then it doesnt exist or is being used
        for (int i = 0; i < externalVolumes.size(); i++) {
            if (externalVolumes.get(i).getName().equals(name)) {
                return externalVolumes.get(i);
            }
        }
        return null;
    }
    public static void displayDrives() {
        for (int i = 0; i < localHD.size(); i ++) {
            System.out.println(localHD.get(i));
        }
    }
    public static void addVolumes (Volumes volume) {
        externalVolumes.add(volume);
    }
    public static void displayVolumes(int volumeType) {
        // volumeType is either 1, 2 ,3  1 for physical volumes, 2 for volume groups, and 3 for logical groups
        for (int i = 0; i < externalVolumes.size(); i++) {
            Volumes obj = externalVolumes.get(i);
            switch (volumeType) {
                case 1:
                    if (obj instanceof PhysicalVolume) {
                        PhysicalVolume ph = (PhysicalhasVolume) obj;
                        System.out.println(ph);
                    }
                    break;
                case 2:
                    if (obj instanceof VolumeGroup) {
                        VolumeGroup vg = (VolumeGroup) obj;
                        System.out.println(vg);
                    }
                    break;
                case 3:
                    if (obj instanceof LogicalVolume) {
                        LogicalVolume lv = (LogicalVolume) obj;
                        System.out.println(lv);
                    }
            }


        }
    }

    }
