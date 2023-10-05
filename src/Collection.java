import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Collection {

    public static Scanner keyboard = new Scanner(System.in);

    private static int count = 0;
    private static Book[] arrayOfBooks = new Book[0];

    public static void addBook(Book bookToAdd) {
        arrayOfBooks = Arrays.copyOf(arrayOfBooks, count + 1);
        arrayOfBooks[count] = bookToAdd;
        count++;
    }

    public static void remove(int indexToRemove) {
        count--;
        Book[] arrayToReturn = new Book[arrayOfBooks.length - 1];
        Boolean removedElement = false;
        for (int i = 0; i < count; i++) {
            if (i != indexToRemove && !removedElement) {
                arrayToReturn[i] = arrayOfBooks[i];
            } else {
                removedElement = true;
                arrayToReturn[i] = arrayOfBooks[i + 1];
            }
        }
        arrayOfBooks = arrayToReturn;
    }

    public static void printOneLong(int i) {
        System.out.println(arrayOfBooks[i].getLongDesc());
    }

    public static void printOneShort(int i) {
        System.out.println(arrayOfBooks[i].getShortDesc());
    }

    public static void printAll() {
        for (int i = 0; i < arrayOfBooks.length; i++) {
            System.out.println(arrayOfBooks[i].getLongDesc());
        }
    }

    public static void printList() {
        for (int i = 0; i < arrayOfBooks.length; i++) {
            System.out.println((i + 1) + ". " + arrayOfBooks[i].getShortDesc());
        }
    }

    public static void bubbleSort(boolean isSortByYear) {
        boolean isSorted;
        int i = 0;
        do {
            isSorted = true;
            for (int j = 1; j < count - i; j++) {
                if (isSortByYear) {
                    if (arrayOfBooks[j].getYearWhenPublished() < arrayOfBooks[j - 1].getYearWhenPublished()) {
                        swap(arrayOfBooks, j);
                        isSorted = false;
                    }
                } else {
                    if (arrayOfBooks[j].getPriceInDollars() < arrayOfBooks[j - 1].getPriceInDollars()) {
                        swap(arrayOfBooks, j);
                        isSorted = false;
                    }
                }
            }
            i++;
        } while (!isSorted);
    }

    public static void swap(Book[] arrayWhereSwap, int indexToSwap) {
        Book temporaryVault = arrayWhereSwap[indexToSwap];
        arrayWhereSwap[indexToSwap] = arrayWhereSwap[indexToSwap - 1];
        arrayWhereSwap[indexToSwap - 1] = temporaryVault;
    }

    public static void searchByAuthor() {
        System.out.println("Please, enter author you are willing to search for: ");
        String stringToSearch = keyboard.nextLine();
        for (int i = 0; i < count; i++) {
            if ((arrayOfBooks[i].getAuthorSurname().toLowerCase().contains(stringToSearch.toLowerCase()))
                    || (arrayOfBooks[i].getAuthorName().toLowerCase().contains(stringToSearch.toLowerCase()))) {
                System.out.print((i + 1) + ". ");
                printOneShort(i);
            }
        }
    }

    public static void searchByYear() {
        System.out.println("Please, enter the year of publication you are willing to search for: ");
        int yearToSearch = keyboard.nextInt();
        keyboard.nextLine();
        for (int i = 0; i < count; i++) {
            if (arrayOfBooks[i].getYearWhenPublished() == yearToSearch) {
                System.out.print((i + 1) + ". ");
                printOneShort(i);
            }
        }
    }

    public static void searchByYearAndString() {
        System.out.println("Please, enter the combination of characters you are willing to search for: ");
        String stringToSearch = keyboard.nextLine();
        System.out.println("Please, enter the year of publication you are willing to search for: ");
        int yearToSearch = keyboard.nextInt();
        keyboard.nextLine();
        for (int i = 0; i < count; i++) {
            if (arrayOfBooks[i].getYearWhenPublished() == yearToSearch &&
                    arrayOfBooks[i].getTitle().toLowerCase().contains(stringToSearch.toLowerCase())) {
                System.out.print((i + 1) + ". ");
                printOneShort(i);
            }
        }
    }

    public static void readFromFile() throws FileNotFoundException {
        FileInputStream myFile = new FileInputStream("books.txt");
        Scanner myFileReader = new Scanner(myFile);

        while (myFileReader.hasNextLine()) {
            String title = myFileReader.nextLine();
            String line = myFileReader.nextLine();
            String[] words = line.split(" ");

            int year = (Integer.parseInt(myFileReader.nextLine()));
            double price = Double.parseDouble(myFileReader.nextLine());
            myFileReader.nextLine();

            addBook(new Book(title, words[0], words[1], year, price));
        }
        myFileReader.close();
    }

    public static void saveToFile() throws IOException {

        FileWriter myFile = new FileWriter("books.txt");
        BufferedWriter output_buffer = new BufferedWriter(myFile);

        for (int i = 0; i < count; i++) {
            output_buffer.write(arrayOfBooks[i].getTitle() + "\n");
            output_buffer.write(arrayOfBooks[i].getAuthorName() + " " + arrayOfBooks[i].getAuthorSurname() + "\n");
            output_buffer.write(arrayOfBooks[i].getYearWhenPublished() + "\n");
            output_buffer.write(String.valueOf(arrayOfBooks[i].getPriceInDollars()) + "\n");
            output_buffer.write("-\n");
        }
        output_buffer.close();
    }

}