package com.wbu.service;

import java.sql.Connection;
import java.util.Map;

import com.wbu.dao.LogRecordDao;
import com.wbu.entity.LogInfo;
import com.wbu.entity.LogInfoVO;

import cn.hutool.db.DbUtil;

public class LogService {

    LogRecordDao logRecordDao=new LogRecordDao();

    public LogInfoVO businessProcess(LogInfoVO logInfoVO){
        LogInfoVO resultVO=null;
        Object tempObj=logInfoVO.getInstance();
        if(null!=tempObj){
            if(tempObj instanceof Map<?,?>){
                resultVO = logRecordDao.queryLogInfoResultVO((Map<String,String>)tempObj);
            }
            if(tempObj instanceof LogInfo){
                //to do
                resultVO = logRecordDao.insertLogEntity((LogInfo)tempObj);
            }
        }

        return resultVO;
    }
}
