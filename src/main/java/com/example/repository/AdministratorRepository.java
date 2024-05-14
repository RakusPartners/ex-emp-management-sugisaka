package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Administrator;

/*
 * Repositoryクラスであることを定義
 */
@Repository
public class AdministratorRepository {
    /**
     * ROW_MAPPERを定義し、Administratorオブジェクトを生成
     */
    private static final RowMapper<Administrator> ROW_MAPPER = (rs, i) -> new Administrator(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("mail_address"),
            rs.getString("password"));

    /**
     * @Autowiredをつけてオブジェクトへの参照変数を注入
     */
    @Autowired
    /**
     * 
     */

    /*
     * NamedParameterJdbcTemplateはDB操作に使用するテンプレートクラス
     */
    private NamedParameterJdbcTemplate template;

    /*
     * 管理者情報をテーブルに挿入するクラス
     */
    public void insert(Administrator administrator) {
        String sql = "INSERT INTO administrators (name,mail_address,password) VALUES (:name,:mailAddress,:password)";
        SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
        template.update(sql, param);
    }

    /**
     * メールアドレスとパスワードから管理者情報を取得する()
     */
    public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
        String sql = "SELECT id,name,mail_address,password FROM administrators WHERE mailaddress = :mailAddress AND password = :password";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("mailAddress", mailAddress)
                .addValue("password", password);
        try {
            return template.queryForObject(sql, param, ROW_MAPPER);
        } catch (Exception e) {
            return null;
        }
    }
}
