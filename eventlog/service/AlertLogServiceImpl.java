package com.xlauncher.utils.eventlog.service;


import com.xlauncher.utils.eventlog.dao.AlertLogDao;
import com.xlauncher.utils.eventlog.entity.AlertLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * 服务层实现类
 * @author 白帅雷
 * @date 2018-02-02
 */
@Service
public class AlertLogServiceImpl implements AlertLogService {

    @Autowired
    @Qualifier("alertLogDao")
    private AlertLogDao alertLogDao;


    /**
     * 添加告警日志信息
     * @param alertLog
     * @return
     */
    @Override
    public int addAlertLog(AlertLog alertLog) {
        return alertLogDao.addAlertLog(alertLog);
    }

    /**
     * 分页查询告警日志信息
     * @return
     */
    @Override
    public List<AlertLog> getAlertLogByPage(@Param(value="startPos") int startPos) throws SQLException {
        return alertLogDao.getAlertLogByPage(startPos);
    }

    /**
     * 获得总数
     * @return
     */
    @Override
    public int getAlertLogCount() {
        return alertLogDao.getAlertLogCount();
    }
}
