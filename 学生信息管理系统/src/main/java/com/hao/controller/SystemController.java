package com.hao.controller;

import com.hao.entity.User;
import com.hao.service.UserService;
import com.hao.util.CpachaUtil;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/system")
@Controller
public class SystemController {

    @Autowired
    private UserService userService;

    /**F
     * 测试环境是否搭建成功
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(ModelAndView model) {
        model.setViewName("helloWorld");
        model.addObject("user", "获取一个页面元素内容");
        return model;
    }

    /**
     * 测试跳转到管理员界面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/index1", method = RequestMethod.GET)
    public ModelAndView index1(ModelAndView model) {
        model.setViewName("system/index");
        return model;
    }

    /**
     * 访问登录页面
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "system/login";
    }

    /**
     * Response 注解表示响应是以json串的方式响应
     *
     * @return
     */
    @RequestMapping(value = "/loginRequest", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> loginRequest(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password,
            @RequestParam(value = "vcode", required = true) String vcode,
            @RequestParam(value = "type", required = true) Integer type,
            HttpServletRequest request

    ) {
        Map<String, String> map = new HashMap<String, String>();
        if (StringUtil.isEmpty(username)) {
            map.put("type", "error");
            map.put("msg", "用户名不能为空");
            return map;
        }

        if (StringUtil.isEmpty(password)) {
            map.put("type", "error");
            map.put("msg", "密码不能为空");
            return map;
        }
        if (StringUtil.isEmpty(vcode)) {
            map.put("type", "error");
            map.put("msg", "验证码不能为空");
            return map;
        }
        HttpSession session = request.getSession();
        String cpatha_code = (String) session.getAttribute("cpatha_code");

        if (StringUtil.isEmpty("cpacpatha_code")) {
            map.put("type", "error");
            map.put("msg", "长时间没有操作，会话失效，请刷新以后在登录");
            return map;
        }
//      cahanged
        //cpatha_code 界面验证码还是上次登录的验证码，就为空
//            boolean t = !cpatha_code.toUpperCase().equals(vcode.toUpperCase());
            boolean t = cpatha_code==null||!cpatha_code.toUpperCase().equals(vcode.toUpperCase());
        if (t) {
//            map.put("type", "error");
            map.put("type", "success");
            map.put("msg", "验证码错误");
            return map;
        }

        //清空session中的验证码
        session.setAttribute("cpatha_code", null);

        User user = userService.findUserUsername(username);
        if (type == 1) {
            if (user == null) {
                map.put("type", "error");
                map.put("msg", "用户名不存在");
                return map;
            }

            if (!user.getPassword().equals(password)) {
                map.put("type", "error");
                map.put("msg", "密码错误");
                return map;
            }
            //将对象放在session
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("userType", type);
            map.put("type", "success");
            map.put("msg", "登录成功");


            return map;
        }

        return map;

    }


    /**
     * 访问验证码
     *
     * @return 获取页面验证码参数的内容跟
     * v1 表示验证码的个数
     * w  表示画布的宽度
     * h  表示画布的高度
     * required = false 表示是否为必须的参数  false 表示可以不需要参数  true表示必须传递参数
     */
    @RequestMapping(value = "/cpacha", method = RequestMethod.GET)
    public void cpacha(HttpServletRequest request,
                       @RequestParam(value = "v1", defaultValue = "4", required = false) Integer v1,
                       @RequestParam(value = "w", defaultValue = "98", required = false) Integer w,
                       @RequestParam(value = "h", defaultValue = "33", required = false) Integer h,
                       HttpServletResponse response) {
        //创建一个验证吗的对象
        CpachaUtil cpachaUtil = new CpachaUtil(v1, w, h);
        //获取验证码的字母
        String code = cpachaUtil.generatorVCode();

        //将验证码的内容存放在在session对象中
        HttpSession session = request.getSession();
        session.setAttribute("cpatha_code", code);

        //获取验证码的image
        BufferedImage image = cpachaUtil.generatorRotateVCodeImage(code, true);

        try {
            ImageIO.write(image, "gif", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}

