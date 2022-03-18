import java.util.ArrayList;
public class LocalHDStorage {
    public static ArrayList<HardDrive> localHD = new ArrayList<HardDrive>();
    public static ArrayList<PhysicalVolume> localPhysicalVolumes = new ArrayList<PhysicalVolume>();
    public static ArrayList<VolumeGroup> localVolumeGroups = new ArrayList<VolumeGroup>();

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
    public static PhysicalVolume hasPV(String name) {
        // if true, that means HD exists in local storage, if false then it doesnt exist or is being used
        for (int i = 0; i < localPhysicalVolumes.size(); i++) {
            if (localPhysicalVolumes.get(i).getName().equals(name)) {
                return localPhysicalVolumes.get(i);
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
    public static void displayPhysicalVol() {
        for (int i = 0; i < localPhysicalVolumes.size(); i++) {
            PhysicalVolume ph = localPhysicalVolumes.get(i);
            boolean isOccupied = ph.isOccupied();
            if (isOccupied) {
                System.out.println(ph.getName() + ": [" + ph.getHd().getSize() + "] [" + ph.getBigVolume().getName() + "] [" + ph.getUUID() + "]");
            }
            else {
                System.out.println(ph.getName() + ": [" + ph.getHd().getSize() + "] [" + ph.getUUID() + "]");
            }
        }
    }

    public static void addVolumeGroup(VolumeGroup vg) {
        localVolumeGroups.add(vg);
    }
//    public static void displayVolumeGroups() {
//        for (int i = 0; i < localVolumeGroups.size(); i++) {
//            VolumeGroup vol = localPhysicalVolumes.get(i);
//            System.out.println(vol.getName() + ": total: [" + vol.returnTotalSize() + "] available")
//        }
//
//        }
    }
