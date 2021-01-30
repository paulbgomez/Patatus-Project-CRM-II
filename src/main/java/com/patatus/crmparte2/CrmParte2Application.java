package com.patatus.crmparte2;

import com.patatus.crmparte2.menu.Menu;
import com.patatus.crmparte2.menu.MenuColors;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrmParte2Application {
    public static void main(String[] args) {
        SpringApplication.run(CrmParte2Application.class, args);
//        Menu menu = new Menu();
//        System.out.println(MenuColors.setColorWhiteBold("CRM Console.") + " Type 'help' for a command list.");
//        menu.show();
    }
}
