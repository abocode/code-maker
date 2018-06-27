package ${bussiPackage}.${entityPackage}.domain.repository.persistence.hibernate;
import ${bussiPackage}.${entityPackage}.domain.repository.${entityName}Repository;
import com.abocode.jfaster.core.domain.repository.persistence.hibernate.CommonRepositoryImpl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository("${entityName?uncap_first}Repository")
@Transactional
public class ${entityName}RepositoryImpl extends CommonRepositoryImpl implements ${entityName}Repository {
}