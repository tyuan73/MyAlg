package p00230;

/**
 * Created by yuantian on 3/30/15.
 */

/*

*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

class Main {
    static class Book implements Comparable<Book> {
        String name, author;
        boolean removed = false;

        Book(String name, String author) {
            this.name = name;
            this.author = author;
            this.removed = false;
        }

        public int compareTo(Book b) {
            if (this.author.equals(b.author)) {
                return this.name.compareTo(b.name);
            }
            return this.author.compareTo(b.author);
        }
    }

    static void go() {
        Scanner in = new Scanner(System.in);
        String book = null;
        ArrayList<Book> list = new ArrayList<>();
        while (in.hasNextLine()) {
            book = in.nextLine();
            if (book.equals("END")) break;

            int index = book.indexOf(" by ");
            list.add(new Book(book.substring(0, index), book.substring(index+4)));
        }
        Collections.sort(list);

        HashSet<String> set = new HashSet<>();
        String line = null;
        while (in.hasNextLine()) {
            line = in.nextLine();
            if (line.equals("END")) break;

            if (line.startsWith("BORROW")) {
                removeBook(list, line.substring(7));
            } else if (line.startsWith("RETURN")) {
                set.add(line.substring(7));
            } else {
                // SHELVE
                returnBook(list, set);
                System.out.println("END");
            }
        }
    }

    static void removeBook(ArrayList<Book> shelf, String bookname) {
        for (Book book : shelf) {
            if (book.name.equals(bookname)) {
                book.removed = true;
                return;
            }
        }
    }

    static void returnBook(ArrayList<Book> shelf, HashSet<String> set) {
        Book pre = null;
        for (Book book : shelf) {
            if (set.contains(book.name)) {
                book.removed = false;

                if (pre == null) {
                    System.out.println("Put " + book.name + " first");
                } else {
                    System.out.println("Put " + book.name + " after " + pre.name);
                }

                set.remove(book.name);
                if (set.size() == 0)
                    return;
            }
            if (!book.removed)
                pre = book;
        }
    }

    public static void main(String[] args) {
        go();
    }
}