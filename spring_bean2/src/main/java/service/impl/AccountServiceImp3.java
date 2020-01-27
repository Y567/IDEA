package service.impl;

import service.AccountService;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

public class AccountServiceImp3 implements AccountService {
    private String[] array;
    private Map<String,String> map;

    public void setArray(String[] array) {
        this.array = array;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public void saveAccount() {
        System.out.println("service层的方法调用了");
        System.out.println(Arrays.toString(array));
        System.out.println(map);
    }


}
