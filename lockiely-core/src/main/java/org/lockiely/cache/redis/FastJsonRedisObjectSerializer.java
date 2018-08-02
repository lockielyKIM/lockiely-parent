package org.lockiely.cache.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import java.nio.charset.Charset;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * 用于对session 和 cache 进行序列化操作
 *
 * @author: lockiely
 * @Date: 2018/7/27 12:31
 * @email: lockiely@163.com
 */
public class FastJsonRedisObjectSerializer<T> implements RedisSerializer<T> {

    private FastJsonConfig config = new FastJsonConfig();

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private Class<T> clazz;

    public FastJsonRedisObjectSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
        config.setDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        config.setSerializerFeatures(SerializerFeature.WriteClassName, SerializerFeature.WriteDateUseDateFormat);
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
//        ParserConfig.getGlobalInstance().addAccept("org.lockiely.shiro.session");
//        config.setParserConfig();
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        }
        return JSON.toJSONString(t, config.getSerializerFeatures()).getBytes(DEFAULT_CHARSET);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        String str = new String(bytes, DEFAULT_CHARSET);

        return (T) JSON.parseObject(str, clazz);
    }
}
