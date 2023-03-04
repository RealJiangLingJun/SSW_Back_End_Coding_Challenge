package top.jiangjiawei.coding_challenge.domain;

/**
 * @program: MyResume
 * @description: 产品
 * @author: LingJunJiang
 * @create: 2023-03-03 21:39
 **/
public class Product {
    private String p_name;
    private String p_code;
    private String p_spec;
    private int p_stock;
    private String p_cTime;

    public Product() {
    }

    public Product(String p_name, String p_code, String p_spec, int p_stock, String p_cTime) {
        this.p_name = p_name;
        this.p_code = p_code;
        this.p_spec = p_spec;
        this.p_stock = p_stock;
        this.p_cTime = p_cTime;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_code() {
        return p_code;
    }

    public void setP_code(String p_code) {
        this.p_code = p_code;
    }

    public String getP_spec() {
        return p_spec;
    }

    public void setP_spec(String p_spec) {
        this.p_spec = p_spec;
    }

    public int getP_stock() {
        return p_stock;
    }

    public void setP_stock(int p_stock) {
        this.p_stock = p_stock;
    }

    public String getP_cTime() {
        return p_cTime;
    }

    public void setP_cTime(String p_cTime) {
        this.p_cTime = p_cTime;
    }

    @Override
    public String toString() {
        return "产品{" +
                "名称='" + p_name + '\'' +
                ", 编码='" + p_code + '\'' +
                ", 规格='" + p_spec + '\'' +
                ", 库存=" + p_stock +
                ", 创建时间='" + p_cTime + '\'' +
                '}';
    }
}
