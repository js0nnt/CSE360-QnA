package com.template;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Stores accounts as "username,password,role,firstName,lastName,email,phone,dateOfBirth,favoriteColor"
 * lines in a local CSV file. Older "username,password,role" lines are still read.
 * Plain text on purpose for simplicity while learning JavaFX; not how a real app should store passwords.
 */
public class UserStore
{
    private static final Path USERS_FILE = Paths.get("users.csv");
    private static final Path USERS_LOCK = Paths.get("users.csv.lock");
    private static final DateTimeFormatter DATE_OF_BIRTH =
            DateTimeFormatter.ofPattern("dd.MM.uuuu").withResolverStyle(ResolverStyle.STRICT);

    public static boolean usernameExists(String username) throws IOException
    {
        return findUserLine(username) != null;
    }

    public static synchronized String registerUser(String username, String password, UserProfile profile)
            throws IOException
    {
        validateRegistration(username, password, profile);
        String phoneDigits = digitsOnly(profile.phone());

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
            String line = String.join(",", username, password, role, profile.firstName().trim(),
                    profile.lastName().trim(), profile.email().trim(), phoneDigits,
                    profile.dateOfBirth(), profile.favoriteColor());
            Files.writeString(USERS_FILE, prefix + line + System.lineSeparator(), StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            return role;
        }
    }

    /** Throws IllegalArgumentException with a message for the first invalid field, in form order. */
    public static void validateRegistration(String username, String password, UserProfile profile)
    {
        requireValue(profile.firstName(), "first name");
        requireValue(profile.lastName(), "last name");
        requireValue(profile.email(), "email");
        requireValue(username, "username");
        requireValue(profile.phone(), "phone number");
        requireValue(profile.dateOfBirth(), "date of birth");
        requireValue(profile.favoriteColor(), "favorite color");
        requireValue(password, "password");

        if (!profile.email().matches("[^@\\s]+@[^@\\s]+\\.[^@\\s]+"))
        {
            throw new IllegalArgumentException("Please enter a valid email address.");
        }
        if (!digitsOnly(profile.phone()).matches("\\d{10}"))
        {
            throw new IllegalArgumentException("Please enter a 10-digit phone number.");
        }
        try
        {
            if (LocalDate.parse(profile.dateOfBirth(), DATE_OF_BIRTH).isAfter(LocalDate.now()))
            {
                throw new IllegalArgumentException("Date of birth cannot be in the future.");
            }
        }
        catch (DateTimeParseException exception)
        {
            throw new IllegalArgumentException("Please enter your date of birth as DD.MM.YYYY.");
        }
        if (password.length() < 8 || !password.matches(".*[A-Za-z].*") || !password.matches(".*\\d.*"))
        {
            throw new IllegalArgumentException("Password must be at least 8 characters with a number and a letter.");
        }
    }

    private static String digitsOnly(String phone)
    {
        return phone.replaceAll("[\\s()\\-.]", "");
    }

    private static void requireValue(String value, String name)
    {
        if (value == null || value.isBlank())
        {
            throw new IllegalArgumentException("Please enter your " + name + ".");
        }
        if (value.contains(",") || value.contains("\n") || value.contains("\r"))
        {
            throw new IllegalArgumentException("Your " + name + " cannot contain commas or line breaks.");
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
            if (parts.length >= 3 && parts[0].equals(user))
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
            if (parts.length >= 3 && parts[0].equals(username))
            {
                return parts;
            }
        }
        return null;
    }
}
