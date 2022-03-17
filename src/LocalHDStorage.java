import java.util.ArrayList;
public class LocalHDStorage {
    public static ArrayList<HardDrive> localHD = new ArrayList<HardDrive>();
    public static ArrayList<PhysicalVolume> localPhysicalVolumes = new ArrayList<PhysicalVolume>();

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

    public static void displayDrives() {
        for (int i = 0; i < localHD.size(); i ++) {
            System.out.println(localHD.get(i));
        }
    }
    public static void addPhysicalVolumes(PhysicalVolume ph) {
        localPhysicalVolumes.add(ph);
    }
    public static boolean hasPhysicalVolume(String name) {
        for (int i = 0; i < localPhysicalVolumes.size(); i++) {
            if (localPhysicalVolumes.get(i).getName().equals(name)) {
                return true;
            }
        }
        return false;    }

    public static void displayPhysicalVol() {
        for (int i = 0; i < localPhysicalVolumes.size(); i++) {
            PhysicalVolume ph = localPhysicalVolumes.get(i);
            boolean isOccupied = ph.isOccupied();
//            if (isOccupied) {
                System.out.println(ph.getName() + ": [" + ph.getHd().getSize() + "] " + ph.getHd().isOccupied());
//            }
//            else {

//            }
        }
    }
}
