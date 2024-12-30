package com.example.ignite.client.service;

import com.example.ignite.server.entity.User;
import org.apache.ignite.cache.query.ScanQuery;
import org.apache.ignite.client.ClientCache;
import org.apache.ignite.client.IgniteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.cache.Cache;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.example.ignite.client.constants.AppConstants.USER_CACHE;

/**
 * Service to handle business logic for User entities.
 */
@Service
public class UserService {


    @Autowired
    IgniteClient igniteClient;

    public List<User> getAllUsersData() {
        ClientCache<Long, User> cache = igniteClient.cache(USER_CACHE);
        // Use ScanQuery to retrieve all entries and collect values into a list
        return StreamSupport.stream(
                        cache.query(new ScanQuery<Integer, User>()).spliterator(), false
                ).map(Cache.Entry::getValue)
                .collect(Collectors.toList());
    }

    public User getUserDataById(Long id) {
        ClientCache<Long, User> cache = igniteClient.cache(USER_CACHE);
        return cache.get(id);
    }

    public User putUserData(User user) throws Exception{
        ClientCache<Long, User> cache = igniteClient.cache(USER_CACHE);
        cache.put(user.getId(),user);
        return user;
    }

    public User updateUserData(Long id, User updatedUser) {
        ClientCache<Long, User> cache = igniteClient.cache(USER_CACHE);
        if (cache.get(id) !=null) {
            cache.put(id,updatedUser);
            return updatedUser;
        }
        return null;
    }

    public void deleteUserData(Long id) {
        igniteClient.cache(USER_CACHE).remove(id);
    }
}