package ${package.Mapper};

import ${package.Entity}.${entity};
import ${superMapperClassPackage};
import java.util.List;

<#if mapperAnnotation>
    import org.apache.ibatis.annotations.Mapper;
    import org.apache.ibatis.annotations.Param;
</#if>

/**
* <p>
    * ${table.comment!} Mapper 接口
    * </p>
*
* @author ${author}
* @since ${date}
*/
<#if mapperAnnotation>
    @Mapper
</#if>
<#if kotlin>
    interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {

    <T extends BasePageRequest> List<${entity}> queryList(T request);

    <T extends BasePageRequest> Integer queryListCount(T request);

    Integer addOne(${entity} entity);

    Integer bulkUpsert(@Param(value = "items") List<#noparse><? extends</#noparse> ${entity}<#noparse>></#noparse> list);

    List<${entity}> queryByIds(@Param(value = "items") List<?> list);

    Integer updateIfNotNullById(${entity} entity);
}
</#if>