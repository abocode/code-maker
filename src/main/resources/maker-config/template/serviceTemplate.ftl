package ${bussiPackage}.${entityPackage}.application;
import com.abocode.jfaster.core.common.model.json.DataGridReturn;
import com.abocode.jfaster.core.persistence.hibernate.qbc.CriteriaQuery;
import ${bussiPackage}.${entityPackage}.domain.entity.${entityName};


public interface ${entityName}Service{

     void del(String id);

     void add(${entityName} ${entityName?uncap_first});

    void update(String id, ${entityName} ${entityName?uncap_first});

     DataGridReturn findDataGridReturn(CriteriaQuery cq );

     ${entityName} find(String id);
}
