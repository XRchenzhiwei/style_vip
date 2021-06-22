package style.style_vip.utils.mybatisplus;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.nimbusds.oauth2.sdk.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * @author 1
 */
public class CodeGenerator extends AutoGenerator implements IColumnType {

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        // 当前程序所在目录
        String projectPath = System.getProperty("user.dir");
//        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setOutputDir(projectPath + "/yc-busi-logistics/src/main/java/");
        gc.setAuthor("awchen");
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columList
        gc.setBaseColumnList(true);
        gc.setSwagger2(true);
        // 是否覆盖同名文件，默认是false
        gc.setFileOverride(true);
//        gc.setFileOverride(false);
        gc.setOpen(false);

        mpg.setGlobalConfig(gc);
//        gc.setServiceName("I%sService");
//        gc.setServiceImplName("%sServiceImpl");
//        gc.setMapperName("I%Mapper");
//        gc.setEntityName("%Entity");
        // 不需要ActiveRecord特性的请改为false
        gc.setActiveRecord(true);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setUrl("jdbc:mysql://39.98.214.85:3306/test?useSSL=false&serverTimezone=CTT&unullNamePatternMatchesAll=true");
        dsc.setUrl("jdbc:mysql://192.168.2.44:3306/yunchang_dev?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=CTT&unullNamePatternMatchesAll=true");

        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("ASsdf55");
        dsc.setTypeConvert(new MySqlTypeConvert() {
            @Override
            public DbColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                //timestamp转换成String
                if (fieldType.toLowerCase().contains("timestamp")) {
                    return DbColumnType.STRING;
                }
                //timestamp转换成String
                if (fieldType.toLowerCase().contains("datetime")) {
                    return DbColumnType.STRING;
                }
                return (DbColumnType) super.processTypeConvert(globalConfig, fieldType);
            }
        });
        mpg.setDataSource(dsc);


        // 包配置
        PackageConfig pc = new PackageConfig();
        // 父包名
        pc.setParent("com.yc.logistics");
        // 实体类包名
        pc.setEntity("modules.taxes.entity");
        // Service包名
        pc.setService("modules.taxes.service");
        // ServiceImpl包名
        pc.setServiceImpl("modules.taxes.service.impl");
        // Mapper包名
        pc.setMapper("modules.taxes.mapper");
        // controller包名
        pc.setController("modules.taxes.controller");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/yc-busi-logistics/src/main/resources/mapper/taxes/"
                        //+ pc.getModuleName()
                        //+ "/"
                        + tableInfo.getEntityName() + "Mapper.xml";
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        // 设置模板不生成的文件
        templateConfig.setXml(null);
//        templateConfig.setController(null);
//        templateConfig.setService(null);
//        templateConfig.setServiceImpl(null);
//        templateConfig.setMapper(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("com.baomidou.mybatisplus.extension.activerecord.Model");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setInclude("wl_customs_declaration_data_excel_info");
        strategy.entityTableFieldAnnotationEnable(true);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public String getPkg() {
        return null;
    }

    @Override
    protected List<TableInfo> getAllTableInfoList(ConfigBuilder config) {
        List<TableInfo> tableInfos = super.getAllTableInfoList(config);
        tableInfos.forEach(t -> {
            t.getFields().forEach(f -> {
                if (StringUtils.isNotBlank(f.getComment())) {
                    String comment = f.getComment();
                    comment = comment.replaceAll("\r\n", "");//注意，替换换行符
                    f.setComment(comment);
                }
            });
        });
        return tableInfos;
    }
}