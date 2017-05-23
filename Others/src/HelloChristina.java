/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 1/19/16
 * Time: 4:40 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class HelloChristina {
    public static void main(String[] args) {
        System.out.println("Hello! I'm computer. What is your name?");
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        System.out.println("hi! $$$$$$" + name + "$$$$$. Nice to meet you!");
        System.out.println("How old are you?");
        String age = in.nextLine();
        System.out.println(age + " is a nice age.");
        System.out.println("\nWhat do you want to drink?");
        String drink = in.nextLine();
        System.out.println("I can offer you " + drink);
    }
}
