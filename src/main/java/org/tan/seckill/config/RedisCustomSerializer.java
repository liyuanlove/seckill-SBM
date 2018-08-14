package org.tan.seckill.config;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.lang.Nullable;
import org.tan.seckill.po.Seckill;

/**
 * 自定义的redis序列化
 * @author ibm
 * @since 0
 * @date 2018-4-22
 */
public class RedisCustomSerializer implements RedisSerializer {

    private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);

    @Nullable
    @Override
    public byte[] serialize(@Nullable Object o) throws SerializationException {
        Seckill seckill = (Seckill)o;
        byte[] bytes = ProtostuffIOUtil.toByteArray(seckill,schema,
                LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
        return bytes;
    }

    @Nullable
    @Override
    public Object deserialize(@Nullable byte[] bytes) throws SerializationException {
        if(bytes != null){
            Seckill seckill = schema.newMessage();
            //反序列化
            ProtostuffIOUtil.mergeFrom(bytes,seckill,schema);
            return seckill;
        }else {
            return null;
        }
    }
}