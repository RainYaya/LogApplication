package com.wbu.entity;

public enum GeneralEnumObject {

    NULLPOINTER_EXCEPTION("空指针异常","NULLPOINTER_EXCEPTION"),
    INDEXOUTOFBOUNDS_EXCEPTION("数组越界异常","INDEXOUTOFBOUNDS_EXCEPTION"),
    TYPEMISSING_EXCEPTION("类型不匹配异常","TYPEMISSING_EXCEPTION"),
    CONNECTIONREFUESED_EXCEPTION("连接被拒绝异常","CONNECTIONREFUESED_EXCEPTION"),

    IP_ADDR("ip地址","ip_addr"),
    RECORD_TIME("录入时间","record_time"),
    EXCEPTIOPN_CONTENT("异常内容","log_content"),
    EXCEPTIOPN_TYPE("异常类型","log_type");
    
    public final String code;
    public final String value;
	GeneralEnumObject(String code, String value) {
		// TODO Auto-generated constructor stub
        this.code=code;
        this.value=value;        
	}

    private String code(){
        return this.code;
    }
    private String value(){
        return this.value;
    }

    public static String GetValue(String code){
        GeneralEnumObject[] _codeEnums=values(); //code？？？
        for(GeneralEnumObject codeEnum:_codeEnums){
            if(codeEnum.code().equals(code)){
                return codeEnum.value();
            }
        }
        return null;
    }
    public static String GetCode(String value){
        GeneralEnumObject[] _valueEnums=values();
        for(GeneralEnumObject valueEnum:_valueEnums){
            if(valueEnum.value().equals(value)){
                return valueEnum.code();
            }
        }
        return null;
    }
}
