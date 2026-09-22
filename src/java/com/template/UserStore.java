package com.template;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Stores accounts as "username,password,role" lines in a local CSV file.
 * Plain text on purpose for simplicity while learning JavaFX; not how a real app should store passwords.
 */
public class UserStore
{
    private static final Path USERS_FILE = Paths.get("users.csv");
    private static final Path USERS_LOCK = Paths.get("users.csv.lock");

    public static boolean usernameExists(String username) throws IOException
    {
        return findUserLine(username) != null;
    }
    
    public static synchronized String registerUser(String username, String password) throws IOException
    {
        if (username == null || username.isBlank() || password == null || password.isEmpty()
                || username.contains(",") || username.contains("\n") || username.contains("\r")
                || password.contains(",") || password.contains("\n") || password.contains("\r"))
        {
            throw new IllegalArgumentException("Username and password cannot be empty or contain commas or line breaks.");
        }

        // Lock registration across app instances so only one account can claim the first slot.
        try (FileChannel channel = FileChannel.open(USERS_LOCK,
                StandardOpenOption.CREATE, StandardOpenOption.WRITE);
             FileLock ignored = channel.lock())
        {
            String contents = Files.exists(USERS_FILE)
                    ? Files.readString(USERS_FILE, StandardCharsets.UTF_8) : "";
            boolean firstUser = contents.lines().noneMatch(line -> !line.isBlank());
            if (usernameExists(username))
            {
                throw new IllegalArgumentException("That username is already taken.");
            }

            String role = firstUser ? "admin" : "user";
            String prefix = !contents.isEmpty() && !contents.endsWith("\n") && !contents.endsWith("\r")
                    ? System.lineSeparator() : "";
            Files.writeString(USERS_FILE, prefix + username + "," + password + "," + role
                            + System.lineSeparator(), StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            return role;
        }
    }

    public static boolean verifyLogin(String username, String password) throws IOException
    {
        String[] parts = findUserLine(username);
        return parts != null && parts[1].equals(password);
    }
    
    public static String getRole(String user) throws IOException
    {
        if (!Files.exists(USERS_FILE))
        {
            return null;
        }
        boolean firstAccount = true;
        for (String line : Files.readAllLines(USERS_FILE, StandardCharsets.UTF_8))
        {
            if (line.isBlank())
            {
                continue;
            }
            String[] parts = line.split(",", -1);
            if (parts.length == 3 && parts[0].equals(user))
            {
                return firstAccount ? "admin" : "user";
            }
            firstAccount = false;
        }
        return null;
    }

    private static String[] findUserLine(String username) throws IOException
    {
        if (!Files.exists(USERS_FILE))
        {
            return null;
        }

        for (String line : Files.readAllLines(USERS_FILE, StandardCharsets.UTF_8))
        {
            String[] parts = line.split(",", -1);
            if (parts.length == 3 && parts[0].equals(username))
            {
                return parts;
            }
        }
        return null;
    }
}
