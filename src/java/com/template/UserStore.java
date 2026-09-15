package com.template;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * Stores accounts as "username,password,role" lines in a local CSV file.
 * Plain text on purpose for simplicity while learning JavaFX; not how a real app should store passwords.
 */
public class UserStore
{
    private static final Path USERS_FILE = Paths.get("users.csv");

    public static boolean usernameExists(String username) throws IOException
    {
        return findUserLine(username) != null;
    }
    
    public static boolean isFirstUser() throws IOException{
    	return !Files.exists(USERS_FILE) || Files.readAllLines(USERS_FILE, StandardCharsets.UTF_8).isEmpty();
   
    }

    public static void registerUser(String username, String password, String role) throws IOException
    {
        String line = username + "," + password + "," + role;
        Files.write(USERS_FILE, (line + System.lineSeparator()).getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    public static boolean verifyLogin(String username, String password) throws IOException
    {
        String[] parts = findUserLine(username);
        return parts != null && parts[1].equals(password);
    }
    
    public static String getRole(String user) throws IOException{
    	String[] parts = findUserLine(user);
    	return parts != null ? parts[2] : null;
    }

    private static String[] findUserLine(String username) throws IOException
    {
        if (!Files.exists(USERS_FILE))
        {
            return null;
        }

        for (String line : Files.readAllLines(USERS_FILE, StandardCharsets.UTF_8))
        {
            String[] parts = line.split(",");
            if (parts.length == 3 && parts[0].equals(username))
            {
                return parts;
            }
        }
        return null;
    }
}
