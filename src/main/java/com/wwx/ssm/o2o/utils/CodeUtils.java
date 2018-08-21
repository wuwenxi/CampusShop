package com.wwx.ssm.o2o.utils;

import com.google.code.kaptcha.Constants;

import javax.servlet.http.HttpServletRequest;

public class CodeUtils {

    /**
     *   校验验证码
     *
     * @param request
     * @return
     */
    public static boolean checkVerifyCode(HttpServletRequest request){
        //真实的验证码
        String verifyCodeExpect = (String) request.getSession().getAttribute(
                Constants.KAPTCHA_SESSION_KEY
        );
        //输入的验证码
        String verifyCodeActual = HttpServletRequestUtils.getString(request,"verifyCode");

        if (verifyCodeActual==null){
            return false;
        }
        return verifyCodeExpect .equalsIgnoreCase(verifyCodeActual);
    }
}
