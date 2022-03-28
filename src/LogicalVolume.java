public class LogicalVolume extends Volumes {
    private VolumeGroup superVG;

    public LogicalVolume(String name, String uuid, VolumeGroup vg, int size) {
        super(name,uuid,size);
        this.superVG = vg;
    }

    public toString()

}
