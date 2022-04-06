import java.util.ArrayList;

public class VolumeGroup extends Volumes {
    private ArrayList<PhysicalVolume> phList;
    private ArrayList<LogicalVolume> lvList;
    private int totalSpace;
    private int leftSpace;
    private String pvListStr;

    public VolumeGroup(String name, String uuid, PhysicalVolume pv, int pvSize) {
        super(name,uuid,pvSize);
        phList = new ArrayList<PhysicalVolume>();
        lvList = new ArrayList<LogicalVolume>();
        totalSpace = 0;
        leftSpace = 0;
        pvListStr = null;
    }
    public VolumeGroup(String name, String uuid, int pvSize, int logicalSize,String list) {
        super(name,uuid,pvSize);
        phList = new ArrayList<PhysicalVolume>();
        lvList = new ArrayList<LogicalVolume>();
        totalSpace = pvSize;
        leftSpace = pvSize - logicalSize;
        pvListStr = list;
    }
    public int getTotalSpace() {
        int minitotalSpace = 0;
        for (PhysicalVolume pv: phList) {
            minitotalSpace += pv.getSize();
        }
        this.totalSpace = minitotalSpace;
        return totalSpace;
    }
    public int getAvailableSpace() {
        int takenSpace = 0;
        for (LogicalVolume lv : lvList) {
            takenSpace += lv.getSize();
        }
        this.leftSpace = getTotalSpace() - takenSpace;
        return getTotalSpace() - takenSpace;
    }
    public String PVListAsString() {
        String res = "[";
        for (PhysicalVolume ph : phList) {
            res += ph.getName() + ",";
        }
        pvListStr = res.substring(0,res.length() - 1) + "]";
        return pvListStr;
    }
    public void setPvListStr(String str) {
        pvListStr = str;
    }
    public void addPVList(PhysicalVolume pv) {
        phList.add(pv);
        pvListStr = pvListStr.substring(0,pvListStr.length() - 1) + "," + PVListAsString().substring(1);
    }
    public void addLVList(LogicalVolume lv) {
        lvList.add(lv);
    }

    public String toString() {
        if (totalSpace != 0 && leftSpace != 0)
        return getName() + ": total: [" + totalSpace + "G] available: [" + leftSpace + "G] " + pvListStr + " [" +getUUID() + "]";
        else
        return getName() + ": total: [" + getTotalSpace() + "G] available: [" + getAvailableSpace() + "G] " + pvListStr + " [" +getUUID() + "]";


    }
}
