<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.Mapper}.${table.mapperName}">

    <#if enableCache>
        <!-- 开启二级缓存 -->
        <cache type="${cacheClassName}"/>

    </#if>
    <#if baseResultMap>
        <!-- 通用查询映射结果 -->
        <resultMap id="BaseResultMap" type="${package.Entity}.${entity}">
            <#list table.fields as field>
                <#if field.keyFlag><#--生成主键排在第一位-->
                    <id column="${field.name}" property="${field.propertyName}"/>
                </#if>
            </#list>
            <#list table.commonFields as field><#--生成公共字段 -->
                <result column="${field.name}" property="${field.propertyName}"/>
            </#list>
            <#list table.fields as field>
                <#if !field.keyFlag><#--生成普通字段 -->
                    <result column="${field.name}" property="${field.propertyName}"/>
                </#if>
            </#list>
        </resultMap>

    </#if>
    <#if baseColumnList>
        <!-- 通用查询结果列 -->
        <sql id="Base_Column_List">
            <#list table.fields as field>
                `${field.columnName}`<#sep>, </#sep>
            </#list>
        </sql>

    </#if>
    <select id="queryList" resultMap="BaseResultMap" parameterType="${package.Entity}.${entity}">
        SELECT
        <#list table.fields as field>
            `${field.columnName}`<#sep>, </#sep>
        </#list>
        FROM `${table.name}`
        <where>
            <#list table.fields as field>
                <if test="${field.propertyName} != null and ${field.propertyName} != ''">
                    AND `${field.columnName}` = <#noparse>#{</#noparse>${field.propertyName}<#noparse>}</#noparse>
                </if>
            </#list>
        </where>
        <if test="orders != null">
            ORDER BY
            <foreach collection="orders" item="order" open="" separator="," close="">
                <#noparse>${order.column} ${order.rule}</#noparse>
            </foreach>
        </if>
        <if test="offset != null and size != null">
            LIMIT <#noparse>${offset}, ${size}</#noparse>
        </if>
    </select>

    <select id="queryListCount" resultType="java.lang.Integer" parameterType="${package.Entity}.${entity}">
        SELECT
        COUNT(1)
        FROM `${table.name}`
        <where>
            <#list table.fields as field>
                <if test="${field.propertyName} != null and ${field.propertyName} != ''">
                    AND `${field.columnName}` = <#noparse>#{</#noparse>${field.propertyName}<#noparse>}</#noparse>
                </if>
            </#list>
        </where>
    </select>

    <insert id="addOne" parameterType="${package.Entity}.${entity}">
        INSERT INTO `${table.name}`
        (
        <#list table.fields as field>
            `${field.columnName}`<#sep>, </#sep>
        </#list>
        ) VALUES (
        <#list table.fields as field>
            <#noparse>#{</#noparse>${field.propertyName}<#noparse>}</#noparse><#sep>, </#sep>
        </#list>
        )
    </insert>

    <insert id="bulkUpsert" parameterType="java.util.List">
        REPLACE INTO `${table.name}`
        (
        <#list table.fields as field>
            `${field.columnName}`<#sep>, </#sep>
        </#list>
        )
        VALUES
        <foreach collection="items" index="index" item="item" separator=",">
            (
            <#list table.fields as field>
                <#noparse>#{item.</#noparse>${field.propertyName}<#noparse>}</#noparse><#sep>, </#sep>
            </#list>
            )
        </foreach>
    </insert>

    <select id="queryByIds" resultMap="BaseResultMap" parameterType="java.util.List">
        SELECT
        <#list table.fields as field>
            `${field.columnName}`<#sep>, </#sep>
        </#list>
        FROM `${table.name}`
        <where>
            <#list table.fields as field>
                <#if field.keyFlag>
                    ${field.name} IN
                </#if>
            </#list>
            <foreach collection="items" item="item" open="(" separator="," close=")"><#noparse>#{item}</#noparse>
            </foreach>
        </where>
    </select>

    <update id="updateIfNotNullById" parameterType="${package.Entity}.${entity}">
        UPDATE `${table.name}`
        <set>
            <#list table.fields as field>
                <#if !field.keyFlag><#--生成普通字段 -->
                    <if test="${field.propertyName} != null">
                        `${field.columnName}` = <#noparse>#{</#noparse>${field.propertyName}<#noparse>}</#noparse><#sep>, </#sep>
                    </if>
                </#if>
            </#list>
        </set>
        <where>
            <#list table.fields as field>
                <#if field.keyFlag><#--生成主键排在第一位-->
                    `${field.columnName}` = <#noparse>#{</#noparse>${field.propertyName}<#noparse>}</#noparse>
                </#if>
            </#list>
        </where>
    </update>
</mapper>
