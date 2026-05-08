package library;

import java.util.ArrayList;
import java.util.Scanner;

interface LibraryOperations {
    void addBook(Book book);
    void issueBook(int bookId, String userName);
    void returnBook(int bookId);
    void searchBook(String keyword);
    void displayBooks();
}

class Book {
    private int bookId;
    private String title;
    private String author;
    private boolean isIssued;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public int getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isIssued() { return isIssued; }
    public void setIssued(boolean issued) { isIssued = issued; }

    public String toString() {
        return bookId + " | " + title + " | " + author +
                " | Issued: " + (isIssued ? "Yes" : "No");
    }
}

class Library implements LibraryOperations {
    ArrayList<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book Added Successfully.");
    }

    public void issueBook(int bookId, String userName) {
        for (Book b : books) {
            if (b.getBookId() == bookId) {
                if (!b.isIssued()) {
                    b.setIssued(true);
                    System.out.println("Book Issued to " + userName);
                } else {
                    System.out.println("Book Already Issued.");
                }
                return;
            }
        }
        System.out.println("Book Not Found.");
    }

    public void returnBook(int bookId) {
        for (Book b : books) {
            if (b.getBookId() == bookId) {
                if (b.isIssued()) {
                    b.setIssued(false);
                    System.out.println("Book Returned Successfully.");
                } else {
                    System.out.println("Book Was Not Issued.");
                }
                return;
            }
        }
        System.out.println("Book Not Found.");
    }

    public void searchBook(String keyword) {
        boolean found = false;
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(keyword) ||
                b.getAuthor().equalsIgnoreCase(keyword)) {
                System.out.println(b);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Book Not Found.");
        }
    }

    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No Books in Library.");
            return;
        }
        for (Book b : books) {
            System.out.println(b);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        LibraryOperations library = new Library();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1.Add Book");
            System.out.println("2.Issue Book");
            System.out.println("3.Return Book");
            System.out.println("4.Search Book");
            System.out.println("5.Display All Books");
            System.out.println("6.Exit");
            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    library.addBook(new Book(id, title, author));
                    break;

                case 2:
                    System.out.print("Enter Book ID: ");
                    int issueId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter User Name: ");
                    String user = sc.nextLine();
                    library.issueBook(issueId, user);
                    break;

                case 3:
                    System.out.print("Enter Book ID: ");
                    int returnId = sc.nextInt();
                    library.returnBook(returnId);
                    break;

                case 4:
                    System.out.print("Enter Title/Author: ");
                    String keyword = sc.nextLine();
                    library.searchBook(keyword);
                    break;

                case 5:
                    library.displayBooks();
                    break;

                case 6:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
            }
        }
    }
}