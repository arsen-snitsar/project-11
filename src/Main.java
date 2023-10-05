import java.util.Scanner;
import java.io.IOException;

public class Main {

    public static Scanner keyboard = new Scanner(System.in);

    public static void printMenu(){
        System.out.println("\nWelcome to the book database!\n" +
                "Choose an option to proceed:\n" +
                "1 - print books with long description\n" +
                "2 - print books with short description\n" +
                "3 - add new book\n" +
                "4 - remove book\n" +
                "5 - sort books by year\n" +
                "6 - sort books by price\n" +
                "7 - search books by author\n" +
                "8 - search books by year\n" +
                "9 - read books from the file\n" +
                "10 - save books to the file \n" +
                "0 - exit the program\n");
    }

    public static int askAction(){
        int currentAction = keyboard.nextInt();
        keyboard.nextLine();
        return currentAction;
    }

    public static boolean doAction(int currentAction) throws IOException {
        switch (currentAction){
            case 1:
                Collection.printAll();
                break;
            case 2:
                Collection.printList();
                break;
            case 3:
                Collection.addBook(askBook());
                break;
            case 4:
                Collection.remove(askIndexToRemove());
                break;
            case 5:
                Collection.bubbleSort(true);
                break;
            case 6:
                Collection.bubbleSort(false);
                break;
            case 7:
                Collection.searchByAuthor();
                break;
            case 8:
                Collection.searchByYear();
                break;
            case 9:
                Collection.readFromFile();
                break;
            case 10:
                Collection.saveToFile();
                break;
            case 0:
                return false;
        }
        return true;
    }

    public static int askIndexToRemove(){
        System.out.println("Please, type the index of a book you want to remove: ");
        int indexToRemove = keyboard.nextInt();
        keyboard.nextLine();
        return indexToRemove;
    }

    public static Book askBook(){

        System.out.print("Please, enter the title of the book: ");
        String bookTitle = keyboard.nextLine();

        System.out.print("Please, enter author's name: ");
        String authorName = keyboard.nextLine();

        System.out.print("Please, enter author's surname: ");
        String authorSurname = keyboard.nextLine();

        System.out.print("Please, enter the year of publication of the book: ");
        int yearOfPublication = keyboard.nextInt();
        keyboard.nextLine();

        System.out.print("Please, enter the book's current price in dollars: ");
        double priceInDollars = keyboard.nextDouble();
        keyboard.nextLine();

        return new Book(bookTitle, authorName, authorSurname, yearOfPublication, priceInDollars);
    }

    public static void main(String[] args) throws IOException {

        boolean shouldContinue = true;
        while (shouldContinue){
            printMenu();
            shouldContinue = doAction(askAction());
        }
    }

}