import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class Transactions {
    int balance;
    int withAmt = 0;
    static final int maxWithAmount = 100000;
    int inaccuratePinCount = 0;

    Transactions(int amount) {
        this.balance = amount;
    }

    // Update the existing amount in an account
    public void updateAmount(int cardId, int pin, int newAmount) {
        int i = 1;
        try {
            File accounts = new File("Accounts.txt");
            Scanner sc = new Scanner(accounts);
            ArrayList<String> details = new ArrayList<>();
            while (sc.hasNextLine()) {
                if (i == cardId) {
                    details.add(cardId + "-" + pin + "-" + newAmount);
                    sc.nextLine();
                } else {
                    details.add(sc.nextLine());
                }
                i++;
            }
            FileWriter accountWriter = new FileWriter("Accounts.txt");
            accountWriter.write("");
            accountWriter.close();
            sc.close();

            accountWriter = new FileWriter("Accounts.txt", true);
            for (int j = 0; j < details.size(); j++) {
                if (j != details.size()) {
                    accountWriter.write(details.get(j) + "\n");
                } else {
                    accountWriter.write(details.get(j));
                }
            }
            accountWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

    // Send a warning
    public void warning(int cardID, String error) {
        try {
            FileWriter warningWriter = new FileWriter("Warnings.txt", true);
            LocalDate currentDate = LocalDate.now();
            warningWriter.write(cardID + "/" + currentDate + "/" + error + "\n");
            warningWriter.close();
            System.out.println("\nWarning! " + error);
            inaccuratePinCount = 0;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // Withdraw money
    public void withdraw(int cardID, int pin, int amount) {
        int i = 1;
        try {
            File accounts = new File("Accounts.txt");
            Scanner sc = new Scanner(accounts);
            while (i < cardID && sc.hasNextLine()) {
                sc.nextLine();
                i++;
            }
            if (sc.hasNextLine()) {
                String data = sc.nextLine();
                String info[] = data.split("-");

                int validCardPin = Integer.parseInt(info[1]);
                int accountBalance = Integer.parseInt(info[2]);

                if (validCardPin == pin) {
                    if (amount <= balance) {
                        if (amount <= accountBalance) {
                            if ((withAmt + amount) <= maxWithAmount) {
                                withAmt += amount;
                                System.out.println("Withdrawal successful. Please collect your cash.");

                                balance = balance - amount;

                                updateAmount(cardID, pin, (accountBalance - amount));

                                LocalDate currentDate = LocalDate.now();
                                FileWriter statementWriter = new FileWriter("Statement.txt", true);
                                statementWriter
                                        .write(cardID + "/" + currentDate + "/" + "withdrawal" + "/" + amount + "\n");
                                statementWriter.close();

                            } else {
                                System.out.println(
                                        "Transaction unsuccessful! Amount exceeds the maximum withdrawal limit. Permissible withdrawal amount: "
                                                + (maxWithAmount - withAmt));

                            }
                        } else {
                            System.out.println(
                                    "Transaction unsuccessful! Insufficient balance. Current Balance: "
                                            + accountBalance);
                        }
                    } else {
                        System.out.println("Sorry! The ATM is out of cash.");
                    }

                } else {
                    System.out.println("Inaccurate pin! Please try again.");
                    inaccuratePinCount++;
                    if (inaccuratePinCount == 3) {
                        warning(cardID, "Withdrawal tolerance exceeded.");
                    }
                }
            } else {
                System.out.println("Error! The entered card does not correspond to any registered account.");
            }

            sc.close();
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }

    }

    // Deposit money
    public void deposit(int cardID, int pin, int amount) {
        int i = 1;
        try {
            File accounts = new File("Accounts.txt");
            Scanner sc = new Scanner(accounts);
            while (i < cardID && sc.hasNextLine()) {
                sc.nextLine();
                i++;
            }
            if (sc.hasNextLine()) {
                String data = sc.nextLine();
                String info[] = data.split("-");

                int validCardPin = Integer.parseInt(info[1]);
                int currAmount = Integer.parseInt(info[2]);

                if (validCardPin == pin) {
                    int newAmount = currAmount + amount;
                    updateAmount(cardID, pin, newAmount);
                    LocalDate currentDate = LocalDate.now();
                    FileWriter statementWriter = new FileWriter("Statement.txt", true);
                    statementWriter.write(cardID + "/" + currentDate + "/" + "deposit" + "/" + amount + "\n");
                    statementWriter.close();
                    System.out.println("Deposit successful. Please check your balance.");
                } else {
                    System.out.println("Inaccurate pin! Please try again.");
                    inaccuratePinCount++;
                    if (inaccuratePinCount == 3) {
                        warning(cardID, "Deposit tolerance exceeded.");
                    }
                }
            } else {
                System.out.println("Error! The entered card does not correspond to any registered account.");
            }

            sc.close();
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }

    }

    // Check available balance
    public int balance(int cardID, int pin) {
        int i = 1;
        try {
            File accounts = new File("Accounts.txt");
            Scanner sc = new Scanner(accounts);
            while (i < cardID && sc.hasNextLine()) {
                sc.nextLine();
                i++;
            }
            if (sc.hasNextLine()) {
                String data = sc.nextLine();
                String info[] = data.split("-");

                int validCardPin = Integer.parseInt(info[1]);
                int accountBalance = Integer.parseInt(info[2]);

                if (validCardPin == pin) {
                    sc.close();
                    return accountBalance;
                } else {
                    System.out.println("Inaccurate pin! Please try again.");
                    inaccuratePinCount++;
                    if (inaccuratePinCount == 3) {
                        sc.close();
                        warning(cardID, "Balance inquiry tolerance exceeded.");
                    }
                    return -1;
                }
            } else {
                System.out.println("Error! The entered card does not correspond to any registered account.");
            }

            sc.close();
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
        return 0;
    }

    // Get mini statement
    public void miniStatement(int cardID, int pin) {
        int i = 1;
        try {
            File accounts = new File("Accounts.txt");
            Scanner sc1 = new Scanner(accounts);
            while (i < cardID && sc1.hasNextLine()) {
                sc1.nextLine();
                i++;
            }

            if (sc1.hasNextLine()) {
                String accountData = sc1.nextLine();
                String accountInfo[] = accountData.split("-");

                int validCardPin = Integer.parseInt(accountInfo[1]);

                if (validCardPin == pin) {
                    File statement = new File("Statement.txt");
                    Scanner sc = new Scanner(statement);
                    while (sc.hasNextLine()) {
                        String data = sc.nextLine();
                        String info[] = data.split("/");
                        if (Integer.parseInt(info[0]) == cardID) {
                            System.out.printf("\nDate: %s, Operation: %s, Amount: %s\n", info[1], info[2], info[3]);
                        }
                    }
                    sc.close();

                } else {
                    System.out.println("Inaccurate pin! Please try again.");
                    inaccuratePinCount++;
                    if (inaccuratePinCount == 3) {
                        warning(cardID, "Mini statement retrieval tolerance exceeded.");
                    }
                }
            } else {
                System.out.println("Error! The entered card does not correspond to any registered account.");
            }

            sc1.close();
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }

    }

    // update card pin
    public void updateCardPin(int cardID, int newPin, int amount) {
        int i = 1;
        try {
            File accounts = new File("Accounts.txt");
            Scanner sc = new Scanner(accounts);
            ArrayList<String> details = new ArrayList<>();
            while (sc.hasNextLine()) {
                if (i == cardID) {
                    details.add(cardID + "-" + newPin + "-" + amount);
                    sc.nextLine();
                } else {
                    details.add(sc.nextLine());
                }
                i++;
            }
            FileWriter accountWriter = new FileWriter("Accounts.txt");
            accountWriter.write("");
            accountWriter.close();
            sc.close();

            accountWriter = new FileWriter("Accounts.txt", true);
            for (int j = 0; j < details.size(); j++) {
                if (j != details.size()) {
                    accountWriter.write(details.get(j) + "\n");
                } else {
                    accountWriter.write(details.get(j));
                }
            }
            accountWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

    // Change ATM card pin
    public void pinChange(int cardID, int currPin, int newPin) {
        int i = 1;
        try {
            File accounts = new File("Accounts.txt");
            Scanner sc = new Scanner(accounts);
            while (i < cardID && sc.hasNextLine()) {
                sc.nextLine();
                i++;
            }
            if (sc.hasNextLine()) {
                String data = sc.nextLine();
                String info[] = data.split("-");

                int validCardPin = Integer.parseInt(info[1]);
                int currentAmount = Integer.parseInt(info[2]);

                if (validCardPin == currPin) {
                    updateCardPin(cardID, newPin, currentAmount);
                    System.out.println("Pin updated successfully.");

                } else {
                    System.out.println("Inaccurate pin! Please try again.");
                    inaccuratePinCount++;
                    if (inaccuratePinCount == 3) {
                        warning(cardID, "Pin update request tolerance exceeded.");
                    }
                }
            } else {
                System.out.println("Error! The entered card does not correspond to any registered account.");
            }
            sc.close();

        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }

    }

    // View warnings
    public void viewWarnings(int cardID, int pin) {
        int i = 1;
        try {
            File accounts = new File("Accounts.txt");
            Scanner sc1 = new Scanner(accounts);
            while (i < cardID && sc1.hasNextLine()) {
                sc1.nextLine();
                i++;
            }

            if (sc1.hasNextLine()) {

                String accountData = sc1.nextLine();
                String accountInfo[] = accountData.split("-");

                int validCardPin = Integer.parseInt(accountInfo[1]);

                if (validCardPin == pin) {
                    File statement = new File("Warnings.txt");
                    Scanner sc = new Scanner(statement);
                    while (sc.hasNextLine()) {
                        String data = sc.nextLine();
                        String info[] = data.split("/");
                        if (Integer.parseInt(info[0]) == cardID) {
                            System.out.printf("\nDate: %s, Warning: %s\n", info[1], info[2]);
                        }
                    }
                    sc.close();

                } else {
                    System.out.println("Inaccurate pin! Please try again.");
                    inaccuratePinCount++;
                    if (inaccuratePinCount == 3) {
                        warning(cardID, "View warning tolerance exceeded.");
                    }
                }

            } else {
                System.out.println("Error! The entered card does not correspond to any registered account.");
            }

            sc1.close();
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }

    }

}
