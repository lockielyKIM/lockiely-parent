package org.lockiely.web.mvc.converter;


import java.util.concurrent.ThreadPoolExecutor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.lockiely.utils.StringUtils;
import org.springframework.core.convert.converter.Converter;

public class StringToPhoneNumberConverter implements Converter<String, PhoneNumber> {

    Pattern pattern = Pattern.compile("^(\\\\d{3,4})-(\\\\d{7,8})$");

    @Override
    public PhoneNumber convert(String source) {
        if(!StringUtils.hasLength(source)) {
            return null;
        }

        Matcher matcher = pattern.matcher(source);
        if(matcher.matches()){
            PhoneNumber phoneNumber = new PhoneNumber();
            phoneNumber.setTelPhoneType(PhoneNumber.TEL);
            phoneNumber.setAreaCode(matcher.group(1));
            phoneNumber.setTelPhoneCode(matcher.group(2));
            return phoneNumber;
        }else{
            source = StringUtils.trimAllWhitespace(source);
            PhoneNumber phoneNumber = new PhoneNumber(PhoneNumber.PHONE, source);
            return phoneNumber;
        }
    }
}
