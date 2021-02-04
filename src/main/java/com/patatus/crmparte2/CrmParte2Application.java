package com.patatus.crmparte2;

import com.patatus.crmparte2.menu.Menu;
import com.patatus.crmparte2.menu.MenuColors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
//public class CrmParte2Application implements CommandLineRunner {  //PARA TEST COMENTA ESTO

    public class CrmParte2Application {       //PARA TEST *DES*COMENTA ESTO
//    @Autowired                              //PARA TEST DESCOMENTA ESTO
//    Menu menu;                              //PARA TEST DESCOMENTA ESTO

    public static void main(String[] args) {
        SpringApplication.run(CrmParte2Application.class, args);
    }

//    @Override                                                                                                      //Para TEST COMENTA ESTO
//    public void run(String... args){                                                                                //Para TEST COMENTA ESTO
//        System.out.println(MenuColors.setColorWhiteBold("CRM Console.") + " Type 'help' for a command list.");  //Para TEST COMENTA ESTO
//        menu.show(new Scanner(System.in));                                                                          //Para TEST COMENTA ESTO
//    }
}
