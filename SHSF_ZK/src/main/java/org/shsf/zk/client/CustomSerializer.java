package org.shsf.zk.client;

import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;

import java.io.UnsupportedEncodingException;

/**
 * 由于zkClient创建连接的时候指定了默认的序列化类-new SerializableSerializer(),
 * 所以存储在节点上的值也是序列化后的字节数组，当使用zkCli.sh在控制台set /xxx/xx的值时，
 * 存储的是普通的字符串字节数组。所以当set值时虽然触发了值改变事件，但zkClient无法反序列化这个值。
 * 要使zkCli.sh的值也能被zkClient读取必须通过实现ZkSerializer接口使用自定义的序列化类：
 * 
 * 
 * 自定义序列化类
 * 实现普通字符串的序列化与反序列化
 * 默认使用utf-8编码
 * Created by xiaopeng on 2016/12/15.
 */
public class CustomSerializer implements ZkSerializer{
    /** default utf 8*/
    private String charset = "UTF-8";

    public CustomSerializer(){}

    public CustomSerializer(String charset){
        this.charset = charset;
    }
    public byte[] serialize(Object data) throws ZkMarshallingError {
        try{
            byte[] bytes = String.valueOf(data).getBytes(charset);
            return bytes;
        }catch (UnsupportedEncodingException e){
            throw new ZkMarshallingError("Wrong Charset:" + charset);
        }
    }

    public Object deserialize(byte[] bytes) throws ZkMarshallingError {
        String result = null;
        try {
            result = new String(bytes,charset);
        } catch (UnsupportedEncodingException e) {
            throw new ZkMarshallingError("Wrong Charset:" + charset);
        }
        return result;
    }
}