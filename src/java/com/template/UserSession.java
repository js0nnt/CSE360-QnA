package com.template;

public class UserSession
{
    private static String username;

    public static void setUsername(String name)
    {
        username = name;
    }

    public static String getUsername()
    {
        return username;
    }
}
