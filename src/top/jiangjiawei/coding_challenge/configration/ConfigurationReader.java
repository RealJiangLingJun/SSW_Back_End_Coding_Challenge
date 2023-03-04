package top.jiangjiawei.coding_challenge.configration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

public class ConfigurationReader {
    /**
     * 这个类是为了读取properties配置文件而设立的
     * 整个项目启动的时候读取一次就够了
     * 将文件中的数据临时的存储在一个Map集合中------缓存
     */

    private static HashMap<String, String> cfg = new HashMap<>();

    // 这个类需要在类加载是就将配置文件中的数据读到
    static { // 静态块是在类加载到内存时执行的，且只执行一次
        Properties properties = new Properties();
        try {
            InputStream inputStream = Thread.currentThread().
                    getContextClassLoader().
                    getResourceAsStream("./Configuration.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Enumeration enumeration = properties.propertyNames();
        while (enumeration.hasMoreElements()){
            String name = (String) enumeration.nextElement();
            String value = properties.getProperty(name);
            cfg.put(name,value);
        }
    }

    // 对外暴露静态方法，通过键来获取配置文件中获得配置项
    public static String getStringValue(String name){
        return cfg.get(name);
    }
    public static int getIntValue(String name){
        return Integer.parseInt(cfg.get(name));
    }
    public static float getFloatValue(String name){
        return Float.parseFloat(cfg.get(name));
    }
}
