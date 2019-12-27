package web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;

@WebFilter("/*")
public class WordsFilter implements Filter {
    private ArrayList<String> list = new ArrayList<String>();
    @Override
    public void init(FilterConfig filterConfig) {
        //用来加载敏感词汇文件
        //1.加载
        try {
            InputStream is = WordsFilter.class.getClassLoader().getResourceAsStream("words.txt");
            //字节输入流转换为字符输入流
            Reader reader = new InputStreamReader(is, "UTF-8");
            //字符输入流转换为缓冲区字符输入流（因为readLine()方法比较方便，所以使用BufferReader）
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = null;
            while((line = bufferedReader.readLine())!=null){
                list.add(line);
            }

            //测试一下，list集合中有没有将敏感词汇加进去
            System.out.println(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //1.创建代理对象，增强方法
        //三个参数，第一个真实对象的类加载器，第二个为真实对象实现的接口，第三个为new个啥
        ServletRequest proxy_req = (ServletRequest) Proxy.newProxyInstance(servletRequest.getClass().getClassLoader(), servletRequest.getClass().getInterfaces(),new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if("getParameter".equals(method.getName())){
                    //说明是调用的获取输出信息的方法，需要进行过滤
                    String value = (String)method.invoke(servletRequest, args);
                    for (String s:list) {
                        if(value.contains(s)){
                            value = value.replaceAll(s,"***");
                        }
                    }
                    return value;
                }
                return method.invoke(servletRequest,args);
            }
        });

        //2.放行
        filterChain.doFilter(proxy_req,servletResponse);
    }


    @Override
    public void destroy() {

    }
}
