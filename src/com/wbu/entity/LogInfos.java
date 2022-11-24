package com.wbu.entity;

import java.io.PushbackInputStream;

public class LogInfos {

    private int logId;
    private String ipAddr;
    private String recordTime;
    private String exceptionType;
    private String exceptionContent;

    private LogInfos(LoginfosBuilder builder){
        this.logId=builder.logId;
        this.ipAddr=builder.ipAddr;
        this.recordTime=builder.recordTime;
        this.exceptionType=builder.exceptionType;
        this.exceptionContent=builder.exceptionContent;
    }
    
    public LogInfos(int logId, String ipAddr, String recordTime, String exceptionType, String exceptionContent) {
        super();
        this.logId = logId;
        this.ipAddr = ipAddr;
        this.recordTime = recordTime;
        this.exceptionType = exceptionType;
        this.exceptionContent = exceptionContent;
    }

    //#region loginfoBuilder
    public static class LoginfosBuilder{
        private int logId;
        private String ipAddr;
        private String recordTime;
        private String exceptionType;
        private String exceptionContent;

        public LoginfosBuilder setRecordTime(String recordTime) {
            this.recordTime = recordTime;
            return this;
        }

        public LoginfosBuilder setIpAddr(String ipAddr) {
            this.ipAddr = ipAddr;
            return this;
        }

        public LoginfosBuilder setLogId(int logId) {
            this.logId = logId;
            return this;
        }

        public LoginfosBuilder setExceptionType(String exceptionType) {
            this.exceptionType = exceptionType;
            return this;
        }

        public LoginfosBuilder setExceptionContent(String exceptionContent) {
            this.exceptionContent = exceptionContent;
            return this;
        }


        public LoginfosBuilder(int logId, String ipAddr, String recordTime) {
            this.logId = logId;
            this.ipAddr = ipAddr;
            this.recordTime = recordTime;
        }

        public int getLogId() {
            return logId;
        }


        public String getIpAddr() {
            return ipAddr;
        }

        public String getRecordTime() {
            return recordTime;
        }

        public LoginfosBuilder(){
            super();
        }    

        public LogInfos build(){
            return new LogInfos(this);
        }
    }
    //#endregion
    
    //#region SetterAndGeter
    public LogInfos(){
        super();
    }
    public int getLogId() {
        return logId;
    }
    public void setLogId(int logId) {
        this.logId = logId;
    }
    public String getIpAddr() {
        return ipAddr;
    }
    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }
    public String getRecordTime() {
        return recordTime;
    }
    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }
    public String getExceptionType() {
        return exceptionType;
    }

    public String getExceptionContent() {
        return exceptionContent;
    }

    //#endregion

    public static void main(String[] args) {
        LogInfos loginf=new LogInfos.LoginfosBuilder(100,"127.0.0.1","11")
        .setExceptionType("info")
        .setExceptionContent("test")
        .build();
    }
}
