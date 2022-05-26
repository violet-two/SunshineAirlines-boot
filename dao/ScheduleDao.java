package edu.wtbu.dao;

import java.util.HashMap;
import java.util.List;

public interface ScheduleDao {
    List<HashMap<String, Object>> getSchedule(HashMap<String, Object> map);

    int updateSchedule(HashMap<String, Object> map);

    List<HashMap<String, Object>> findScheduleById(Object scheduleId);

    List<HashMap<String, Object>> getTicketStatistics(HashMap<String, Object> map);
}
