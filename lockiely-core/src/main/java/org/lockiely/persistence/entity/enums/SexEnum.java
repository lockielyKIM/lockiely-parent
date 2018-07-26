package org.lockiely.persistence.entity.enums;

import com.baomidou.mybatisplus.enums.IEnum;
import java.io.Serializable;

public enum SexEnum implements IEnum {

    MAN(1, "男"), WOMEN(2, "女");

    private Integer sex;

    private String des;

    SexEnum(Integer sex, String des){
        this.sex = sex;
        this.des = des;
    }

    public Integer getSex() {
        return sex;
    }

    public String getDes() {
        return des;
    }

    @Override
    public Serializable getValue() {
        return this.sex;
    }
}
