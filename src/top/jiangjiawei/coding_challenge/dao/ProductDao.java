package top.jiangjiawei.coding_challenge.dao;

import top.jiangjiawei.coding_challenge.configration.ConfigurationReader;
import top.jiangjiawei.coding_challenge.domain.Product;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @program: MyResume
 * @description:
 * @author: LingJunJiang
 * @create: 2023-03-03 21:14
 **/
public class ProductDao {


    private static String url = ConfigurationReader.getStringValue("url");
    private static String root = ConfigurationReader.getStringValue("username");
    private static String password = ConfigurationReader.getStringValue("password");
    private static Connection conn = null;
    private static PreparedStatement pstat = null;

    static {
        try {
            Class.forName(ConfigurationReader.getStringValue("className"));
            conn = DriverManager.getConnection(url,root,password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }



    public ProductDao() throws SQLException {
    }

    // 查询商品信息 通过关键字
    // 用户可以通过一个关键字，查寻找到 产品名称或产品编码或产品规格 匹配的产品数据
    public ArrayList<Product> getProducts(String msg){
        ArrayList<Product> PList = new ArrayList<>();
        Product product = null;
        try {
            pstat = conn.prepareStatement(
                    "select * from product where p_name like ? or p_code like ? or p_spec like ?"
            );
            pstat.setString(1,"%"+msg+"%");
            pstat.setString(2,"%"+msg+"%");
            pstat.setString(3,"%"+msg+"%");

            ResultSet resultSet = pstat.executeQuery();
            while(resultSet.next()){
                product = new Product();
                product.setP_name(resultSet.getString("p_name"));
                product.setP_code(resultSet.getString("p_code"));
                product.setP_spec(resultSet.getString("p_spec"));
                product.setP_stock(resultSet.getInt("p_stock"));
                product.setP_cTime(resultSet.getString("p_cTime"));
                PList.add(product);
                product = null;
            }
            pstat.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return PList;
    }

    // 查询出一个产品
    public Product getProductByName(String msg){
        ArrayList<Product> PList = new ArrayList<>();
        Product product = null;
        try {
            conn = DriverManager.getConnection(url,root,password);
            pstat = conn.prepareStatement(
                    "select * from product where p_name = ?"
            );
            pstat.setString(1,msg);

            ResultSet resultSet = pstat.executeQuery();
            while(resultSet.next()){
                product = new Product();
                product.setP_name(resultSet.getString("p_name"));
                product.setP_code(resultSet.getString("p_code"));
                product.setP_spec(resultSet.getString("p_spec"));
                product.setP_stock(resultSet.getInt("p_stock"));
                product.setP_cTime(resultSet.getString("p_cTime"));
            }
            pstat.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    // 查询所有
    public ArrayList<Product> getAllProducts(){
        ArrayList<Product> PList = new ArrayList<>();
        Product product = null;

        try {
            conn = DriverManager.getConnection(url,root,password);
            pstat = conn.prepareStatement(
                    "select * from product"
            );
            ResultSet resultSet = pstat.executeQuery();
            while(resultSet.next()){
                product = new Product();
                product.setP_name(resultSet.getString("p_name"));
                product.setP_code(resultSet.getString("p_code"));
                product.setP_spec(resultSet.getString("p_spec"));
                product.setP_stock(resultSet.getInt("p_stock"));
                product.setP_cTime(resultSet.getString("p_cTime"));
                PList.add(product);
                product = null;
            }
            pstat.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return PList;
    }

    // 新增产品
    public boolean p_insert(Product product){
        boolean flag = false;
        try {
            conn = DriverManager.getConnection(url,root,password);
            pstat = conn.prepareStatement("insert into product values(?,?,?,?,?);");
            pstat.setString(1,product.getP_name());
            pstat.setString(2,product.getP_code());
            pstat.setString(3,product.getP_spec());
            pstat.setInt(4,product.getP_stock());
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            java.util.Date date = new Date(System.currentTimeMillis());
            pstat.setString(5,formatter.format(date));

            if(pstat.execute()){
                flag = true;
            }
            pstat.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    // 可以更新产品
    public boolean p_update(Product product){
        boolean flag = false;
        try {
            conn = DriverManager.getConnection(url,root,password);
            pstat = conn.prepareStatement("update product set p_code = ?, p_spec = ?, p_stock = ?, p_cTime = ? where p_name = ?");
            pstat.setString(1,product.getP_code());
            pstat.setString(2,product.getP_spec());
            pstat.setInt(3,product.getP_stock());
            pstat.setInt(4,product.getP_stock());
            pstat.setString(5,product.getP_name());

            if(pstat.executeUpdate()>0){
                flag = true;
            }
            pstat.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    // 删除产品
    public boolean p_delete(String msg){
        boolean flag = false;
        try {
            conn = DriverManager.getConnection(url,root,password);
            pstat = conn.prepareStatement("delete from product where p_name = ?");
            pstat.setString(1,msg);
            if(pstat.executeUpdate()>0){
                flag = true;
            }
            pstat.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
