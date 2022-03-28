public class PhysicalVolume extends Volumes {
    private HardDrive hd;
    private boolean occupied;
    private VolumeGroup bigVolume;

    public PhysicalVolume(String name, String UUID, HardDrive hd, int hdSize) {
        super(name,UUID,hdSize);
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

    @Override
    public String toString() {
        if (isOccupied()) {
            return getName() + ": [" + getHd().getSize() + "] [" + getBigVolume().getName() + "] [" + getUUID() + "]";
        } else {
            return getName() + ": [" + getHd().getSize() + "] [" + getUUID() + "]";
        }
    }
}
