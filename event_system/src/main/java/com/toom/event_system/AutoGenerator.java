package com.toom.event_system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 代码生成
 * @author Toom
 */
public class AutoGenerator {
    private static final String url = "jdbc:mysql://localhost:3306/event_system?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
    private static final String username = "root";
    private static final String password = "123456";

    public static void main(String[] args) {
        List<String> tables = new ArrayList<>();
        tables.add("user");
        tables.add("community");
        tables.add("activity");
        tables.add("confirmation");
        tables.add("message");
        tables.add("notice");
        tables.add("news");
        tables.add("role");
        tables.add("menu");

        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("Toom")               //作者
                            .outputDir("D://Computer/Java_code/JavaWeb/Auto/")   // 指定输出目录
                            .enableSwagger()           //开启swagger
                            .commentDate("yyyy-MM-dd")
                            .fileOverride();            //开启覆盖之前生成的文件

                })
                .packageConfig(builder -> {
                    builder.parent("com.toom")          // 设置父包名
                            .moduleName("event_system")  // 设置父包模块名
                            .entity("entity")
                            .service("service")
                            .serviceImpl("serviceImpl")
                            .controller("controller")
                            .mapper("mapper")
                            .xml("mapper")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D://Computer/Java_code/JavaWeb/Auto/"));// 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tables)       // 设置需要生成的表名
                            .addTablePrefix("t_", "c_")       // 设置过滤表前缀
                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .entityBuilder()
                            .enableLombok()
                            .logicDeleteColumnName("deleted")
                            .enableTableFieldAnnotation()
                            .controllerBuilder()
                            .formatFileName("%sController")
                            .enableRestStyle()
                            .mapperBuilder()
                            .enableBaseResultMap()  //生成通用的resultMap
                            .superClass(BaseMapper.class)
                            .formatMapperFileName("%sMapper")
                            .enableMapperAnnotation()
                            .formatXmlFileName("%sMapper");
                })
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
