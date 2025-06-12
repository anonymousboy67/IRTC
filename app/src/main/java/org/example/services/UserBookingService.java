package org.example.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.User;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserBookingService {

    private User user;

    private ObjectMapper objectMapper = new ObjectMapper();

    private List<User> userList;
    private static final String USER_PATH = "app/src/main/java/org/example/localDb/users.json";

    public UserBookingService(User user1) throws IOException {
        this.user = user1;

        File users = new File(USER_PATH);
        userList = objectMapper.readValue(users, new TypeReference<List<User>>() {});
    }


    public Boolean loginUser() {
        Optional<User> foundUser = userList.stream()
                .filter(u -> u.getName().equals(user.getName()) &&
                        UserServiceUtil.checkPassword(user, u))
                .findFirst();

        return foundUser.isPresent();
    }

    public Boolean signUp(User user1) {
        try {
            // Optional: Check if user already exists
            boolean userExists = userList.stream()
                    .anyMatch(u -> u.getName().equals(user1.getName()));

            if (userExists) {
                return Boolean.FALSE; // Don't allow duplicate sign-ups
            }

            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        } catch (IOException ex) {
            return Boolean.FALSE;
        }
    }



}
