package com.gyy.controller;

import com.gyy.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Date;
import java.util.Map;

/**
 * 来测试常用的注解
 */
@Controller
@RequestMapping("/anno")
@SessionAttributes(value = {"msg"})   //将信息存入到session域中
public class AnnoationController {

    /**
     * @RequestParam注解 用于为参数赋值
     */
    @RequestMapping("/testRequestParam")
    public String testRequestParam(@RequestParam(name = "name") String username){
        System.out.println(username);
        return "success";
    }

    /**
     * @RequestBody注解 用于获取请求体
     * @param body
     * @return
     */
    @RequestMapping("/testRequestBody")
    public String testRequestBody(@RequestBody String body){
        System.out.println(body);
        return "success";
    }

    /**
     * @PathVariable注解 用于获取占位符的值
     * @param id
     * @return
     */
    @RequestMapping(path = "/testPathVariable/{sid}",method = RequestMethod.POST)
    public String testPathVariable(@PathVariable(name = "sid") String id){
        System.out.println(id);
        return "success";
    }

    /**
     * @RequestHeader：用于获取请求头的
     * @return
     */
    @RequestMapping(path = "/testRequestHeader")
    public String testRequestHeader(@RequestHeader(value = "Accept") String header){
        System.out.println(header);
        return "success";
    }

    /**
     * @CookieValue注解：用于获取指定cookie的值
     * @return
     */
    @RequestMapping(path = "/testCookieValue")
    public String testCookieValue(@CookieValue(value = "JSESSIONID") String jsessionId){
        System.out.println(jsessionId);
        return "success";
    }

    /**
     * @ModelAttribute注解：用于让方法在访问先开始执行
     *    有返回值的和无返回值的两种情况
     * @return
     */
    /*@ModelAttribute
    public User testModelAttribute(String uname){
        User user = new User();
        user.setUname("阿狗");
        user.setAge(23);
        user.setBirthday(new Date());
        System.out.println("先执行了");
        return user;
    }*/

    @ModelAttribute
    public void testModelAttribute(String uname, Map<String,User> map){
        User user = new User();
        user.setUname("阿狗");
        user.setAge(23);
        user.setBirthday(new Date());
        map.put("user",user);
        System.out.println("先执行了");
    }

    /**
     * 无返回值的ModeLAttribute注解用法，需要用Map集合的帮助
     * @param user
     * @return
     */
    @RequestMapping(path = "/testModelAttribute")
    public String testModelAttribute(@ModelAttribute("user") User user){
        System.out.println(user);
        return "success";
    }

    /**
     * spring提供的Model接口可以将数据以键值对的形式存入request域中去
     * @param model
     * @return
     */
    @RequestMapping(path = "/testSessionAttribute")
    public String testSessionAttribute(Model model){   //model底层将数据存入request域中
        System.out.println("testSessionAttribute方法执行了");
        model.addAttribute("msg","阿狗");
        return "success";
    }

    /**
     * 我们可以利用ModelMap即Model的实现类来取存入的信息
     * @param modelMap
     * @return
     */
    @RequestMapping(path = "/getDataFromRequest")
    public String testSessionAttribute(ModelMap modelMap){   //model底层将数据存入request域中
        System.out.println("getDataFromRequest方法执行了");
        String msg = (String) modelMap.get("msg");
        System.out.println(msg);
        return "success";
    }


    /**
     * 利用SessionStatus可以删除session
     * @param sessionStatus
     * @return
     */
    @RequestMapping(path = "/delSessionData")
    public String testSessionAttribute(SessionStatus sessionStatus){   //model底层将数据存入request域中
        System.out.println("delSessionData方法执行了");
        sessionStatus.setComplete();  //完成任务了表示即这个session要被删除
        return "success";
    }
}
