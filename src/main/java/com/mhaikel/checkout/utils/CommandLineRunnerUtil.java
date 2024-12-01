package com.mhaikel.checkout.utils;

import com.mhaikel.checkout.services.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Component
public class CommandLineRunnerUtil implements CommandLineRunner {

    private final CheckoutService checkoutService;

    @Autowired
    public CommandLineRunnerUtil(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        Map<Character, Integer> cart = new HashMap<>();

        System.out.println("Welcome to the checkout system!");
        while (true) {
            System.out.print("Enter item SKU (or type 'total' to finish): ");
            String input = scanner.nextLine().trim();

            if ("total".equalsIgnoreCase(input)) {
                int total = checkoutService.calculateTotalPrice(cart);
                System.out.println("Total price: " + total + " pence");
                break;
            }

            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Invalid SKU. Please enter a single letter.");
                continue;
            }

            char sku = Character.toUpperCase(input.charAt(0));
            cart.put(sku, cart.getOrDefault(sku, 0) + 1);
            System.out.println("Added " + sku + " to cart. Current total: "
                    + checkoutService.calculateTotalPrice(cart) + " pence.");
        }

        scanner.close();
    }
}
