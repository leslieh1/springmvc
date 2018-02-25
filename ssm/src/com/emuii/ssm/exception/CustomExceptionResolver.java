package com.emuii.ssm.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Create by Leslie on 2018\1\10 0010.<br>
 */
public class CustomExceptionResolver implements HandlerExceptionResolver{

    // 前端控制器DispatcherServlet在进HandleMapping，调用HandlerAdapter执行Handler过程中，如果遇到异常就会执行此方法
    // handler最终要执行的Handler，它的真实身份是HandlerMethod
    // Exception ex就是接收到异常信息
    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object handle, Exception ex) {
        // 输出异常
        ex.printStackTrace();

        // 统一异常处理代码
        // 针对系统自定义的CustomException异常，就可以直接从异常类中获取异常信息，将异常处理在错误页面展示
        // 异常信息
        String message = null;
        CustomException customException = null;
        if(ex instanceof CustomException){
            customException = (CustomException) ex;
        }else {
            // 针对非CustomException异常，对这类构造新的自定义异常
            customException = new CustomException("未知错误");
        }

        // 错误信息
        message = customException.getMessage();

        request.setAttribute("message", message);

        request.getRequestDispatcher("WEB-INF/jsp/error.jsp");

        return new ModelAndView();
    }
}
