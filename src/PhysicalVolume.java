public class PhysicalVolume {
    private HardDrive hd;
    private String UUID;
    private String name;
    private boolean occupied;

    public PhysicalVolume(String name, String UUID, HardDrive hd) {
        this.hd = hd;
        this.UUID = UUID;
        this.name = name;
        occupied = false;
    }

    public void setOccupied() {
        this.occupied = true;
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
}
