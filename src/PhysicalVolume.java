public class PhysicalVolume extends Volumes {
    private HardDrive hd;
    private boolean occupied;
    private VolumeGroup bigVolume;

    public PhysicalVolume(String name, String UUID, HardDrive hd) {
        super(name,UUID);
        this.hd = hd;
        occupied = false;
        bigVolume = null;
    }

    public void setOccupied(VolumeGroup vg) {
        this.occupied = true;
        this.bigVolume = vg;
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
}
