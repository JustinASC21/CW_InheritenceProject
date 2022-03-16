import java.util.ArrayList;
public class LocalHDStorage {
    public static ArrayList<HardDrive> localHD = new ArrayList<HardDrive>();

    public static void addDrive(HardDrive hd) {
        localHD.add(hd);
    }
    public static boolean hasHD(String name) {
        // if true, that means HD exists in local storage, if false then it doesnt exist or is being used
        for (int i = 0; i < localHD.size(); i++) {
            if (localHD.get(i).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static void displayDrives() {
        for (int i = 0; i < localHD.size(); i ++) {
            System.out.println(localHD.get(i));
        }
    }

}
