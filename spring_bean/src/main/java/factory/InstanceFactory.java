package factory;

import service.AccountService;
import service.impl.AccountServiceImp;

public class InstanceFactory {
    /**
     * 模拟一个和jar包里的代码，这是不能改变的，所以在创建bean对象时要想办法
     * 模拟的时第二种方式没有默认构造方法
     */

  /*  public InstanceFactory(String name){
        System.out.println("啦啦啦");
    }
*/

    public AccountService getAccountService(){
        return new AccountServiceImp();
    }
}
