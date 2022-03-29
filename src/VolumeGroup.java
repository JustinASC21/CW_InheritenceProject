import java.util.ArrayList;

public class VolumeGroup extends Volumes {
    private ArrayList<PhysicalVolume> phList;
    private ArrayList<LogicalVolume> lvList;

    public VolumeGroup(String name, String uuid, PhysicalVolume pv, int pvSize) {
        super(name,uuid,pvSize);
        phList = new ArrayList<PhysicalVolume>();
        lvList = new ArrayList<LogicalVolume>();
    }
    public int getTotalSpace() {
        int totalSpace = 0;
        for (PhysicalVolume pv: phList) {
            totalSpace += pv.getSize();
        }
        return totalSpace;
    }
    public int getAvailableSpace() {
        int takenSpace = 0;
        for (LogicalVolume lv : lvList) {
            takenSpace += lv.getSize();
        }
        return getTotalSpace() - takenSpace;
    }
    public String PVListAsString() {
        String res = "[";
        for (PhysicalVolume ph : phList) {
            res += ph.getName() + ",";
        }
        res = res.substring(0,res.length() - 1) + "]";
        return res;
    }
    public void addPVList(PhysicalVolume pv) {
        phList.add(pv);
    }
    public void addLVList(LogicalVolume lv) {
        lvList.add(lv);
    }

    public String toString() {
        return getName() + ": total: [" + getTotalSpace() + "G] available: [" + getAvailableSpace() + "G] " + PVListAsString() + " [" +getUUID() + "]";

    }
}
