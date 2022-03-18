import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("cmd$: ");
            String input = sc.nextLine();
            ParseCommands.parseMessage(input);
        }
    }
}
