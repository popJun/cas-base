package server.sso.cas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import server.sso.cas.utils.CaptchaUtil;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

/**
 * 用于验证码操作
 */
@Controller
public class CaptchaController {
    public static final String KEY_CAPTCHA = "captcha";

    @RequestMapping("/captcha.jpg")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 设置相应类型,告诉浏览器输出的内容为图片
            response.setContentType("image/png;charset=UTF-8");
            //不缓存此内容
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            HttpSession session = request.getSession();
            StringBuffer sb = new StringBuffer();
            CaptchaUtil captchaUtil = new CaptchaUtil();
            BufferedImage image = captchaUtil.genRandomCodeImage(sb);
            session.setAttribute("captcha",sb.toString());
            //将内存中的图片通过流动形式输出到客户端
            ImageIO.write(image, "jpeg", response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
