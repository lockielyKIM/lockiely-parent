package org.lockiely.persistence.entity.enums;

import com.baomidou.mybatisplus.enums.IEnum;
import java.io.Serializable;

/**
 * @author: lockiely
 * @Date: 2018/7/21 14:04
 * @email: lockiely@163.com
 */
public enum ActiveEnum implements IEnum {

    ENABLE(1, "启用"), FREEZED(0, "冻结");

    private int code;

    private String des;

    ActiveEnum(int code, String des) {
        this.code = code;
        this.des = des;
    }

    @Override
    public Serializable getValue() {
        return code;
    }
}
