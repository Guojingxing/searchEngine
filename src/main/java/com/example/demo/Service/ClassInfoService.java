package com.example.demo.Service;


import com.example.demo.Entity.ClassInfo;
import com.example.demo.Entity.Student;

import java.util.List;

public interface ClassInfoService {
    List<ClassInfo> findAllClassInfo();
    Integer addClassInfo(ClassInfo classInfo);
    ClassInfo findClassByID(Integer clsid);
    Integer updateClassByID(ClassInfo classInfo);
    Integer deleteClassByID(Integer clsid);
}
