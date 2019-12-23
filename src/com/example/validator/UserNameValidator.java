package com.example.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserNameValidator {

    private Matcher matcher;

    private static final Pattern USER_NAME_PATTERN = Pattern.compile("^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$");

    public boolean validate(final String hex) {
        matcher = USER_NAME_PATTERN.matcher(hex);
        return matcher.matches();
    }

}
