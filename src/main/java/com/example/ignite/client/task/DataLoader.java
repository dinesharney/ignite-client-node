package com.example.ignite.client.task;

import com.example.ignite.server.entity.User;
import com.example.ignite.client.service.UserService;
import org.apache.ignite.client.ClientCache;
import org.apache.ignite.client.IgniteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static com.example.ignite.client.constants.AppConstants.USER_CACHE;

/**
 * CommandLineRunner to load initial data into Ignite cache from the database on application startup.
 */
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private IgniteClient igniteClient;

    @Override
    public void run(String... args) throws Exception {
        ClientCache<Long, User> userCache =  igniteClient.cache(USER_CACHE);
        //userService.getAllUsers().forEach(user -> userCache.put(user.getId(), user));
    }
}
