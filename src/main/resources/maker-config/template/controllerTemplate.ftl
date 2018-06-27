package ${bussiPackage}.${entityPackage}.interfaces.web;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.abocode.jfaster.core.common.model.json.AjaxJson;
import com.abocode.jfaster.core.common.model.json.DataGrid;
import com.abocode.jfaster.core.common.model.json.DataGridReturn;
import com.abocode.jfaster.core.common.util.StringUtils;
import com.abocode.jfaster.core.persistence.hibernate.qbc.CriteriaQuery;
import com.abocode.jfaster.core.platform.view.widgets.easyui.TagUtil;
import com.abocode.jfaster.web.common.hqlsearch.HqlGenerateUtil;
import com.abocode.jfaster.core.interfaces.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ${bussiPackage}.${entityPackage}.domain.entity.${entityName};
import ${bussiPackage}.${entityPackage}.application.${entityName}Service;

/**   
 * @Title: Controller
 * @Description: ${ftl_description}
 * @author abocode.com
 * @date ${ftl_create_time}
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/${entityName?uncap_first}Controller")
public class ${entityName}Controller extends BaseController {
	@Autowired
	private ${entityName}Service ${entityName?uncap_first}Service;

	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * ${ftl_description}列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "${entityName?uncap_first}")
	public ModelAndView ${entityName?uncap_first}(HttpServletRequest request) {
		return new ModelAndView("${entityPackage}/${entityName?uncap_first}List");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param ${entityName?uncap_first}
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(${entityName} ${entityName?uncap_first},HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(${entityName}.class, dataGrid);
		//查询条件组装器
		HqlGenerateUtil.installHql(cq, ${entityName?uncap_first}, request.getParameterMap());
        DataGridReturn results=this.${entityName?uncap_first}Service.findDataGridReturn(cq);
		TagUtil.datagrid(response, results);
	}

	/**
	 * 删除${ftl_description}
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(${entityName} ${entityName?uncap_first}, HttpServletRequest request) {
        message = "${ftl_description}删除成功";
		AjaxJson j = new AjaxJson(message);
        ${entityName?uncap_first}Service.del( ${entityName?uncap_first}.getId());
		return j;
	}


	/**
	 * 添加${ftl_description}
	 * 
	 * @param ${entityName?uncap_first}
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(${entityName} ${entityName?uncap_first}, HttpServletRequest request) {
		if (StringUtils.isNotEmpty( ${entityName?uncap_first}.getId())) {
		    message = "${ftl_description}更新成功";
            ${entityName?uncap_first}Service.update( ${entityName?uncap_first}.getId(), ${entityName?uncap_first});
		}else{
		    message = "${ftl_description}添加成功";
            ${entityName?uncap_first}Service.add( ${entityName?uncap_first});
		}
		AjaxJson j = new AjaxJson(message);
		j.setMsg(message);
		return j;
	}

	/**
	 * ${ftl_description}列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(${entityName} ${entityName?uncap_first}, HttpServletRequest req) {
		if (StringUtils.isNotEmpty(${entityName?uncap_first}.getId())) {
			${entityName?uncap_first} = ${entityName?uncap_first}Service.find(${entityName?uncap_first}.getId());
			req.setAttribute("${entityName?uncap_first}Page", ${entityName?uncap_first});
		}
		return new ModelAndView("${entityPackage}/${entityName?uncap_first}");
	}
}
