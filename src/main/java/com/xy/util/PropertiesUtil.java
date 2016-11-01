package com.xy.util;


import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.util.Collections;
import java.util.List;

/**
 * 配置文件工具类
 * Created by XiuYang on 2016/11/1.
 */
public class PropertiesUtil {
    private static Configuration config;

    static {
        Parameters params = new Parameters();
        FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                        .configure(params.properties()
                                .setFileName("config.properties")
                                .setListDelimiterHandler(new DefaultListDelimiterHandler(','))//逗号分隔的属性解析为List
                        .setEncoding("GBK"));
        try {
            config = builder.getConfiguration();
            //添加属性
            //config.setProperty("colors.background", "#000000");
            //builder.save();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key){
        if(config.containsKey(key)){
            return config.getString(key);
        }
        return "";
    }

    /**
     * 获取配置为list,英文逗号分隔
     * @param key
     * @return
     */
    public static List<String> getList(String key){
        if(config.containsKey(key)){
            return config.getList(String.class,key);
        }
        return Collections.EMPTY_LIST;
    }
    public static void main(String[] args) {
        System.out.println(config.getString("username"));
        System.out.println(config.getInt("password"));
        config.getList("lovegames").forEach(System.out::println);
        System.out.println(config.getString("test"));
        System.out.println(config.getString("key"));
        config.getList(String.class,"config.dirs").forEach(System.out::println);
        System.out.println(config.containsKey("asdf"));
        config.getKeys().forEachRemaining(System.out::println);
        config.setProperty("asdf","aaaaaaaaaaaaaa");
        System.out.println(config.getString("asdf"));

        System.out.println(get("username"));
        getList("lovegames").forEach(System.out::println);
    }
}
