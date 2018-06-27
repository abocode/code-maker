# code-maker 说明

 code-maker是为JFaster提供的完善的代码生成器

## 功能说明

    1.所有代码均开源，开源协议详见LICENSE
    2.使用方法，运行：com.abocode.codemaker.Run即可
    3.项目配置文件修改：contextConfig.properties
    4.数据库连接修改：database.properties
    5.模版修改：maker-config/template/*
    
## 如果添加新模版
 
#### 1.新加枚举类型：CodeType：
 
         public enum CodeType {
         controller("Controller"),
     
         service("Service"),
         serviceImpl("ServiceImpl"),
     
         entity("Entity"),
         repository("Repository"),
         repositoryImpl("RepositoryImpl"),
         newType("NewType");
         }   
 #### 2.添加枚举类型对应的代码生成路径：JFasterCodeFactory getCodePath()
   
        if("Controller".equalsIgnoreCase(codeType)) {
                        str.append(StringUtils.lowerCase("interfaces/web"));
        } else if("ServiceImpl".equalsIgnoreCase(codeType)) {
            str.append(StringUtils.lowerCase("application/service"));
        } else if("Service".equalsIgnoreCase(codeType)) {
            str.append(StringUtils.lowerCase("application"));
        } else if("RepositoryImpl".equalsIgnoreCase(codeType)) {
            str.append(StringUtils.lowerCase("domain/repository/persistence/hibernate"));
        } else if("Repository".equalsIgnoreCase(codeType)) {
            str.append(StringUtils.lowerCase("domain/repository"));
        } 
        
        
#### 3.添加代码生成器中使用模版：JFasterCodeGenerate.java  generateToFile()
    
      codeFactory.invoke("repositoryImplTemplate.ftl", "repositoryImpl");
      codeFactory.invoke("repositoryTemplate.ftl", "repository");
    
## JFaster使用说明
 
     v2.0 版本以下请使用 code-maker-v1.0
     v2.1+ 版本请使用版本 code-maker-v1.1

## 共享开源：

   欢迎各位开发者提出建议、push代码,如有疑问联系，请使用如下联系方式。邮箱:guanxf@aliyun.com,昵称:糊涂,交流qq群:140586555。