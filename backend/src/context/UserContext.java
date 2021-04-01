package com.eepractice.webcrawller.context;

import com.eepractice.webcrawller.bean.User;

public class UserContext {

    private static String  currentUsername = "";

    private static boolean hasCurrentUser = false;

    public static void setCurrentUser(String username) {
        currentUsername = username;
        hasCurrentUser = true;
    }

    public static String getCurrentUser() {
        return currentUsername;
    }

    public static void resetCurrentUser(){
        hasCurrentUser = false;
    }

    public static boolean isHasCurrentUser() {
        return hasCurrentUser;
    }
}
