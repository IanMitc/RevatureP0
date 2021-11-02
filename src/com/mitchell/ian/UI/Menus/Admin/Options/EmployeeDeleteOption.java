package com.mitchell.ian.UI.Menus.Admin.Options;

import com.mitchell.ian.Data.DaoFactory;
import com.mitchell.ian.Data.UserDao;
import com.mitchell.ian.UI.Utility.Ask;
import com.mitchell.ian.UI.Utility.Clear;
import com.mitchell.ian.User.User;

public class EmployeeDeleteOption {
    public static void execute() {
        UserDao userDao = DaoFactory.getUserDao();
        boolean isSure = false;
        int idToDelete = 0;

        boolean run = true;
        while (run) {
            try {
                idToDelete = Ask.forInt("Enter Employee ID to delete (0 to cancel)");
                run = false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        if (idToDelete == 0) {
            Clear.console();
            System.out.println("Canceling...");
            return;
        }

        User employee = userDao.getUser(idToDelete);

        run = true;
        while (run) {
            try {
                System.out.println("Deleting " + employee);
                isSure = Ask.forBoolean("Are you sure", 'y', 'n');
                run = false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        Clear.console();
        if (isSure) {
            userDao.deleteUser(employee);
            System.out.println(employee + "\nDeleted");
        } else {
            System.out.println("Canceling...");
        }
    }
}

