package com.example.domain;

/** administratorsテーブルのカラムに対応した属性を定義 */

public class Administrator {
    private Integer id;
    private String name;
    private String mailAddress;
    private String password;

    public Administrator() {
        /**
         * 処理は何も記載しない
         */
    }

    /**
     * すべてのフィールドを引数に取るコンストラクタの生成
     */
    public Administrator(Integer id, String name, String mailAddress, String password) {
        this.id = id;
        this.name = name;
        this.mailAddress = mailAddress;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Administrator [id=" + id + ", name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
                + "]";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
