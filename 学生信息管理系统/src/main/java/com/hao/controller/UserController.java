package com.hao.controller;

import com.hao.entity.User;
import com.hao.page.Page;
import com.hao.service.UserService;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/user")
@Controller

public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public ModelAndView userList(ModelAndView model) {

        model.setViewName("user/userList");
        return model;


    }


    /**
     * 获取所有的用户信息
     */
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getList(
            @RequestParam(value = "username", defaultValue = "", required = false) String username,
            Page page) {
        Map<String, Object> ret = new HashMap<String, Object>();
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("username", "%" + username + "%");
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        //获取所有的数据
        ret.put("rows", userService.getList(queryMap));
        //获取总的条数
        ret.put("total", userService.getTotal(queryMap));
        return ret;

    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> delete(
            @RequestParam(value = "ids[]", required = true) Integer[] ids) {
        HashMap<String, String> map = new HashMap<String, String>();
        if (ids == null) {
            map.put("type", "error");
            map.put("msg", "请选择要删除的数据");
            return map;
        }
        if (userService.delete(ids) <= 0) {
            map.put("type", "error");
            map.put("msg", "删除数据错误");
            return map;
        }
        map.put("type", "success");
        map.put("msg", "删除数据成功");

        return map;
    }


    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> edit(User user) {
        HashMap<String, String> map = new HashMap<>();
        if (user == null) {
            map.put("type", "error");
            map.put("msg", "数据绑定错误，请练习开发者");
            return map;
        }

        if (StringUtil.isEmpty(user.getUsername())) {
            map.put("type", "error");
            map.put("msg", "用户信息不能为空");
            return map;
        }
        if (StringUtil.isEmpty(user.getPassword())) {
            map.put("type", "error");
            map.put("msg", "用户密码不能为空");
            return map;
        }

        User userexist = userService.findUserUsername(user.getUsername());
        if (userexist != null) {
            if (userexist.getId() != user.getId()) {
                map.put("type", "error");
                map.put("msg", "该用户已经存在");
                return map;
            }
        }

        if (userService.edit(user) <= 0) {
            map.put("type", "error");
            map.put("msg", "修改失败");
            return map;
        }

        map.put("type", "success");
        map.put("msg", "修改成功");
        return map;


    }


    /**
     * 添加一个用户信息
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(User user) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        if (user == null) {
            map.put("type", "error");
            map.put("msg", "用户数据绑定错误");
            return map;
        }
        if (StringUtil.isEmpty(user.getUsername())) {
            map.put("type", "error");
            map.put("msg", "用户名不能为空");
            return map;
        }
        if (StringUtil.isEmpty(user.getPassword())) {
            map.put("type", "error");
            map.put("msg", "密码不能为空");
            return map;
        }
        System.out.println(user.getUsername());
        User userexist = userService.findUserUsername(user.getUsername());
        if (userexist != null) {
            map.put("type", "error");
            map.put("msg", "该用户已经存在");
            return map;
        }

        if (userService.add(user) <= 0) {
            map.put("type", "error");
            map.put("msg", "添加用户失败");
            return map;
        }

        //changed
        map.put("type", "success");
        map.put("msg", "添加用户成功");

        return map;
    }

}
