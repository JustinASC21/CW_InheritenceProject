import java.io.File;  // Import the File class
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FileReaderWriter {

    public static void createFile() {
        File obj = new File("virtualfloppydisk.txt");

        try {
            if (obj.createNewFile()) {
                System.out.println("Virtual disks created!");
            } else {
                System.out.println("File exists: Updating virtual disks...");
            }
        }
        catch ( IOException e) {
            System.out.println("An error occurred.");
        }

    }

    public static void writeToFile(ArrayList<HardDrive> hd, ArrayList<Volumes> volumes) {
        String fileName = "virtualfloppydisk.txt";
        try {
            FileWriter transcribe = new FileWriter(fileName);
            for (int i = 0; i < hd.size(); i ++) {
                transcribe.write("HD: " + hd.get(i) + " " + hd.get(i).isOccupied() + "\n");
            }
            for (int i = 0; i < volumes.size(); i ++) {
                Volumes v = volumes.get(i);
                if (v instanceof PhysicalVolume) {
                    transcribe.write("PV: " + volumes.get(i) + " " + ((PhysicalVolume) volumes.get(i)).isOccupied() + "\n");
                }
                else if (v instanceof VolumeGroup) {
                    transcribe.write("VG: " + volumes.get(i) + "\n");
                }
                else if (v instanceof LogicalVolume) {
                    transcribe.write("LV: " + volumes.get(i) + "\n");
                }
            }

            transcribe.close();
        }
        catch (IOException e) {
            System.out.println("Error");
        }

    }

    public static boolean doesFileExist() {
            File obj = new File("virtualfloppydisk.txt");
            return obj.exists();
    }

    public static void descript() {
        try {
            File myObj = new File("virtualfloppydisk.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                classifyDisks(data);
            }
            System.out.println("Successfully booted drives");
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    private static void classifyDisks(String str) {
        String label = str.substring(0,str.indexOf(":"));
        String[] splitAttr = str.split(" ");
            if (label.equals("HD")) {

                // recreate harddrive
                String name = splitAttr[1];
                String size = splitAttr[2].substring(1, splitAttr[2].length() - 1);
                boolean occupied = false;
                if (splitAttr[3].equals("true"))
                    occupied = true;
                LocalHDStorage.addDrive(new HardDrive(name, size, occupied));
            }
            else if (label.equals("PV")) {

                // recreate physical drive
                String nameP = splitAttr[1].substring(0, splitAttr[1].length() - 1);
                String sizeP = splitAttr[2].substring(1, splitAttr[2].length() - 2);
                if (splitAttr[splitAttr.length - 1].equals("true")) { // last item either true or false
                    String bigVName = splitAttr[3].substring(1, splitAttr[3].length() - 1);
                    String uuidP = splitAttr[4].substring(1, splitAttr[4].length() - 1);
                    LocalHDStorage.addVolumes(new PhysicalVolume(nameP, uuidP, Integer.parseInt(sizeP), bigVName, true));
                } else {
                    String uuidP = splitAttr[3].substring(1, splitAttr[3].length() - 1);
                    LocalHDStorage.addVolumes(new PhysicalVolume(nameP, uuidP, Integer.parseInt(sizeP), "", false));
                }
            }
            else if (label.equals("VG")) {
                String nameV = splitAttr[1].substring(0, splitAttr[1].length() - 1);
                String sizeV = splitAttr[3].substring(1, splitAttr[3].length() - 2);
                String uuidV = splitAttr[splitAttr.length - 1].substring(1, splitAttr[splitAttr.length - 1].length() - 1);
                String pvList = splitAttr[6];
                String available = splitAttr[5].substring(1,splitAttr[5].length() - 2);
                LocalHDStorage.addVolumes(new VolumeGroup(nameV,uuidV,Integer.parseInt(sizeV),Integer.parseInt(available),pvList));
            }
            else if (label.equals("LV")) {
                String nameL = splitAttr[1].substring(0,splitAttr[1].length() - 1);
                String sizeL = splitAttr[2].substring(1,splitAttr[2].length() - 2);
                String vgL = splitAttr[3].substring(1,splitAttr[3].length() - 1);
                String uuidL = splitAttr[4].substring(1,splitAttr[4].length() - 1);
                LocalHDStorage.addVolumes(new LogicalVolume(nameL,uuidL,vgL,Integer.parseInt(sizeL)));
            }
        }


    }

