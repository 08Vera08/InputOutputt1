import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

class Basket {
    private ArrayList<Integer> prices;
    private ArrayList<Integer> counts;
    private ArrayList<String> products;
    private static int amount;

    Basket() {

    }

    Basket(ArrayList<Integer> prices, ArrayList<Integer> counts, ArrayList<String> products, int n) {
        this.products = new ArrayList<String>();
        this.prices = new ArrayList<Integer>();
        this.counts = new ArrayList<Integer>();
        this.amount = n;
        for (int i = 0; i < n; ++i) {
            this.products.add(products.get(i));
            this.prices.add(prices.get(i));
            this.counts.add(counts.get(i));
        }
    }

    public String getProduct(int num) {
        return products.get(num);
    }

    public void setProduct(int num, String product) {
        if (num < amount) {
            products.set(num, product);
        } else {
            products.add(product);
            amount++;
        }
    }

    public void setPrice(int num, int price) {
        if (num < amount) {
            prices.set(num, price);
        } else {
            prices.add(price);
        }
    }

    public void setCount(int num, int count) {
        if (num < amount) {
            counts.set(num, count);
        } else {
            counts.add(count);
        }
    }

    public void setAmount(int count) {
        amount = count;
    }

    public int getCount(int num) {
        return counts.get(num);
    }

    public int getSize() {
        return amount;
    }

    public int getPrice(int num) {
        return prices.get(num);
    }

    public void addToCart(int productNum, int amount) {
        counts.set(productNum, amount + counts.get(productNum));
    }

    public void printCart() {
        for (int i = 0; i < amount; ++i) {
            System.out.println(products.get(i) + " : " + counts.get(i) + "шт. по цене " + prices.get(i) + " за штуку.");
        }
    }

    public static Basket loadFromTxtFile(File textFile) {
        ArrayList<Integer> price = new ArrayList<Integer>();
        ArrayList<Integer> count = new ArrayList<Integer>();
        ArrayList<String> product = new ArrayList<String>();
        try {
            Scanner myReader = new Scanner(textFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String arr[] = data.split(" ");
                amount = arr.length;
                for (int i = 0; i < arr.length; i++) {
                    product.add(arr[i]);
                }
                data = myReader.nextLine();
                arr = data.split(" ");
                for (int i = 0; i < arr.length; i++) {
                    count.add(Integer.valueOf(arr[i]));
                }
                data = myReader.nextLine();
                arr = data.split(" ");
                for (int i = 0; i < arr.length; i++) {
                    price.add(Integer.valueOf(arr[i]));
                }
            }
            myReader.close();
            System.out.println("Saved!");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return new Basket(price, count, product, amount);
    }

    public void saveTxt(File textFile) {
        try {
            PrintWriter out = new PrintWriter(textFile);
            for (int i = 0; i < amount; ++i) {
                out.print(products.get(i) + " ");
            }
            out.println();
            for (int i = 0; i < amount; ++i) {
                out.print(counts.get(i) + " ");
            }
            out.println();
            for (int i = 0; i < amount; ++i) {
                out.print(prices.get(i) + " ");
            }
            out.close();
            System.out.println("Saved!");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}