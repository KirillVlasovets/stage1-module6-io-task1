package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class FileReader {

    public Profile getDataFromFile(File file) {
        Profile profile = new Profile();
        boolean isValue = false;
        try (FileInputStream inputStream = new FileInputStream(file)) {
            int character;
            String key = "";
            while ((character = inputStream.read()) != -1) {
                String value = "";
                if (character == (int) ' ') {
                    continue;
                } else if (character == (int) ':') {
                    isValue = true;
                } else if (character == (int) '\r' || character == (int) '\n') {
                    isValue = false;
                    key = "";
                } else if (isValue) {
                    value = String.valueOf(Character.toChars(character));
                    switch (key) {
                        case "Name":
                            profile.setName(profile.getName() == null ? value : profile.getName().concat(value));
                            break;
                        case "Age":
                            profile.setAge(Integer.parseInt(profile.getAge() == null ? value :
                                    profile.getAge().toString().concat(value)));
                            break;
                        case "Email":
                            profile.setEmail(profile.getEmail() == null ? value : profile.getEmail().concat(value));
                            break;
                        case "Phone":
                            profile.setPhone(Long.parseLong(profile.getPhone() == null ? value :
                                    profile.getPhone().toString().concat(value)));
                            break;
                    }
                } else {
                    key = key.concat(String.valueOf(Character.toChars(character)[0]));
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return profile;
    }
}
