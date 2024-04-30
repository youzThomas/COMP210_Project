package assn07;

import java.util.Scanner;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String,String> passwordManager = new PasswordManager<>();

        while (true) {
            System.out.println("Enter Master Password");
            String enteredPassword = scanner.nextLine();
            if (passwordManager.checkMasterPassword(enteredPassword)){
                break;
            }
        }

        while (true) {
            String Command_1;
            String Command_2;
            Command_1 = scanner.nextLine();
            if (Command_1.equals("Exit")){
                break;
            }

            switch (Command_1){
                case ("New password"):
                    Command_1 = scanner.nextLine();
                    Command_2 = scanner.nextLine();
                    passwordManager.put(Command_1, Command_2);
                    System.out.println("New password added");
                    break;

                case ("Get password"):
                    Command_1 = scanner.nextLine();
                    String password = passwordManager.get(Command_1);
                    if (password == null) {
                        System.out.println("Account does not exist");
                    } else {
                        System.out.println(password);
                    }
                    break;

                case ("Delete account"):
                    Command_1 = scanner.nextLine();
                    String d = passwordManager.remove(Command_1);
                    if (d == null){
                        System.out.println("Account does not exist");
                    } else {
                        System.out.println("Account deleted");
                    }
                    break;

                case ("Check duplicate password"):
                    Command_1 = scanner.nextLine();
                    List<String> e = passwordManager.checkDuplicate(Command_1);
                    if (e.isEmpty()){
                        System.out.println("No account uses that password");
                    } else {
                        System.out.println("Websites using that password:");
                        for (int i = 0; i < e.size(); i++){
                            System.out.println(e.get(i));
                        }
                    }
                    break;

                case ("Get accounts"):
                    Set<String> accounts = passwordManager.keySet();
                    System.out.println("Your accounts:");
                    for (String element : accounts) {
                        System.out.println(element);
                    }
                    break;
                case ("Generate random password"):
                    int length = Integer.parseInt(scanner.nextLine());
                    System.out.println(passwordManager.generatesafeRandomPassword(length));
                    break;
                default:
                    System.out.println("Command not found");
                    break;
            }
        }
    }
}
