import java.util.*;

public class ATM {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        Transactions operation = new Transactions(500000);
        int con = 1;
        int cardId, pin, amount;

        while (con == 1) {
            System.out.println("\n\n==================== ATM TRANSACTION MENU ====================");
            System.out.println("1.  Withdraw money");
            System.out.println("2.  Deposit money");
            System.out.println("3.  Check balance");
            System.out.println("4.  View mini statement");
            System.out.println("5.  Update card pin");
            System.out.println("6.  View card warnings");
            System.out.println("7.  Exit");
            System.out.println("==============================================================");
            System.out.print("Enter your choice (1-7): ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("\nEnter the card ID: ");
                    cardId = sc.nextInt();

                    System.out.print("\nEnter the card pin: ");
                    pin = sc.nextInt();

                    System.out.print("\nEnter the amount: ");
                    amount = sc.nextInt();

                    operation.withdraw(cardId, pin, amount);
                    break;

                case 2:
                    System.out.print("\nEnter the card ID: ");
                    cardId = sc.nextInt();

                    System.out.print("\nEnter the card pin: ");
                    pin = sc.nextInt();

                    System.out.print("\nEnter the amount: ");
                    amount = sc.nextInt();

                    operation.deposit(cardId, pin, amount);
                    break;

                case 3:
                    System.out.print("\nEnter the card ID: ");
                    cardId = sc.nextInt();

                    System.out.print("\nEnter the card pin: ");
                    pin = sc.nextInt();

                    int balance = operation.balance(cardId, pin);
                    if (balance != -1) {
                        System.out.printf("\nThe available balance for card %d is: %d \n", cardId, balance);

                    }
                    break;

                case 4:
                    System.out.print("\nEnter the card ID: ");
                    cardId = sc.nextInt();

                    System.out.print("\nEnter the card pin: ");
                    pin = sc.nextInt();

                    System.out.println("\n---------- Mini statement for card " + cardId + " ----------");
                    operation.miniStatement(cardId, pin);
                    break;

                case 5:
                    System.out.print("\nEnter the card ID: ");
                    cardId = sc.nextInt();

                    System.out.print("\nEnter the current card pin: ");
                    pin = sc.nextInt();

                    System.out.print("\nEnter the new card pin: ");
                    int newPin = sc.nextInt();

                    operation.pinChange(cardId, pin, newPin);
                    break;

                case 6:
                    System.out.print("\nEnter the card ID: ");
                    cardId = sc.nextInt();

                    System.out.print("\nEnter the card pin: ");
                    pin = sc.nextInt();

                    System.out.println("\n---------- Warnings for card " + cardId + " ----------");
                    operation.viewWarnings(cardId, pin);
                    break;

                case 7:
                    System.out.println("\nThank you for using our ATM service.");
                    con = 0;
                    break;

                default:
                    System.out.println("Invalid choice! Please select between 1 and 7.");
                    break;
            }
        }

        sc.close();
    }
}