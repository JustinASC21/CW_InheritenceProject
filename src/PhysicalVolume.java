public class PhysicalVolume {
    private HardDrive hd;
    private String UUID;
    private String name;

    public PhysicalVolume(String name, String UUID, HardDrive hd) {
        this.hd = hd;
        this.UUID = UUID;
        this.name = name;
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


}
