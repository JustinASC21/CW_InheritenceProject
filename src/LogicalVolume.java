public class LogicalVolume extends Volumes {
    private VolumeGroup superVG;
    private String vgName;

    public LogicalVolume(String name, String uuid, VolumeGroup vg, int size) {
        super(name,uuid,size);
        this.superVG = vg;
        vgName = vg.getName();
    }
    public LogicalVolume(String name, String uuid, String vgName, int size) {
        super(name,uuid,size);
        this.vgName = vgName;
    }

    public VolumeGroup getSuperVG() {
        return superVG;
    }

    public String toString() {
        return super.toString() + " [" + vgName + "] [" + this.getUUID() + "]";
    }

}
