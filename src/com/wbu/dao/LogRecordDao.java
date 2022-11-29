package com.wbu.dao;



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

    public String getMysqlPaht(){
        String sql="show variables like '%secure%'";
        conn=dbUtil.getConnection();
        
        try {        
            PreparedStatement preparedStatement=conn.prepareStatement(sql.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet result=preparedStatement.executeQuery();

            result.next();
            result.next();
            String resultStr= result.getString(2);
            return resultStr;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }


    public Boolean ExportRecordLog(String callSql,String databaseName,String dataTableName){
        conn=dbUtil.getConnection();
        PreparedStatement pstmt=null;

        try {
            pstmt=conn.prepareStatement(callSql);
            pstmt.setString(1, dataTableName);
            pstmt.setString(2, databaseName);
            pstmt.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();            
        }finally{
            try{
                if(null!=pstmt){                
                    pstmt.close();                
                }
                if(conn!=null){
                    conn.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
        return false;
    }

    public boolean batchRecordLogInsert(String sqlValueArray[]){

        StringBuilder sqlBuiler=new StringBuilder("insert into record_log")
        .append("(record_time,")
        .append("log_type,")
        .append("ip_addr,")
        .append("log_content)")
        .append(" values(?,?,?,?) ");

        conn=dbUtil.getConnection();
        PreparedStatement pstmt=null;
        try {
            int loopTimes=0;
            conn.setAutoCommit(false);
            pstmt=conn.prepareStatement(sqlBuiler.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            for(String sqlValueObject : sqlValueArray){
                loopTimes++;
                if("".equals(sqlValueObject)||null==sqlValueObject){
                    continue;
                }
                String repalceValue[]=sqlValueObject.split(",");
                pstmt.setString(1, repalceValue[0]);
                pstmt.setString(2, repalceValue[1]);
                pstmt.setString(3, repalceValue[2]);
                pstmt.setString(4, repalceValue[3]);
                pstmt.addBatch();

                if(loopTimes%100==0){
                    pstmt.executeBatch();
                    pstmt.clearBatch();
                }
                System.out.println(loopTimes);
            }
            pstmt.executeBatch();
            pstmt.clearBatch();;
            conn.commit();
            return true;
        } catch (Exception e) {
            // TODO: handle exception
        	e.printStackTrace();
        }finally{
            try{
                if(null!=pstmt){                
                    pstmt.close();                
                }
                if(conn!=null){
                    conn.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
        return false;
    }
    
    public LogInfoVO insertLogEntity(LogInfo logInfo){

        int result=-1;
        LogInfoVO logInfoVO=new LogInfoVO();
        StringBuilder sqlBuiler=new StringBuilder("insert into record_log")
                    .append("(ip_addr,")
                    .append("log_content,")
                    .append("record_time,")
                    .append("log_type)")
                    .append(" values(?,?,?,?); ");
        
        conn=dbUtil.getConnection();
        try {
            PreparedStatement pstmt=conn.prepareStatement(sqlBuiler.toString());
            pstmt.setString(1, logInfo.getIpAddr());
            pstmt.setString(2, logInfo.getExceptionContent());
            pstmt.setString(3, logInfo.getRecordTime());
            pstmt.setString(4, logInfo.getExceptionType());
            result=pstmt.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            if(result>0){
                logInfoVO.setLogInfo(logInfo);
            }
        }
        return logInfoVO;
    }

    public LogInfoVO queryLogInfoResultVO(Map<String,String> keyValue){
        LogInfoVO logInfoVO=null;
        List<LogInfo> resultLogInfo=null;
        StringBuffer sb=new StringBuffer("select log_id,")
            .append("log_content,")
            .append("record_time,")
            .append("log_type,")
            .append("ip_addr")
            .append(" from record_log where 1=1 ");                

        if(keyValue.size()>-1){
            Iterator<Entry<String,String>> conditionSetIterator =keyValue.entrySet().iterator();
            while (conditionSetIterator.hasNext()) {      
                Entry<String,String> entryObj=conditionSetIterator.next();
                sb.append(" and ");
                sb.append(entryObj.getKey());
                sb.append(" = '");
                sb.append(entryObj.getValue());
                sb.append("'");
            }
        }
        conn = dbUtil.getConnection();
        try {
            PreparedStatement preparedStatement=conn.prepareStatement(sb.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
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

    public ResultSet queryLogInfoResultSet(Map<String,String> keyValue){
        StringBuffer sb=new StringBuffer("select log_id,")
            .append("log_content,")
            .append("record_time,")
            .append("log_type,")
            .append("ip_addr ")
            .append(" from record_log where 1=1 ");                

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
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
