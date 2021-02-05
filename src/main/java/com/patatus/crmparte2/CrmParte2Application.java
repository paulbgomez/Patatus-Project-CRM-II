package com.patatus.crmparte2;

import com.patatus.crmparte2.menu.Menu;
import com.patatus.crmparte2.menu.MenuColors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication                                                                                          // PARA TEST COMENTA ESTO
public class CrmParte2Application implements CommandLineRunner {                                                // PARA TEST COMENTA ESTO
    @Autowired                                                                                                  // PARA TEST COMENTA ESTO
    Menu menu;                                                                                                  // PARA TEST COMENTA ESTO

    public static void main(String[] args) {                                                                    // PARA TEST COMENTA ESTO
        SpringApplication.run(CrmParte2Application.class, args);                                                // PARA TEST COMENTA ESTO
    }                                                                                                           // PARA TEST COMENTA ESTO

    @Override                                                                                                   // PARA TEST COMENTA ESTO
    public void run(String... args){                                                                            // PARA TEST COMENTA ESTO
        System.out.println(MenuColors.setColorWhiteBold("CRM Console.") + " Type 'help' for a command list.");  // PARA TEST COMENTA ESTO
        menu.show(new Scanner(System.in));                                                                      // PARA TEST COMENTA ESTO
    }                                                                                                           // PARA TEST COMENTA ESTO
}                                                                                                               // PARA TEST COMENTA ESTO


//@SpringBootApplication                                                  // PARA TEST *DES*COMENTA ESTO
//public class CrmParte2Application {                                     // PARA TEST *DES*COMENTA ESTO
//    public static void main(String[] args) {                            // PARA TEST *DES*COMENTA ESTO
//        SpringApplication.run(CrmParte2Application.class, args);        // PARA TEST *DES*COMENTA ESTO
//    }                                                                   // PARA TEST *DES*COMENTA ESTO
//}                                                                       // PARA TEST *DES*COMENTA ESTO
