package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.InsertAdministratorForm;
import com.example.service.AdministratorService;

@Controller
@RequestMapping("/")
public class AdministractorController {
    @Autowired
    private AdministratorService administratorService;

    /**
     * administrator/insertにフォワードする処理を記述
     * フォームを引数で受け取ることで従業員登録する際のリクエストパラメータが格納される
     */
    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm form) {
        return "administrator/insert";
    }
}
