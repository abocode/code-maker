package ${bussiPackage}.${entityPackage}.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ${bussiPackage}.${entityPackage}.service.${entityName}Service;
import com.abocode.jfaster.core.common.service.impl.CommonServiceImpl;

@Service("${entityName?uncap_first}Service")
@Transactional
public class ${entityName}ServiceImpl extends CommonServiceImpl implements ${entityName}Service{
	
}