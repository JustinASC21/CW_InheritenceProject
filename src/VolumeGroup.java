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
        for (LogicalVolume lv: lvList) {
//            lv
        }
        return 0;
    }
    public int getAvailableSpace() {
        return getTotalSpace() - super.getSize();
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
        super.addSize(pv.getSize());
    }
    public void addLVList(LogicalVolume lv) {
        lvList.add(lv);
    }
//    public String returnTotalSize() {
//        int total = 0;
//        for (int i = 0; i < phList.size(); i ++){
//            total += phList.get(i).getHd().getSizeAsInt();
//        }
//        return total + "G";
//    }

}
