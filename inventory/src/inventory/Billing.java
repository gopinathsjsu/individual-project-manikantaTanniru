package inventory;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Billing {

    private static ArrayList<Inventory> loadInventory(String inventoryFilename) {
        ArrayList<Inventory> data = new ArrayList<>();
        try {
            File file = new File(inventoryFilename);
            Scanner sc = new Scanner(file);
            sc.nextLine();
            while (sc.hasNextLine()) {
                String line = sc.nextLine().replace("\"", "");
                String[] lineData = line.split(",");
                Inventory inventory = new Inventory(lineData[1].trim(), lineData[0].trim(),
                        Integer.parseInt(lineData[2].trim()), Double.parseDouble(lineData[3].trim()));
                data.add(inventory);
            }

        } catch (FileNotFoundException ex) {
            System.err.println("Inventory Error: " + ex.getMessage());
        }
        return data;
    }

    private static ArrayList<CardDetails> loadCards(String cardsFilename) {
        ArrayList<CardDetails> data = new ArrayList<>();
        File file = new File(cardsFilename);
        try {
            Scanner sc = new Scanner(file);
            sc.nextLine();
            while (sc.hasNextLine()) {
                String[] lineData = sc.nextLine().split(",");
                CardDetails card = new CardDetails(lineData[0]);
                data.add(card);
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Cards Error: " + ex.getMessage());
        }

        return data;
    }

    private static ArrayList<Order> loadOrders(String ordersFilename) {
        ArrayList<Order> data = new ArrayList<>();

        try {
            File file = new File(ordersFilename);
            Scanner sc = new Scanner(file);
            sc.nextLine();
            int count = 0;
            String cardnumber = "mani";
            while (sc.hasNextLine()) {
                String line = sc.nextLine().replace("\"", "");
                String[] lineData = line.split(",");
                if (count <= 0) {
                    Order order = new Order(lineData[0], Integer.parseInt(lineData[1]), lineData[2]);
                    count = count + 1;
                    cardnumber = lineData[2];
                    data.add(order);
                } else {
                    Order order = new Order(lineData[0], Integer.parseInt(lineData[1]), cardnumber);
                    data.add(order);
                }
            }
        }catch (FileNotFoundException ex) {
            System.err.println("Orders Error: " + ex.getMessage());
        }

        return data;
    }

    public static void writeToLog(String content) {
        FileWriter writer = null;
        try {
            File file = new File("logs.txt");
            writer = new FileWriter(file,true);
            BufferedWriter out = new BufferedWriter(writer);
            out.write("Please correct quantities.\n");
            out.write(content + "\n");
            out.flush();
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(Billing.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(Billing.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static boolean cardExists(ArrayList<CardDetails> cards, String cardNumber) {
        boolean status = false;
        for (CardDetails c : cards) {
            if (c.getCardNumber().equals(cardNumber)) {
                status = true;
                break;
            }
        }

        return status;
    }



    public static void updateCardNumbers(String cardNumber) throws IOException{
        FileWriter pw = new FileWriter("Cards.csv", true);
        pw.append(cardNumber + ",");
        pw.append("\n");
        pw.flush();
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        double amountPaid = 0;
        // load inventory
        System.out.println(">> Load dataset file....");
        ArrayList<Inventory> inventory = loadInventory("Dataset.csv");
        System.out.println("Dataset loading complete!\n");
        // load cards
        System.out.println(">> Load cards data....");
        ArrayList<CardDetails> cards = loadCards("Cards.csv");
        System.out.println("Cards data loading complete!\n");
        // load input file

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a file name: ");
        String fileName = scan.nextLine();

//        System.out.println(">> Load Sample input file data....");
        ArrayList<Order> orders = loadOrders(fileName);
        System.out.println("Sample input data loading complete!\n");

        String ListItems = "";
        // process input file: validations
//        System.out.println(">> Processing input...");
        for (Order or : orders) {

            // validate order
            // Essentials > 3, luxury > 4, Misc > 6
            for (Inventory iv : inventory) {
                if (or.getItemName().equalsIgnoreCase(iv.getItemName())) {
                    if (iv.getCategory().equalsIgnoreCase("essentials") && iv.getQuantity() >= or.getQuantity()
                            && or.getQuantity() <= 3) {
                        // process order
                        double price = iv.getPrice() * or.getQuantity();
                        ListItems += "Item: " + or.getItemName()  +", " + "Quantity: " + or.getQuantity() +", "+ "Price: " + price +", " +"\n";
                        if (!cardExists(cards, or.getCardNumber())){
                            updateCardNumbers(or.getCardNumber());
                        }
                        amountPaid += price;
                        System.out.println("Essentials: " + price);
                        break;
                    } else if (iv.getCategory().equalsIgnoreCase("luxury") && iv.getQuantity() >= or.getQuantity()
                            && or.getQuantity() <= 4) {
                        // process order
                        double price = iv.getPrice() * or.getQuantity();
                        ListItems += "Item: " + or.getItemName()  +", " + "Quantity: " + or.getQuantity() +", "+ "Price: " + price +", " +"\n";
                        amountPaid += price;
                        System.out.println("Luxury: " + price);
                        if (!cardExists(cards, or.getCardNumber())){
                            updateCardNumbers(or.getCardNumber());
                        }
                        break;
                    } else if (iv.getCategory().equalsIgnoreCase("misc") && iv.getQuantity() >= or.getQuantity()
                            && or.getQuantity() <= 6) {
                        // process order
                        double price = iv.getPrice() * or.getQuantity();
                        ListItems += "Item: " + or.getItemName()  +", " + "Quantity: " + or.getQuantity() +", "+ "Price: " + price +", " +"\n";
                        amountPaid += price;
                        System.out.println("Misc: " + price);
                        if (!cardExists(cards, or.getCardNumber())){
                            updateCardNumbers(or.getCardNumber());
                        }
                        break;
                    } else {
                        System.err.println(">> Incorrect quantity.. writing to log.txt");
                        String log = "";
                        log+= "Item: " + or.getItemName();
//                        System.out.println(log);
                        writeToLog(log);
                    }
                }
            }
            if (amountPaid > 0){
//                System.out.println("Total for rest of the cart" + amountPaid);
//                 updateAmount(amountPaid);
            }
        }
        UpdateList(ListItems,amountPaid);

    }

    public static void UpdateList(String ListItems,double amount) throws IOException{
        FileWriter pw = new FileWriter("Output.csv");
        pw.append( "Amt Paid");
        pw.append("\n");
        pw.append(amount + "s");
        pw.append("\n");
        pw.append("List of items: ");
        pw.append("\n");
        pw.append(ListItems);
        pw.append("\n");
        pw.flush();
        pw.close();
    }


}
