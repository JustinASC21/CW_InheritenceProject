public class HardDrive {
    private String name;
    private String size;
    private boolean occupied;

    public HardDrive(String name, String size) {
        this.name = name;
        this.size = size;
        this.occupied = false;
    }

    public void setOccupied() {
        this.occupied = true;
    }

    public int getSizeAsInt() {
        return Integer.parseInt(size.substring(0,size.length() - 1));
    }

    public String getSize() {
        return size;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name + " [" + size + "]";
    }
}
