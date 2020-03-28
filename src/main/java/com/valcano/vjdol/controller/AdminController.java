//package com.valcano.vjdol.controller;
//
//import com.valcano.vjdol.entity.Admin;
//import com.valcano.vjdol.repository.AdminRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//public class AdminController {
//    /**
//     * 查询所有管理员账号
//     *
//     * @return
//     */
//    @PostMapping(value = "")
//    @GetMapping(value = "/admin")
//    public List<Admin> adminList() {
//        return adminRepository.findAll();
//    }
//}
