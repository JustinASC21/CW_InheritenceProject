import java.util.ArrayList;

public class VolumeGroup extends Volumes {
    private ArrayList<PhysicalVolume> phList;
    private ArrayList<LogicalVolume> lvList;

    public VolumeGroup(String name, String uuid, PhysicalVolume pv ) {
        super(name,uuid);
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
