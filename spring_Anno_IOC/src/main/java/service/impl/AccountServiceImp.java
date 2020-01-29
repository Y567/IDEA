package service.impl;

import dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import service.AccountService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * 利用注解的方式实现
 * 1.将创建对象交给spring框架  @Component
 * 2.实现依赖注入
 * 3.改变对象的作用范围
 * 4.生命周期相关
 *
 * @Component衍生出来的三个注解功能一样，用于表现层，业务层，持久层
 * @Service
 * @Repository
 * @Controller
 *
 *
 *
 * @Autowired:
 *  作用：自动注入数据，当容器中由唯一一个对象类型和需要注入的数据类型一样，则注入成功，如果没有则报错
 *  如果由多个先按数据类型找出来在按变量名称找,找不到就出错
 *
 *
 * @Value:
 *  用于注入基本数据类型和String类型
 *  可以用spring的el表达式来写${}
 * <bean id="" class="" scop="" init-Method="" destroy-Method="">
 *     <property name="" value/ref=""></property>
 * </bean>
 */
//该注解：
//   作用：将该类的实例化对象存入容器中
//   有一个value属性用于指定容器对象里的key，如果默认不写就是类名但首字母是小写
@Component("accountServiceImp")
public class AccountServiceImp implements AccountService {

   /* @Autowired
    @Qualifier("accountDao1")*/
   //该注解在注入类成员变量时需要和Autowired配合使用，用于指定注入的数据 但是用于注入方法参数时不需要配合使用
    @Resource(name = "accountDao1")  //该注解直接相当于上面两个的功能，但是属性时name不是value
    private AccountDao accountDao;

    public AccountServiceImp(){
        System.out.println("对象创建了");
    }

    //解耦
    public void saveAccount() {
        accountDao.saveAccount();
    }

    @PreDestroy
    public void destroy() {
        System.out.println("销毁方法");
    }

    @PostConstruct
    public void init() {
        System.out.println("初始化方法");
    }
}
