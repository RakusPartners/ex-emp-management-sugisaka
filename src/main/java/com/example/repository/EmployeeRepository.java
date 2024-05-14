package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Employee;

@Repository
public class EmployeeRepository {
    /**
     * ROW_MAPPERを定義し、Employeeオブジェクトを生成
     */
    private static final RowMapper<Employee> ROW_MAPPER = (rs, i) -> new Employee(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("image"),
            rs.getString("gender"),
            rs.getDate("hire_date"),
            rs.getString("mail_address"),
            rs.getString("zip_code"),
            rs.getString("address"),
            rs.getString("telephone"),
            rs.getInt("salary"),
            rs.getString("characteristics"),
            rs.getInt("dependents_count"));

    @Autowired

    private NamedParameterJdbcTemplate template;

    /**
     * 従業員一覧情報を入社日順(降順)で取得するメソッド。
     *
     * @return 従業員一覧情報。従業員が存在しない場合はサイズ0のリストを返す。
     */
    public List<Employee> findAll() {
        String sql = "SELECT * FROM employees ORDER BY hire_date DESC";
        return template.query(sql, ROW_MAPPER);
    }

    /**
     * 主キーから従業員情報を取得するメソッド。
     *
     * @param id 従業員ID
     * @return 従業員情報
     */
    public Employee load(Integer id) {
        String sql = "SELECT * FROM employees WHERE id = :id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        return template.queryForObject(sql, param, ROW_MAPPER);
    }

    /**
     * 従業員情報を変更するメソッド。
     *
     * @param employee 更新する従業員情報
     */
    public void update(Employee employee) {
        String sql = "UPDATE employees SET name = :name, image = :image, gender = :gender, hire_date = :hireDate, mail_address = :mailAddress, zip_code = :zipCode, address = :address, telephone = :telephone, salary = :salary, characteristics = :characteristics, dependents_count = :dependentsCount WHERE id = :id";
        SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
        template.update(sql, param);
    }
}
