package com.gyy.controller;

import com.gyy.domain.Account;
import com.gyy.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * web层
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("testFindAccountAll")
    public String testFindAccountAll(Model model){
        System.out.println("testFindAccountAll执行了");
        List<Account> list = accountService.findAccountAll();
        model.addAttribute("list",list);
        return "list";
    }

    @RequestMapping("testSaveAccount")
    public void testFindAccountAll(Account account, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("testSaveAccount执行了");
        accountService.saveAccount(account);
        //这里我们保存完毕后将页面跳转到list页面进行展示,需要用到request和response域对象
        response.sendRedirect(request.getContextPath()+"/account/testFindAccountAll");
        return;
    }
}
