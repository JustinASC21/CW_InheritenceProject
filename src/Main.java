import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        if (FileReaderWriter.doesFileExist()) {
            FileReaderWriter.descript();
            System.out.println("Correctly set up disks!");
        }
        Scanner sc = new Scanner(System.in);
        System.out.print("cmd$: ");
        String input = sc.nextLine();
        while (!input.equals("exit")) {
            ParseCommands.parseMessage(input);
            System.out.print("cmd$: ");
            input = sc.nextLine();
        }
        FileReaderWriter.createFile();
        FileReaderWriter.writeToFile(LocalHDStorage.localHD,LocalHDStorage.externalVolumes);
        System.out.println("GoodBye!!!");
    }
}
