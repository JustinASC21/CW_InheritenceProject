public class PhysicalVolume extends Volumes {
    private HardDrive hd;
    private boolean occupied;
    private VolumeGroup bigVolume;
    private String VolumeName;

    public PhysicalVolume(String name, String UUID, HardDrive hd, int hdSize) {
        super(name,UUID,hdSize);
        this.hd = hd;
        occupied = false;
        bigVolume = null;
    }
    public PhysicalVolume(String name, String UUID, int hdSize, String bigV,boolean occupied) {
        super(name,UUID,hdSize);
        this.occupied = occupied;
        VolumeName = bigV;
    }

    public void setOccupied(VolumeGroup vg) {
        this.occupied = true;
        this.bigVolume = vg;
        VolumeName = vg.getName();
    }

    public HardDrive getHd() {
        return hd;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public VolumeGroup getBigVolume() {
        return bigVolume;
    }

    public String getVolumeName() {return VolumeName; }

    @Override
    public String toString() {
        if (isOccupied()) {
            return super.toString() + " [" + getVolumeName() + "] [" + getUUID() + "]";
        } else {
            return super.toString() + " [" + getUUID() + "]";
        }
    }
}
