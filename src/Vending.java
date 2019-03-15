
import java.util.Scanner;

/**
 *
 * This is my own work: Michael Mussler 
 * 9/30/2015 
 * Program #3 Vending machine
 */
public class Vending {

//PROVIDED
    public static void main(String[] args) {
        VendingMachine chaChing = new VendingMachine();
        chaChing.run();
    }
}

/**
 * *******************************************************
 */
class VendingMachine {

    //PROVIDED
    private Dispenser snackMachine;
    private Coinbox moneyBox;

    //PROVIDED
    public VendingMachine() {
        snackMachine = new Dispenser();
        moneyBox = new Coinbox(0, 3, 3);
    }

    //PROVIDED
    public void run() {
        Scanner kybd = new Scanner(System.in);
        boolean quit = false;
        showUserChoice();
        do {
            //showUserChoice();
            String choice = kybd.next();
            if (choice.equals("BOSS")) {
                quit = this.bossWork();
            } else {
                serviceCustomer(choice.charAt(0));
            }
        } while (!quit);
    }

    private void showUserChoice() {
        System.out.println("");
        snackMachine.toString();
        moneyBox.displayCoins();
        System.out.println("Enter money first and then select product: ");
        }

    private void serviceCustomer(char choice) {
        moneyBox.Option(choice);
            if(moneyBox.getAmount()>=snackMachine.getPrice(choice)){
                if(snackMachine.inStock(choice)){
                System.out.println("Here is your ");
                moneyBox.giveChange(choice);
            }
        }
    }

    private boolean bossWork() {
        Scanner k = new Scanner(System.in);
        System.out.println("WELCOME BOSS\n"  +
        "1	Add products\n" +
        "2	Restock products\n" +
        "3	Change price\n" +
        "4	Remove product\n" +
        "5	Shutdown\n" +
        "6	Start machine     ");
        
      
      
        
        
        
        return false;
    }

}

/**
 * *******************************************************
 */
class Product {

    private String name;
    private int quantity;
    private double price;

    //initialize name,price,and quantity.
    public Product() {
        this.name = "";
        this.quantity = 0;
        this.price = 0;
    }

    //initialize all fields 
    public Product(String name, double price, int quantity) {
        this.quantity = quantity;
        this.price = price;
        this.name = name;

    }

    //set name
    public void setName(String n) {
        this.name = n;
    }

    //set Price
    public void setPrice(double p) {
        this.price = p;
    }

    //set quantity
    public void setQuantity(int q) {
        this.quantity = q;
    }

    //get name
    public String getName() {
        return this.name;
    }

    //get quantity
    public int getQuantity() {
        return this.quantity;

    }

    //get price
    public double getPrice() {
        return this.price;
    }

    //display a string name,price, quantity
    public String toString() {
        return "(" + this.name + ", " + this.price + ", "
                + this.quantity + ")";

    }

}

/**
 * *******************************************************
 */
class Coinbox {

    //PROVIDED
    private int numQ, numD, numN;
    private int amount;

    //PROVIDED
    public Coinbox(int Q, int D, int N) {
        this.numQ = Q;
        this.numD = D;
        this.numN = N;
        this.amount = 0;
    }

    public void giveChange(int change) {
        int Q = 0, D = 0, N = 0;
        //change is >0 and correct change is false 
        if (change > 0 && !this.correctChange()) {
            
            Q = change / 25;
            
            if (Q > numQ) {
                
                Q = numQ;
            }
            // update change due 
            change -= Q * 25;
            //reduce coins in box 
            numQ -= Q;

            D = change / 10;
            if (D > numD) {
                D = numD;
            }
            change -= 10 * D;
            numD -= D;

            N = change / 5;
            numN -= N;
        }

        // reset amount to 0
        this.amount = 0;
        //dsiplay
        System.out.println("Now returning " + Q
                + " Quarters, " + D
                + " nickles.");
    }

