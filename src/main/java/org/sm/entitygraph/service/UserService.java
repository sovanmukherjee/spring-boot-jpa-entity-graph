package org.sm.entitygraph.service;

import org.sm.entitygraph.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    List<User> getUsers();

    User getUserById(int userId);

    List<User> getUserByClassName(String className);

    List<User> getUserByRollNumberAndClassName(Set<String> className, int rollNumber);
}
