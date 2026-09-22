package com.template;

public class UserSession
{
    private static String username;
    private static String role;
    private static boolean newAdmin;

    public static void start(String name, String accountRole, boolean firstAdminSignup)
    {
        username = name;
        role = accountRole;
        newAdmin = firstAdminSignup;
    }

    public static String getUsername()
    {
        return username;
    }

    public static boolean isAdmin()
    {
        return username != null && "admin".equals(role);
    }

    public static boolean consumeAdminCongratulations()
    {
        boolean showCongratulations = newAdmin;
        newAdmin = false;
        return showCongratulations;
    }

    public static void clear()
    {
        username = null;
        role = null;
        newAdmin = false;
    }
}
