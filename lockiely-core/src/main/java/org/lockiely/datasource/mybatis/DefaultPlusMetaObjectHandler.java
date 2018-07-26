package org.lockiely.datasource.mybatis;

import com.baomidou.mybatisplus.entity.TableFieldInfo;
import com.baomidou.mybatisplus.entity.TableInfo;
import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.baomidou.mybatisplus.toolkit.TableInfoHelper;
import java.util.Date;
import org.apache.ibatis.reflection.MetaObject;
import org.lockiely.persistence.entity.BaseEntity;

public class DefaultPlusMetaObjectHandler extends MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {   //用于设置默认字段
        if(metaObject.getOriginalObject() instanceof BaseEntity){
            BaseEntity entity = (BaseEntity) metaObject.getOriginalObject();
            if (StringUtils.checkValNull(entity.getCreateTime())) {
                setFieldValByName("createTime", new Date(), metaObject);
            }
            TableInfo tableInfo = TableInfoHelper.getTableInfo(entity.getClass());
            for(TableFieldInfo fieldInfo : tableInfo.getFieldList()){
                if(fieldInfo.isLogicDelete()){
                    setFieldValByName(fieldInfo.getProperty(), Integer.valueOf(fieldInfo.getLogicNotDeleteValue()), metaObject);
                }
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
//        if(metaObject.getOriginalObject() instanceof BaseEntity){
//            BaseEntity entity = (BaseEntity) metaObject.getOriginalObject();
//            if(StringUtils.checkValNull(entity.getUpdateTime())){
//                setFieldValByName("updateTime", new Date(), metaObject);
//            }
//        }
    }


}
