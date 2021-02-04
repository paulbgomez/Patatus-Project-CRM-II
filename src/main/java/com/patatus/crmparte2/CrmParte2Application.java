package com.patatus.crmparte2;

import com.patatus.crmparte2.menu.Menu;
import com.patatus.crmparte2.menu.MenuColors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class CrmParte2Application implements CommandLineRunner {
    @Autowired
    Menu menu;

    public static void main(String[] args) {
        SpringApplication.run(CrmParte2Application.class, args);
    }

    @Override
    public void run(String... args){
        System.out.println(MenuColors.setColorWhiteBold("CRM Console.") + " Type 'help' for a command list.");
        menu.show(new Scanner(System.in));
    }
}
