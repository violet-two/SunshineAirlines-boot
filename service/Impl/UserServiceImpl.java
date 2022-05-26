package edu.wtbu.service.Impl;

import edu.wtbu.dao.UserDao;
import edu.wtbu.pojo.Page;
import edu.wtbu.pojo.Result;
import edu.wtbu.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public Result login(HashMap map) {
        Result result = new Result("fail",null,null);
        List<HashMap<String,Object>> list = userDao.findEmailAndPassword(map);
        if(list!=null&&list.size()>0){
            result.setFlag("success");
            result.setData(list);
        }else{
            if(findEmail(map.get("email"))){
                result.setData("密码错误");
            }else{
                result.setData("邮箱不存在");
            }
        }
        return result;
    }

    @Override
    public Result updatePassword(HashMap map) {
        Result result = new Result("fail",null,null);
        int rs = userDao.updatePasswordById(map);
        if(rs>0){
            result.setFlag("success");
        }else{
            if(!findUserId(map.get("userId"))){
                result.setData("用户信息不存在");
            }
        }
        return result;
    }

    @Override
    public Result getUserList(HashMap map) {
        Result result = new Result("fail",null,null);
        int total = userDao.findTotalByNameAndId(map);
        int pageSize = Integer.parseInt(map.get("pageSize").toString());
        int startPage = Integer.parseInt(map.get("startPage").toString());
        Page page = new Page(pageSize,startPage,total);
        map.put("startPage",(startPage-1)*10);
        List<HashMap<String,Object>> list = userDao.findUserListByNameAndId(map);
        if(list!=null&&list.size()>0){
            result.setFlag("success");
            result.setData(list);
            result.setPage(page);
        }
        return result;
    }

    @Override
    public Result addUser(HashMap map) {
        Result result = new Result("fail",null,null);
        if(findEmail(map.get("email"))){
            result.setData("邮箱已存在");
            return result;
        }
        int rs = userDao.addUser(map);
        if(rs>0){
            result.setFlag("success");
        }
        return result;
    }

    private boolean findUserId(Object userId) {
        List<HashMap<String,Object>> list = userDao.findUserId(userId);
        if(list!=null&&list.size()>0){
            return true;
        }else{
            return false;
        }
    }


    private boolean findEmail(Object email) {
        List<HashMap<String,Object>> list = userDao.findEmail(email);
        if(list!=null&&list.size()>0){
            return true;
        }else{
            return false;
        }
    }
}
