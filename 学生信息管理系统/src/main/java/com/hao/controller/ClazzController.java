package com.hao.controller;

import com.hao.entity.Clazz;
import com.hao.page.Page;
import com.hao.service.ClazzService;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
//changed
@RequestMapping("/clazz")
@Controller
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    /**
     * 跳转到班级列表页面
     */
    @RequestMapping(value = "/list")
    public ModelAndView getClazzList(ModelAndView model) {
        model.setViewName("clazz/claszzList");
        List<Clazz> clazzes = clazzService.findQueryAll();

        //unchanged
        model.addObject("clazzList", clazzes);
        clazzes.forEach(c-> System.out.println(c.toString()));
        return model;
    }



    /**
     * 查询班级信息
     */
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getList(
            @RequestParam(value = "name", defaultValue = "", required = false) String name, Page page) {
        HashMap<String, Object> map = new HashMap<>();
        HashMap<String, Object> ret = new HashMap<>();
        //获取模糊查询的name
        map.put("name", "%" + name + "%");
        //获取起始条数
        map.put("offset", page.getOffset());
        //获取每页多少条
        map.put("pageSize", page.getRows());
        //获取所有的年级信息
        ret.put("rows", clazzService.getList(map));
        //获取总共多少条数据
        ret.put("total", clazzService.getTotal(map));
        return ret;
    }
    /*
    * 添加班级 changed
    * */

    @RequestMapping("/add")
    @ResponseBody
    public Map<String,String> add(Clazz clazz){
        HashMap<String, String> map = new HashMap<>();

        if (clazz == null) {
            map.put("type", "error");
            map.put("msg", "数据绑定错误，请联系开发人员");
            return map;
        }

        if (StringUtil.isEmpty(clazz.getName())) {
            map.put("type", "error");
            map.put("msg", "班级信息不能为空");
            return map;
        }

        if (clazzService.add(clazz) <= 0) {
            map.put("type", "error");
            map.put("msg", "添加班级失败");
            return map;
        }

        //changed
        map.put("type", "success");
        map.put("msg", "添加成功");
        return map;
    }
}
