package org.lockiely.persistence.entity.enums;

import com.baomidou.mybatisplus.enums.IEnum;
import java.io.Serializable;

public enum StatusEnum implements IEnum {

    CREATE(10, "创建");

    private int status;

    private String description;

    StatusEnum(int status, String description){
        this.status = status;
        this.description = description;
    }


    @Override
    public Serializable getValue() {
        return status;
    }

    public String getDescription() {
        return description;
    }
}
