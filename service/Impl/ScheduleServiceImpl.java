package edu.wtbu.service.Impl;

import edu.wtbu.dao.ScheduleDao;
import edu.wtbu.pojo.Result;
import edu.wtbu.service.ScheduleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Resource
    private ScheduleDao scheduleDao;

    @Override
    public Result getSchedule(HashMap<String, Object> map) {
        Result result = new Result("fail",null,null);
        List<HashMap<String,Object>> list = scheduleDao.getSchedule(map);
        if(list!=null&&list.size()>0){
            result.setFlag("success");
            result.setData(list);
        }
        return result;
    }

    @Override
    public Result updateSchedule(HashMap<String, Object> map) {
        Result result = new Result("fail",null,null);
        List<HashMap<String,Object>> list = scheduleDao.findScheduleById(map.get("scheduleId"));
        if(list.size()==0){
            result.setData("航班信息不存在");
            return result;
        }
        int rs = scheduleDao.updateSchedule(map);
        if(rs>0){
            result.setFlag("success");
        }
        return result;
    }

    @Override
    public Result getTicketStatistics(HashMap<String, Object> map) {
        Result result = new Result("fail",null,null);
        String endDate = "";
        int year = Integer.parseInt(map.get("endDate").toString().split("-")[0]);
        int month = Integer.parseInt(map.get("endDate").toString().split("-")[1]);
        if(month<9){
            month++;
            endDate = year+"-0"+month+"-01 00:00:00";
        }else if(month>=12){
            year++;
            endDate = year+"-01-01 00:00:00";
        }else{
            month++;
            endDate = year+"-"+month+"-01 00:00:00";
        }
        map.put("endDate",endDate);
        List<HashMap<String,Object>> list = scheduleDao.getTicketStatistics(map);
        if(list!=null&&list.size()>0){
            for (HashMap<String,Object> hashmap:list) {
                Integer Year = Integer.parseInt(hashmap.get("Year").toString());
                Integer Month = Integer.parseInt(hashmap.get("Month").toString());
                String MonthStr = "";
                if(Month<10){
                    MonthStr = Year +"-0"+Month;
                }else{
                    MonthStr = Year +"-"+Month;
                }
                hashmap.put("Month",MonthStr);
            }
            result.setFlag("success");
            result.setData(list);
        }
        return result;
    }
}
