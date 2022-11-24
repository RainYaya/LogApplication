package com.wbu.dao;


import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.wbu.entity.LogInfoVO;
import com.wbu.entity.LogInfo;
import com.wbu.utils.DBUtil;

public class LogRecordDao {
    DBUtil dbUtil=new DBUtil();
    Connection conn=null;

    public LogInfoVO queryLogInfoResultSet(Map<String,String> keyValue){
        LogInfoVO logInfoVO=null;
        List<LogInfo> resultLogInfo=null;
        StringBuffer sb=new StringBuffer("select log_id,")
            .append("log_content,")
            .append("record_time,")
            .append("log_type")
            .append("ip_addr")
            .append(" where 1=1 ");                

        if(keyValue.size()>-1){
            Iterator<Entry<String,String>> conditionSetIterator =keyValue.entrySet().iterator();
            while (conditionSetIterator.hasNext()) {      
                Entry<String,String> entryObj=conditionSetIterator.next();
                sb.append("and");
                sb.append(entryObj.getKey());
                sb.append(" = '");
                sb.append(entryObj.getValue());
                sb.append("'");
            }
        }
        conn = dbUtil.getConnection();
        try {
            PreparedStatement preparedStatement=conn.prepareStatement(sb.toString());
            ResultSet result=preparedStatement.executeQuery();

            LogInfo loginfoTemp=null;
            if(result.next()){
                resultLogInfo =new ArrayList<LogInfo>();
                logInfoVO=new LogInfoVO();
                result.previous();
            }

            while (result.next()) {
                loginfoTemp=new LogInfo();
                loginfoTemp.setLogId(result.getInt(1));
                loginfoTemp.setIpAddr(result.getString(2));
                loginfoTemp.setExceptionContent(result.getString(3));
                loginfoTemp.setRecordTime(result.getString(4));
                loginfoTemp.setExceptionType(result.getString(5));
                resultLogInfo.add(loginfoTemp);
            }
            logInfoVO.setLogInfoList(resultLogInfo);
            return logInfoVO;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
