package org.lockiely.web.mvc.converter;

public class PhoneNumber {

    public static final String TEL = "tel";

    public static final String PHONE = "phone";

    private String telPhoneType;

    private String areaCode;

    private String telPhoneCode;

    public PhoneNumber(){

    }

    public PhoneNumber(String telPhoneCode) {
        this.telPhoneType = PHONE;
        determinePhoneNumber(this.telPhoneType, telPhoneCode);
    }

    public PhoneNumber(String telPhoneType, String areaCode, String telPhoneCode) {
        this.telPhoneType = telPhoneType;
        this.areaCode = areaCode;
        determinePhoneNumber(telPhoneType, telPhoneCode);
    }

    public PhoneNumber(String telPhoneType, String telPhoneCode) {
        this.telPhoneType = telPhoneType;
        determinePhoneNumber(telPhoneType, telPhoneCode);
    }

    private void determinePhoneNumber(String telPhoneType, String telPhoneCode){
        if(telPhoneType.equals(PHONE)){
            if(telPhoneCode.length() != 11){
                //FIXME:
            }
            this.telPhoneCode = telPhoneCode;
        }else if(telPhoneType.equals(TEL)){
            this.telPhoneCode = telPhoneCode;
        }
    }

    public String getTelPhoneType() {
        return telPhoneType;
    }

    public void setTelPhoneType(String telPhoneType) {
        this.telPhoneType = telPhoneType;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getTelPhoneCode() {
        return telPhoneCode;
    }

    public void setTelPhoneCode(String telPhoneCode) {
        this.telPhoneCode = telPhoneCode;
    }
}
