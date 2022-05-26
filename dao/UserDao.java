package edu.wtbu.dao;

import java.util.HashMap;
import java.util.List;

public interface UserDao {
    List<HashMap<String, Object>> findEmailAndPassword(HashMap map);

    List<HashMap<String, Object>> findEmail(Object email);

    int updatePasswordById(HashMap map);

    List<HashMap<String, Object>> findUserId(Object userId);

    List<HashMap<String, Object>> findUserListByNameAndId(HashMap map);

    int findTotalByNameAndId(HashMap map);

    int addUser(HashMap map);
}
