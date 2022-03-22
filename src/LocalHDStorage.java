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
    public static void addPhysicalVolumes(PhysicalVolume ph) {
        externalVolumes.add(ph);
    }
    public static void displayVolumes() {
        for (int i = 0; i < externalVolumes.size(); i++) {
            Volumes obj = externalVolumes.get(i);
            if (obj instanceof PhysicalVolume) {
                PhysicalVolume ph = (PhysicalVolume) obj;
                boolean isOccupied = ph.isOccupied();
                if (isOccupied) {
                    System.out.println(ph.getName() + ": [" + ph.getHd().getSize() + "] [" + ph.getBigVolume().getName() + "] [" + ph.getUUID() + "]");
                }
                else {
                    System.out.println(ph.getName() + ": [" + ph.getHd().getSize() + "] [" + ph.getUUID() + "]");
                }
            }

        }
    }

//    public static void addVolumeGroup(VolumeGroup vg) {
//        localVolumeGroups.add(vg);
//    }
//    public static void displayVolumeGroups() {
//        for (int i = 0; i < localVolumeGroups.size(); i++) {
//            VolumeGroup vol = localPhysicalVolumes.get(i);
//            System.out.println(vol.getName() + ": total: [" + vol.returnTotalSize() + "] available")
//        }
//
//        }
    }
