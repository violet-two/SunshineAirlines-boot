package edu.wtbu.service;

import edu.wtbu.pojo.Result;

import java.util.HashMap;

public interface UserService {
    Result login(HashMap map);

    Result updatePassword(HashMap map);

    Result getUserList(HashMap map);

    Result addUser(HashMap map);
}
