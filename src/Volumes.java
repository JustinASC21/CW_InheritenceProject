public class Volumes {
    private String UUID;
    private String name;
//    private String size;
//    No size needed >?

    public Volumes(String UUID, String name) {
        this.UUID = UUID;
        this.name = name;
//        this.size = size;
    }
//
//    public String getSize() {
//        return size;
//    }

    public String getUUID() {
        return UUID;
    }

    public String getName() {
        return name;
    }
}
