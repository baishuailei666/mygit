package com.xlauncher.utils.eventlog.dao;

import com.xlauncher.utils.eventlog.entity.AlertLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 告警日志接口
 * @author 白帅雷
 * @date 2018-02-02
 */
@Service
public interface AlertLogDao {

    /**
     * 添加告警日志信息
     * @param alertLog
     * @return
     */
    public int addAlertLog(AlertLog alertLog);


    /**
     * 分页查询
     * @param startPos
     * @return
     */
    public List<AlertLog> getAlertLogByPage(@Param(value="startPos") int startPos);

    /**
     * 总数
     * @return
     */
    public int getAlertLogCount();
}
