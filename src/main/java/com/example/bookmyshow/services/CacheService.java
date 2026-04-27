package com.example.bookmyshow.services;


public interface CacheService {

    void set(String key, Object value);
    Object get(String key);
    void delete(String key);
    void getAllKeysAndValues();
    void deleteAll();
}
