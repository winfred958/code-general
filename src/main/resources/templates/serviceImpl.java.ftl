package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;

/**
* <p>
    * ${table.comment!} 服务实现类
    * </p>
*
* @author ${author}
* @since ${date}
*/
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

@Override
public
<T extends BasePageRequest> List<${entity}> queryList(T request) {
    return getBaseMapper().queryList(request);
    }

    @Override
    public
    <T extends BasePageRequest> Integer queryListCount(T request) {
        return getBaseMapper().queryListCount(request);
        }

        @Override
        public Integer addOne(${entity} entity) {
        return getBaseMapper().addOne(entity);
        }

        @Override
        public Integer bulkUpsert(List<? extends ${entity}> list) {
        return getBaseMapper().bulkUpsert(list);
        }

        @Override
        public List<${entity}> queryByIds(List<?> list) {
        return getBaseMapper().queryByIds(list);
        }

        @Override
        public Integer updateIfNotNullById(${entity} entity) {
        return getBaseMapper().updateIfNotNullById(entity);
        }

}

        </#if>
