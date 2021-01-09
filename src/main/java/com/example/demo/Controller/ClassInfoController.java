package com.example.demo.Controller;

import com.example.demo.Entity.ClassInfo;
import com.example.demo.Service.ClassInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("cls")
public class ClassInfoController {

    @Autowired
    private ClassInfoService classInfoService;

    // 班级管理系统
    @GetMapping("list")
    public String getAllClasses(Model model){

        List<ClassInfo> classes = classInfoService.findAllClassInfo();
        model.addAttribute("cls", classes);
        return "classsystem";
    }

    // 添加班级信息页面
    @GetMapping("add")
    public String addStudentInfo(Model model){
        List<ClassInfo> classes = classInfoService.findAllClassInfo();
        model.addAttribute("cls", classes);
        return "addclassinfo"; // addclassinfo.html
    }

    // 添加班级信息提交
    @PostMapping("add")
    public String addStudentInfo(ClassInfo classInfo){
        // 保存到数据库里
        classInfoService.addClassInfo(classInfo);
        return "redirect:/cls/list";
    }

    // 修改班级信息页面
    @GetMapping("update/{id}")
    public String updateStudent(@PathVariable("id") Integer clsid, Model model){
        ClassInfo classInfo = classInfoService.findClassByID(clsid);
        model.addAttribute("cls", classInfo);
        return "updateclassinfo";
    }

    // 修改班级信息提交
    @PostMapping("update")
    public String updateStudent(ClassInfo classInfo){
        // 保存在数据库里
        classInfoService.updateClassByID(classInfo);
        return "redirect:/cls/list";
    }

    // 删除学生信息
    @GetMapping("list/delete/{id}")
    public String deleteStudent(@PathVariable("id") Integer clsid, Model model){
        classInfoService.deleteClassByID(clsid);
        List<ClassInfo> clsLists = classInfoService.findAllClassInfo();
        model.addAttribute("cls",clsLists);
        return "redirect:/cls/list";
    }
}
