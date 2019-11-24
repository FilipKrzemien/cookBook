package com.user.cookbook.db.dao;

import java.util.ArrayList;

public interface IDAO <T>{
    long save(T type);
    ArrayList<T> getAll();
}
