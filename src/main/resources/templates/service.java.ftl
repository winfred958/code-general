package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import java.util.List;

/**
* <p>
    * ${table.comment!} 服务类
    * </p>
*
* @author ${author}
* @since ${date}
*/
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

<T extends BasePageRequest> List<${entity}> queryList(T request);

    <T extends BasePageRequest> Integer queryListCount(T request);

        Integer addOne(${entity} entity);

        Integer bulkUpsert(List<#noparse><? extends</#noparse> ${entity}<#noparse>></#noparse> list);

        List<${entity}> queryByIds(List<?> list);

        Integer updateIfNotNullById(${entity} entity);
        }
        </#if>

