package com.example.demo.ServiceImpl;

import com.example.demo.Entity.ClassInfo;
import com.example.demo.Entity.Student;
import com.example.demo.Mapper.ClassMapper;
import com.example.demo.Service.ClassInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassInfoServiceImpl implements ClassInfoService {

    @Autowired
    private ClassMapper classMapper;

    @Override
    public List<ClassInfo> findAllClassInfo() {
        return classMapper.findAllClassInfo();
    }

    @Override
    public Integer addClassInfo(ClassInfo classInfo) {
        return classMapper.addClassInfo(classInfo);
    }

    @Override
    public ClassInfo findClassByID(Integer clsid) {
        return classMapper.findClassByID(clsid);
    }

    @Override
    public Integer updateClassByID(ClassInfo classInfo) {
        return classMapper.updateClassByID(classInfo);
    }

    @Override
    public Integer deleteClassByID(Integer clsid) {
        return classMapper.deleteClassByID(clsid);
    }
}
