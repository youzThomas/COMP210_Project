package assn07;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String,String> passwordManager = new PasswordManager<>();

        boolean isAuthenticated = false;
        while (!isAuthenticated) {
            System.out.println("Enter Master Password");
            String enteredPassword = scanner.nextLine();
            isAuthenticated = passwordManager.checkMasterPassword(enteredPassword);
        }

        while (true) {
            System.out.println("Enter command:");
            String command = scanner.nextLine().trim();

            if (command.equalsIgnoreCase("Exit")) {
                System.out.println("Exiting...");
                break;
            }

            String[] commandParts = command.split(" ");

            switch (commandParts[0].toLowerCase()) {
                case "new password":
                    if (commandParts.length == 3) {
                        String website = commandParts[1];
                        String password = commandParts[2];
                        passwordManager.put(website, password);
                        System.out.println("New password added");
                    } else {
                        System.out.println("Invalid command format for 'New password'");
                    }
                    break;

                case "get password":
                    if (commandParts.length == 2) {
                        String website = commandParts[1];
                        String result = passwordManager.get(website);
                        if (result == null) {
                            System.out.println("Account does not exist");
                        } else {
                            System.out.println(result);
                        }
                    } else {
                        System.out.println("Invalid command format for 'Get password'");
                    }
                    break;

                case "delete account":
                    if (commandParts.length == 2) {
                        String website = commandParts[1];
                        String removedPassword = passwordManager.remove(website);
                        if (removedPassword == null) {
                            System.out.println("Account does not exist");
                        } else {
                            System.out.println("Account deleted");
                        }
                    } else {
                        System.out.println("Invalid command format for 'Delete account'");
                    }
                    break;

                case "check duplicate password":
                    if (commandParts.length == 2) {
                        String password = commandParts[1];
                        var duplicateWebsites = passwordManager.checkDuplicate(password);
                        if (duplicateWebsites.isEmpty()) {
                            System.out.println("No account uses that password");
                        } else {
                            System.out.println("Websites using that password:");
                            for (String website : duplicateWebsites) {
                                System.out.println(website);
                            }
                        }
                    } else {
                        System.out.println("Invalid command format for 'Check duplicate password'");
                    }
                    break;

                case "get accounts":
                    System.out.println("Command 'Get accounts' not implemented");
                    break;

                case "generate random password":
                    System.out.println("Command 'Generate random password' not implemented");
                    break;

                default:
                    System.out.println("Command not found");
                    break;
            }
        }
    }
        // your code below

        // infinite loop to go back to "Enter master password"


        // loop to read and execute commands until "Exit" is entered
}
