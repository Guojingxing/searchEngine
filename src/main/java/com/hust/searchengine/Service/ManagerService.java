package com.hust.searchengine.Service;


import com.github.pagehelper.PageInfo;
import com.hust.searchengine.Entity.ClassInfo;
import com.hust.searchengine.Entity.Manager;
import com.hust.searchengine.Entity.User;

import java.util.List;

public interface ManagerService {
    Manager managerLogin(Integer managerid, String password);
    Integer totalNumberOfArticles();
    Integer totalNumberOfUsers();
    PageInfo<User> findAllUsers(Integer pageIndex, Integer pageSize);
    Integer deleteUserByUsername(String username);
    Integer addNewUser(User user);

    //忽略以下代码
//    List<ClassInfo> findAllClassInfo();
//    Integer addClassInfo(ClassInfo classInfo);
//    ClassInfo findClassByID(Integer clsid);
//    Integer updateClassByID(ClassInfo classInfo);
//    Integer deleteClassByID(Integer clsid);
}