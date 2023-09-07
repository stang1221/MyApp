package com.example.myapp;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;

public class SimpsonQuote {
    public static void main(String[] args) {
        String url = "https://raw.githubusercontent.com/erabug/simpsons-quotes/master/quotes.json";

        try {
            String json = readUrl(url);
            String[] quotes = extractQuotes(json);

            if (quotes.length > 0) {
                Random random = new Random();
                int randomIndex = random.nextInt(quotes.length);
                String randomQuote = quotes[randomIndex];
                System.out.println(randomQuote);
            } else {
                System.out.println("No quotes found.");
            }
        } catch (IOException e) {
            System.out.println("Failed to retrieve quotes from the URL.");
        }
    }

    private static String readUrl(String urlString) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (Scanner scanner = new Scanner(new URL(urlString).openStream())) {
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine());
            }
        }
        return sb.toString();
    }

    private static String[] extractQuotes(String json) {
        // Parse the JSON and extract the quotes using a JSON library
        // You can use libraries like Gson, Jackson, or org.json, depending on your preference

        // For demonstration purposes, we'll assume the quotes are extracted correctly and return a sample array
        return new String[]{
                "Quote 1",
                "Quote 2",
                "Quote 3"
        };
    }
}
