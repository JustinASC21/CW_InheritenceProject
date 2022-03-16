import java.util.ArrayList;

public class VolumeGroup {
    private ArrayList<PhysicalVolume> phList;
    private ArrayList<LogicalVolume> lvList;

    public VolumeGroup(String name, ) {
        phList = new ArrayList<PhysicalVolume>();
        lvList = new ArrayList<LogicalVolume>();
    }

    public String returnTotalSize() {
        int total = 0;
        for (int i = 0; i < phList.size(); i ++){
            total += phList.get(i).getHd().getSizeAsInt();
        }
        return total + "G";
    }
}
