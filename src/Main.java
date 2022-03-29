import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("cmd$: ");
        String input = sc.nextLine();
        while (!input.equals("exit")) {
            ParseCommands.parseMessage(input);
            System.out.print("cmd$: ");
            input = sc.nextLine();
        }
        System.out.println("GoodBye!!!");
    }
}
