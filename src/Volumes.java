public class Volumes {
    private String UUID;
    private String name;
    private int size;
//    No size needed >?

    public Volumes(String name, String UUID, int size) {
        this.UUID = UUID;
        this.name = name;
        this.size = size;
    }

    public void addSize(int amt) {this.size += amt;}


    public int getSize() {
        return size;
    }

    public String getUUID() {
        return UUID;
    }

    public String getName() {
        return name;
    }

}
