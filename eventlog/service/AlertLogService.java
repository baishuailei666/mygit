package com.xlauncher.utils.eventlog.service;

import com.xlauncher.utils.eventlog.entity.AlertLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * 服务层接口
 * @author 白帅雷
 * @date 2018-02-02
 */
@Service
public interface AlertLogService {

    /**
     * 添加告警日志信息
     * @param alertLog
     * @return
     */
    public int addAlertLog(AlertLog alertLog);


    /**
     * 分页查询告警日志信息
     * @param startPos
     * @return
     */
    public List<AlertLog> getAlertLogByPage(@Param(value="startPos") int startPos) throws SQLException;

    /**
     * 获得总数
     * @return
     */
    public int getAlertLogCount();
}
