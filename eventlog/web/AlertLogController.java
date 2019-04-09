package com.xlauncher.utils.eventlog.web;


import com.xlauncher.utils.eventlog.entity.AlertLog;
import com.xlauncher.utils.eventlog.service.AlertLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Desc: 告警日志控制器
 *
 * Created by baiShuaiLei on 2018/1/25.
 */
@Controller
@RequestMapping(value = "/cms/alertLog")
public class AlertLogController {

    @Autowired
    private AlertLogService alertLogService;


    /**
     * 添加告警日志信息
     * @param alertLog
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    public Map<String, Object> addAlertLog(@RequestBody AlertLog alertLog){
        int ret = this.alertLogService.addAlertLog(alertLog);
        Map<String, Object> retMap = new HashMap<>(1);
        retMap.put("status", ret);
        return retMap;
    }


    /**
     * 分页查询告警日志信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/page/{number}",method = RequestMethod.GET)
    public Map<String,Object> getAlertLogByPage(@PathVariable("number") int number) throws SQLException {
        List<AlertLog> listAlertLog = this.alertLogService.getAlertLogByPage((number-1)*10);
        Map<String, Object> alertLogMap = new HashMap<>(1);
        alertLogMap.put("alertLog",listAlertLog);
        return alertLogMap;
    }

    /**
     * 获得总数
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/count",method = RequestMethod.GET)
    public int getAlertLogCount(){
        int ret = this.alertLogService.getAlertLogCount();
        return ret;
    }

}
