package edu;

import java.util.*;

public class test {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        //System.getProperties().list(System.out);
        for(Map.Entry<String, String> entry: System.getenv().entrySet()) {
            System.out.format("%s=%s%n", entry.getKey(), entry.getValue());
        }
        if (!System.getenv().containsKey("RUN_ENV")) {
            System.out.println("This is not local env!");
        } else {
            System.out.println("This is local env!");
        }
    }
}