public class LogicalVolume extends Volumes {
    private VolumeGroup superVG;

    public LogicalVolume(String name, String uuid, VolumeGroup vg, int size) {
        super(name,uuid,size);
        this.superVG = vg;
    }

    public VolumeGroup getSuperVG() {
        return superVG;
    }

    public String toString() {
        return super.toString() + " [" + superVG.getName() + "] [" + this.getUUID() + "]";
    }

}
