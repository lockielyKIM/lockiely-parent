package org.lockiely.persistence.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.apache.ibatis.annotations.Param;

public interface SuperMapper<T> extends BaseMapper<T> {

    int updateCustomByWrapper(@Param("et") T entity, @Param("ew") Wrapper<T> wrapper);

}