    //checks for correct amount of change.
    public boolean correctChange() {
        if (this.numN < 1 || this.numD * 10 + this.numN * 5 < 20) {
            return true;
        } else {
            return false;
        }
    }

    //evauluates id quarters,dimes, nickles, or give money back
    public boolean Option(char choice) {
        if (choice == 'Q'
                || choice == 'D'
                || choice == 'N') {
            this.takeCoin(choice);
            this.displayAmount();
            return true;
        } else if (choice == 'R') {
            this.giveChange(this.amount);
            return false;
        }
        return false;
    }

    // display coinbox menu
    public void displayCoins() {
        System.out.println("This machine takes quarters, dimes, and nickels.");
        System.out.println("Enter Q, D, N, or R for coin return.");

    }

    //return amount deposited 
    public int getAmount() {
        
        return this.amount;
        
    }

    //determins what coin was used and total amount from transaction
    private void takeCoin(char coin) {
        if (coin == 'Q') {
            numQ += 1;
            amount += 25;
        }
            if (coin == 'D') {
                numD += 1;
                amount += 10;
            }
                if (coin == 'N') {
                    numN += 1;
                    amount += 5;
                    
                }

            }
        

    
    //return amount deposited
    private void displayAmount() {
        System.out.println("Total amount disposited: "+ this.amount+" Cents");

    }

}

/**
 * *******************************************************
 */
class Dispenser {

    private Product[] items;
    private int numItems;

    //Allocate the array size and intialize numItems
    public Dispenser() {
        this.numItems = 0;
        items = new Product[5];

    }

    @Override
    //return string that contains the products in a numberlist
    public String toString() {
        String result = "";
        for (int i = 0; i < numItems; i++) {
            result = i + 1 + ") " + items[i] + "\n";
        }
        return result;
    }

    //evaluates choice
    public boolean option(char choice) {
        if (choice >= 1 && choice <= numItems) {
            return true;
        }
        return false;
    }

    //allownBoss to enter name,price,and quantity 
    public void setUpDispenser() {
        Scanner k = new Scanner(System.in);
        if (numItems < items.length) {
            System.out.println("Enter name, price, and quantity:");
            this.items[numItems] = new Product(k.nextLine(), k.nextDouble(), +k.nextInt());
            numItems++;
        }
    }

    //display products and allow boss to change price
    public void changePrice() {
        Scanner k = new Scanner(System.in);
        int item = 0;
        double newPrice = 0;
        System.out.println(items.toString());
        System.out.println("Enter product number: ");
        item = k.nextInt();
        System.out.println("Enter new price: ");
        newPrice = k.nextDouble();

        items[item].setPrice(newPrice);

    }

    //display prodcts and allow boss to add items 
    public void restockeProduct() {
        Scanner k = new Scanner(System.in);
        int item = 0;
        double newQuantity = 0;
        System.out.println(items.toString());
        System.out.println("Enter product number: ");
        item = k.nextInt();
        System.out.println("Enter number of added product: ");
        newQuantity = k.nextDouble();

        items[item].setPrice(items[item].getQuantity() + newQuantity);

    }

    //Return the price of the selected
    public double getPrice(char choice) {
        if (choice <= numItems) {
            return items[choice].getPrice();
        }
        return -1;

    }

    //Returns true if the choice is valid
    public boolean inStock(char choice) {
        if (choice <= numItems && items[choice].getQuantity() > 0) {
            return true;
        }
        return false;
    }

    //If in stock give customer the product 
    public int dispense(char choice) {
        if (inStock(choice)) {
            return 1;
        }
        return 0;
    }

    //Allow Boss to delete a product
    public void deletProduct() {
        int delete = 0;
        Scanner k = new Scanner(System.in);
        System.out.println(items.toString());
        System.out.println("Enter which product number you would like to delete: ");
        delete = k.nextInt();

        if (delete == numItems) {
            numItems--;
        } else {
            for (int i = delete; i < numItems; i++) {
                items[i - 1] = items[i];
            }
            numItems--;
        }
    }
}
