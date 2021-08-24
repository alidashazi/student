package com.hao.controller;

import com.hao.entity.Grade;
import com.hao.page.Page;
import com.hao.service.GradeService;
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

@RequestMapping("/grade")
@Controller
public class GradeController {

    @Autowired
    private GradeService gradeService;


    /**
     * 跳转到grade页面
     */
    @RequestMapping(value = "/list")
    public ModelAndView gradeList(ModelAndView model) {
        model.setViewName("grade/gradeList");
        return model;
    }

    /**
     * 查询年级信息
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
        ret.put("rows", gradeService.getList(map));
        //获取总共多少条数据
        ret.put("total", gradeService.getTotal(map));
        return ret;
    }


    /**
     * 修改年级信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> edit(Grade grade) {
        HashMap<String, String> map = new HashMap<>();
        if (grade == null) {
            map.put("type", "error");
            map.put("msg", "数据绑定错误，请联系程序员");
            return map;
        }
        if (StringUtil.isEmpty(grade.getName())) {
            map.put("type", "error");
            map.put("msg", "年级名称不能为空");
            return map;
        }
        if (gradeService.edit(grade) <= 0) {
            map.put("type", "error");
            map.put("msg", "修改年级信息失败");
            return map;
        }
        map.put("type", "success");
        map.put("msg", "修改信息成功");
        return map;

    }


    /**
     * 删除年级信息
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> delete(
            @RequestParam(value = "ids[]", required = true) Integer[] ids
    ) {
        HashMap<String, String> map = new HashMap<>();
        if (ids == null || ids.length == 0) {
            map.put("type", "error");
            map.put("msg", "请选择要删除的年级");
            return map;
        }

        if (gradeService.delete(ids) <= 0) {
            map.put("type", "error");
            map.put("msg", "删除年级信息失败");
            return map;
        }

        map.put("type", "success");
        map.put("msg", "删除年级信息成功");


        return map;
    }


    /**
     * 添加一个年级信息
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> add(Grade grade) {
        HashMap<String, String> map = new HashMap<>();

        if (grade == null) {
            map.put("type", "error");
            map.put("msg", "数据绑定错误，请联系开发人员");
            return map;
        }

        if (StringUtil.isEmpty(grade.getName())) {
            map.put("type", "error");
            map.put("msg", "年级信息不能为空");
            return map;
        }

        if (gradeService.add(grade) <= 0) {
            map.put("type", "error");
            map.put("msg", "添加年级失败");
            return map;
        }

        //changed
        map.put("type", "success");
        map.put("msg", "添加成功");
        return map;


    }


}
