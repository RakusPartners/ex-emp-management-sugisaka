package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class AdministractorController {
    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private HttpSession session;

    /**
     * administrator/insertにフォワードする処理を記述
     * フォームを引数で受け取ることで従業員登録する際のリクエストパラメータが格納される
     */
    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm form) {
        return "administrator/insert";
    }

    @PostMapping("/insert")

    /*
     * 管理者情報を登録する
     */
    public String insert(InsertAdministratorForm form) {
        Administrator administrator = new Administrator();
        /*
         * InsertAdministratorFormオブジェクトの中身をインスタンス化したAdministratorドメインオブジェクトにコポーする
         */
        administrator.setName(form.getName());
        administrator.setMailAddress(form.getMailAddress());
        administrator.setPassword(form.getPassword());

        /*
         * administratorServiceのinsert()メソッドを呼ぶ
         */
        administratorService.insert(administrator);

        /*
         * ログイン画面にリダイレクトする
         */
        return "redirect:/login";

    }

    @GetMapping("/")
    public String toLogin(LoginForm form) {
        return "administrator/login";
    }

    @PostMapping("/login")
    public String login(LoginForm form, Model model) {
        Administrator administrator = administratorService.login(form.getMailAddress(), form.getPassword());
        if (administrator == null) {
            model.addAttribute("error", true);
            return "administrator/login";
        } else {
            session.setAttribute("administratorName", administrator.getName());
            return "redirect:/employee/showList";
        }
    }
}
