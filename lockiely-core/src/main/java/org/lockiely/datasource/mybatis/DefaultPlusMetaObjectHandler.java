package org.lockiely.datasource.mybatis;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.entity.TableFieldInfo;
import com.baomidou.mybatisplus.entity.TableInfo;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.baomidou.mybatisplus.toolkit.TableInfoHelper;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.reflection.MetaObject;
import org.lockiely.persistence.entity.BaseEntity;
import org.lockiely.shiro.ShiroUtils;

public class DefaultPlusMetaObjectHandler extends MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {   //用于设置默认字段
        if(metaObject.getOriginalObject() instanceof BaseEntity){
            BaseEntity entity = (BaseEntity) metaObject.getOriginalObject();
            if (StringUtils.checkValNull(entity.getCreateTime())) {
                setFieldValByName("createTime", new Date(), metaObject);
            }
            if(StringUtils.checkValNull(entity.getCreateBy())) {
                setFieldValByName("createBy", ShiroUtils.getUser().getAccount(), metaObject);
            }
            if(StringUtils.checkValNull(entity.getUpdateTime())) {
                setFieldValByName("updateTime", new Date(), metaObject);
            }
            if(StringUtils.checkValNull(entity.getUpdateBy())) {
                setFieldValByName("updateBy", ShiroUtils.getUser().getAccount(), metaObject);
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
        FillDefaultFieldByFillType(metaObject, FieldFill.UPDATE);
    }

    private void FillDefaultFieldByFillType(MetaObject metaObject, FieldFill fieldFill) {
        if(metaObject.getOriginalObject() instanceof BaseEntity){
            BaseEntity entity = (BaseEntity) metaObject.getOriginalObject();
            if(fieldFill == FieldFill.INSERT || fieldFill == FieldFill.INSERT_UPDATE) {
                if (StringUtils.checkValNull(entity.getCreateTime())) {
                    setFieldValByName("createTime", new Date(), metaObject);
                }
                if(StringUtils.checkValNull(entity.getCreateBy())) {
                    setFieldValByName("createBy", ShiroUtils.getUser().getAccount(), metaObject);
                }
                if(StringUtils.checkValNull(entity.getUpdateTime())) {
                    setFieldValByName("updateTime", new Date(), metaObject);
                }
                if(StringUtils.checkValNull(entity.getUpdateBy())) {
                    setFieldValByName("updateBy", ShiroUtils.getUser().getAccount(), metaObject);
                }
                TableInfo tableInfo = TableInfoHelper.getTableInfo(entity.getClass());
                for(TableFieldInfo fieldInfo : tableInfo.getFieldList()){
                    if(fieldInfo.isLogicDelete()){
                        setFieldValByName(fieldInfo.getProperty(), Integer.valueOf(fieldInfo.getLogicNotDeleteValue()), metaObject);
                    }
                }
            }
            if(fieldFill == FieldFill.UPDATE || fieldFill == FieldFill.INSERT_UPDATE) {
                if(StringUtils.checkValNull(entity.getUpdateTime())) {
                    setFieldValByName("updateTime", new Date(), metaObject);
                }
                if(StringUtils.checkValNull(entity.getUpdateBy())) {
                    setFieldValByName("updateBy", ShiroUtils.getUser().getAccount(), metaObject);
                }
                TableInfo tableInfo = TableInfoHelper.getTableInfo(entity.getClass());
                for(TableFieldInfo fieldInfo : tableInfo.getFieldList()){
                    if(fieldInfo.isLogicDelete()){
                        setFieldValByName(fieldInfo.getProperty(), Integer.valueOf(fieldInfo.getLogicNotDeleteValue()), metaObject);
                    }
                }
            }
        }
    }
}
