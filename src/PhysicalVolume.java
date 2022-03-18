public class PhysicalVolume {
    private HardDrive hd;
    private String UUID;
    private String name;
    private boolean occupied;
    private VolumeGroup bigVolume;

    public PhysicalVolume(String name, String UUID, HardDrive hd) {
        this.hd = hd;
        this.UUID = UUID;
        this.name = name;
        occupied = false;
        bigVolume = null;
    }

    public void setOccupied(VolumeGroup vg) {
        this.occupied = true;
        this.bigVolume = vg;
    }

    public String getName() {
        return name;
    }

    public String getUUID() {
        return UUID;
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
