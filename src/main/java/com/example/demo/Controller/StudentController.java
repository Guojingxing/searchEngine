package com.example.demo.Controller;

import com.example.demo.Entity.ClassInfo;
import com.example.demo.Entity.Student;
import com.example.demo.Service.ClassInfoService;
import com.example.demo.Service.StudentService;
import com.example.demo.Utils.FileUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("stu")
public class StudentController {
// http://localhost:8080/stu/add
    @Autowired
    private StudentService studentService;

    @Autowired
    private ClassInfoService classInfoService;

//    @GetMapping("list")
//    public String getAllStudent(Model model){
//        List<Student> stuLists = studentService.findAllStudent();
//        List<ClassInfo> classes = classInfoService.findAllClassInfo();
//        model.addAttribute("cls", classes);
//        model.addAttribute("stus",stuLists);
//        return "system"; // system.html
//    }

    // 学生管理系统
    @RequestMapping("list")
    public String getStudentByIDStuid(@RequestParam(value = "pageIndex",defaultValue = "1") Integer pageIndex,
                                      @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                      @RequestParam(value="clsid", defaultValue = "0") Integer clsid,
                                      @RequestParam(value="stu_name", defaultValue = "") String stu_name,
                                      Model model){
        List<Student> studentList = null;
        PageInfo<Student> studentPageInfo = null;

        if(clsid == 0 && stu_name.isEmpty()){
            studentPageInfo = studentService.findAllStudent(pageIndex, pageSize);
        }
        else if(clsid == 0 && !stu_name.isEmpty()){
            studentPageInfo = studentService.findStudentByStuName(pageIndex, pageSize, stu_name);
        }
        else{
            studentPageInfo = studentService.findStudentByClsIDStuName(pageIndex, pageSize, clsid, stu_name);
        }

        List<ClassInfo> classes = classInfoService.findAllClassInfo();

        model.addAttribute("cls", classes);
        model.addAttribute("stus", studentPageInfo);

        model.addAttribute("clsid", clsid);
        model.addAttribute("stu_name", stu_name);
        return "system";
    }

    // 添加学生信息页面
    @GetMapping("add")
    public String addStudentInfo(Model model){
        List<ClassInfo> classes = classInfoService.findAllClassInfo();
        model.addAttribute("cls", classes);
        return "addinfo"; // addinfo.html
    }

    // 添加学生信息提交
    @PostMapping("add")
    public String addStudentInfo(Student student, @RequestParam("filepic") MultipartFile file){
        // 1.保存文件到硬盘上
        String fileName = file.getOriginalFilename();
        String filePath = FileUtil.getUpLoadFilePath();
        fileName = System.currentTimeMillis()+fileName;

        try {
            FileUtil.uploadFile(file.getBytes(),filePath,fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 2.保存文件名到数据库里
        student.setStu_image_url(fileName);
        studentService.addStudentInfo(student);
        return "redirect:/stu/list";
    }

    // 修改学生信息页面
    @GetMapping("update/{id}")
    public String updateStudent(@PathVariable("id") Integer stuid, Model model){
        Student student = studentService.findStudentByID(stuid);
        List<ClassInfo> classes = classInfoService.findAllClassInfo();
        model.addAttribute("cls", classes);
        model.addAttribute("stu",student);
        return "updateinfo";
    }

    // 修改学生信息提交
    @PostMapping("update")
    public String updateStudent(Student student, @RequestParam("filepic") MultipartFile file){
        // 1.保存文件到硬盘上
        String fileName = file.getOriginalFilename();
        String filePath = FileUtil.getUpLoadFilePath();
        fileName = System.currentTimeMillis()+fileName;

        try {
            FileUtil.uploadFile(file.getBytes(),filePath,fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 2.保存文件名到数据库里
        student.setStu_image_url(fileName);
        studentService.updateStudentByID(student);
        return "redirect:/stu/list";
    }

    // 删除学生信息
    @GetMapping("list/delete/{id}")
    public String deleteStudent(@PathVariable("id") Integer stuid,
                                @RequestParam(value = "pageIndex",defaultValue = "1") Integer pageIndex,
                                @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                Model model){
        studentService.deleteStudentByID(stuid);
        PageInfo<Student> stuLists = studentService.findAllStudent(pageIndex, pageSize);
        model.addAttribute("stus",stuLists);
        return "redirect:/stu/list";
    }
}