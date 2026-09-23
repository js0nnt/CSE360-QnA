package com.template;

public class UserSession
{
    private static String username;
    private static String role;

    public static void start(String name, String accountRole)
    {
        username = name;
        role = accountRole;
    }

    public static String getUsername()
    {
        return username;
    }

    public static boolean isAdmin()
    {
        return username != null && "admin".equals(role);
    }

    public static void clear()
    {
        username = null;
        role = null;
    }
}
