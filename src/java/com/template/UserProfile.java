package com.template;

/**
 * The personal details collected by the Create Account form, saved alongside the login.
 * Phone is stored as its 10 digits and date of birth as DD.MM.YYYY.
 */
public record UserProfile(String firstName, String lastName, String email, String phone,
                          String dateOfBirth, String favoriteColor)
{
}
