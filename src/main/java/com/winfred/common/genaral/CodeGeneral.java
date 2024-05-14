package com.winfred.common.genaral;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.winfred.common.entity.BasePageRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * @author winfred
 */
@Slf4j
public class CodeGeneral {

  public interface Constant {

    // database name
    String DB_SCHEMA_NAME = "oauth2_authorization_server";

    String DB_JDBC_URL = "jdbc:mysql://10.101.110.111:13306" +
        "/" + DB_SCHEMA_NAME + "?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    String DB_USER_NAME = "root";
    String DB_USER_PASSWORD = "123456";
    String DB_DRIVER_NAME = "com.mysql.jdbc.Driver";

    /**
     * package 信息
     */
    String PACKAGE_PATH = "com.winfred.mall";
    String SUB_PACKAGE_PATH = "auth";

    /**
     * fixed
     */
    String DEFAULT_PACKAGE_NAME_ENTITY = "entity";
    String DEFAULT_PACKAGE_NAME_MAPPER = "mapper";
    String DEFAULT_PACKAGE_NAME_XML = "mapper.xml";
    String DEFAULT_PACKAGE_NAME_SERVICE = "service";
    String DEFAULT_PACKAGE_NAME_SERVICE_IMPL = "service.impl";
    String DEFAULT_PACKAGE_NAME_CONTROLLER = "controller";
  }

  public static void main(String[] args) {

    log.info("code general start ...");

    String MODULE_NAME = "auto-general";
    String OUTPUT_DIR = "src/main/java";

    DataSourceConfig datasourceConfig = getDataSourceConfig();

    AutoGenerator generator = new AutoGenerator(datasourceConfig);

    final GlobalConfig.Builder builder = new GlobalConfig.Builder();
    final GlobalConfig globalConfig = builder
//            .outputDir(System.getProperty("user.dir") + "/" + MODULE_NAME + "/" + OUTPUT_DIR)
        .outputDir(System.getProperty("user.dir") + "/" + OUTPUT_DIR)
        .author("winfred")
        .dateType(DateType.TIME_PACK)
        .disableOpenDir()
        .commentDate("yyyy-MM-dd'T'HH:mm:ss")
        .build();
    generator.global(globalConfig);

    PackageConfig packageConfig = getPackageConfig();
    generator.packageInfo(packageConfig);

    final StrategyConfig strategyConfig = new StrategyConfig.Builder()
        .enableSkipView()
        .addTablePrefix("sys_")
        .addExclude("flyway_schema_history")
        .enableSchema()
        .build();

    strategyConfig
        .entityBuilder()
        .enableLombok()
        .formatFileName("%sEntity")
        .enableTableFieldAnnotation()
        .enableChainModel()
        .superClass(BasePageRequest.class)
        .enableColumnConstant()
        .fileOverride();

    strategyConfig
        .mapperBuilder()
        .enableBaseResultMap()
        .enableMapperAnnotation()
        .enableBaseColumnList()
        .fileOverride();

    strategyConfig
        .controllerBuilder()
        .enableRestStyle()
        .fileOverride();

    generator.strategy(strategyConfig);

    final TemplateConfig templateConfig = new TemplateConfig.Builder()
        .build();
    generator.template(templateConfig);
    generator.execute(new FreemarkerTemplateEngine());

  }

  public static class DatasourceConfigBuilder {

    private String jdbcUrl;
    private String userName;
    private String userPassword;
    private String jdbcDriverName;
    private String schemaName;

    public DataSourceConfig build() {
      DataSourceConfig.Builder builder = new DataSourceConfig.Builder(this.jdbcUrl, this.userName, this.userPassword);
      builder.schema(this.schemaName);
      builder.dbQuery(new MySqlQuery());
      DataSourceConfig dataSourceConfig = builder.build();
      return dataSourceConfig;
    }

    public DatasourceConfigBuilder setJdbcUrl(String jdbcUrl) {
      this.jdbcUrl = jdbcUrl;
      return this;
    }

    public DatasourceConfigBuilder setUserName(String userName) {
      this.userName = userName;
      return this;
    }

    public DatasourceConfigBuilder setUserPassword(String userPassword) {
      this.userPassword = userPassword;
      return this;
    }

    public DatasourceConfigBuilder setJdbcDriverName(String jdbcDriverName) {
      this.jdbcDriverName = jdbcDriverName;
      return this;
    }

    public DatasourceConfigBuilder setSchemaName(String schemaName) {
      this.schemaName = schemaName;
      return this;
    }
  }

  /**
   * @return {@link DataSourceConfig}
   */
  private static DataSourceConfig getDataSourceConfig() {
    return new DatasourceConfigBuilder()
        .setJdbcUrl(Constant.DB_JDBC_URL)
        .setUserName(Constant.DB_USER_NAME)
        .setUserPassword(Constant.DB_USER_PASSWORD)
        .setJdbcDriverName(Constant.DB_DRIVER_NAME)
        .setSchemaName(Constant.DB_SCHEMA_NAME)
        .build();
  }

  /**
   * package config
   *
   * @return {@link PackageConfig}
   */
  private static PackageConfig getPackageConfig() {
    final PackageConfig.Builder builder = new PackageConfig.Builder();
    return builder
        .parent(Constant.PACKAGE_PATH)
        .moduleName(Constant.SUB_PACKAGE_PATH)
        .mapper(Constant.DEFAULT_PACKAGE_NAME_MAPPER)
        .xml(Constant.DEFAULT_PACKAGE_NAME_XML)
        .service(Constant.DEFAULT_PACKAGE_NAME_SERVICE)
        .serviceImpl(Constant.DEFAULT_PACKAGE_NAME_SERVICE_IMPL)
        .controller(Constant.DEFAULT_PACKAGE_NAME_CONTROLLER)
        .entity(Constant.DEFAULT_PACKAGE_NAME_ENTITY)
        .build();
  }
}
