package edu.wtbu.controller;

import edu.wtbu.pojo.Result;
import edu.wtbu.service.ScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;

@Controller
public class ScheduleController {

    @Resource
    private ScheduleService scheduleService;

    @RequestMapping("/getSchedule")
    @ResponseBody
    public Object getSchedule(String fromCity,String toCity,String startDate,String endDate){
        HashMap<String,Object> map = new HashMap<>();
        map.put("fromCity",fromCity);
        map.put("toCity",toCity);
        map.put("startDate",startDate+" 00:00:00");
        map.put("endDate",endDate+" 23:59:59");
        Result result = scheduleService.getSchedule(map);
        return result;
    }
    @RequestMapping("/updateSchedule")
    @ResponseBody
    public Object updateSchedule(Integer scheduleId,String status){
        HashMap<String,Object> map = new HashMap<>();
        map.put("scheduleId",scheduleId);
        map.put("status",status);
        Result result = scheduleService.updateSchedule(map);
        return result;
    }
    @RequestMapping("/getTicketStatistics")
    @ResponseBody
    public Object getTicketStatistics(String startDate,String endDate){
        HashMap<String,Object> map = new HashMap<>();
        map.put("startDate",startDate+"-01 00:00:00");
        map.put("endDate",endDate);
        Result result = scheduleService.getTicketStatistics(map);
        return result;
    }
}
