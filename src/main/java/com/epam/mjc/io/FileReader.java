package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Logger;


public class FileReader {

    private static final Logger logger = Logger.getLogger(FileReader.class.getName());

    public Profile getDataFromFile(File file) {
        Profile profile = new Profile();
        boolean isValue = false;
        try (FileInputStream inputStream = new FileInputStream(file)) {
            int character;
            String key = "";
            while ((character = inputStream.read()) != -1) {
                String value = "";
                if (character == ' ') {
                    continue;
                }
                if (character == ':') {
                    isValue = true;
                } else if (character == '\r' || character == '\n') {
                    isValue = false;
                    key = "";
                } else if (isValue) {
                    value = String.valueOf(Character.toChars(character));
                    setProfileField(profile, key, value);
                } else {
                    key = key.concat(String.valueOf(Character.toChars(character)[0]));
                }
            }
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
        return profile;
    }

    private void setProfileField(Profile profile, String key, String value) {
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
            default:
                logger.info("Not allowed key");
        }
    }
}
