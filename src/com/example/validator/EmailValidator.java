package com.example.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

        private Matcher matcher;

        private static final Pattern EMAIL_PATTERN = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");


        public boolean validate(final String hex) {
            matcher = EMAIL_PATTERN.matcher(hex);
            return matcher.matches();
        }

    }

