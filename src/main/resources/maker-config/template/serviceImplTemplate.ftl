package ${bussiPackage}.${entityPackage}.application.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.abocode.jfaster.core.common.model.json.DataGridReturn;
import com.abocode.jfaster.core.common.util.BeanPropertyUtils;
import com.abocode.jfaster.core.persistence.hibernate.qbc.CriteriaQuery;

import ${bussiPackage}.${entityPackage}.application.${entityName}Service;
import ${bussiPackage}.${entityPackage}.domain.entity.${entityName};
import ${bussiPackage}.${entityPackage}.domain.repository.${entityName}Repository;
import javax.annotation.Resource;
@Service("${entityName?uncap_first}Service")
@Transactional
public class ${entityName}ServiceImpl  implements ${entityName}Service{
    @Resource
    private ${entityName}Repository ${entityName?uncap_first}Repository;
    @Override
    public void del(String id) {
        ${entityName} ${entityName?uncap_first} = ${entityName?uncap_first}Repository.findEntity(${entityName}.class, id);
        ${entityName?uncap_first}Repository.delete(${entityName?uncap_first});
    }

    @Override
    public void add(${entityName} ${entityName?uncap_first}) {
        ${entityName?uncap_first}Repository.save(${entityName?uncap_first});
    }

    @Override
    public void update(String id, ${entityName} ${entityName?uncap_first}) {
        ${entityName} t = ${entityName?uncap_first}Repository.find(${entityName}.class, ${entityName?uncap_first}.getId());
        BeanPropertyUtils.copyBeanNotNull2Bean(${entityName?uncap_first}, t);
        ${entityName?uncap_first}Repository.saveOrUpdate(t);
    }


    @Override
    public DataGridReturn findDataGridReturn(CriteriaQuery cq) {
        return ${entityName?uncap_first}Repository.findDataGridReturn(cq,true);
    }

    @Override
    public ${entityName?cap_first} find(String id) {
        return ${entityName?uncap_first}Repository.find(${entityName}.class,id);
    }
}