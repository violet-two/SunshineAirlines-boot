package edu.wtbu.service.Impl;

import edu.wtbu.dao.CityDao;
import edu.wtbu.pojo.Result;
import edu.wtbu.service.CityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Resource
    private CityDao cityDao;

    @Override
    public Result getCityNames() {
        Result result = new Result("fail",null,null);
        List<HashMap<String,Object>> list = cityDao.findCityNames();
        if(list!=null&&list.size()>0){
            result.setFlag("success");
            result.setData(list);
        }
        return result;
    }
}
