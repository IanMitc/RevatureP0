package com.mitchell.ian;

import com.mitchell.ian.Data.Database;
import com.mitchell.ian.UI.Menus.Shared.MainMenu;

public class TheBank {

    public static void main(String[] args) {
        Database.initialize();

        MainMenu.show();
    }
}
