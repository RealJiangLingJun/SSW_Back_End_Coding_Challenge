package top.jiangjiawei.coding_challenge.main;

import top.jiangjiawei.coding_challenge.dao.ProductDao;
import top.jiangjiawei.coding_challenge.domain.Product;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * @program: MyResume
 * @description: Main
 * @author: LingJunJiang
 * @create: 2023-03-03 21:14
 **/
public class Main {
    public static void main(String[] args) throws SQLException {
        ProductDao productDao = new ProductDao();
        ArrayList<Product> products = new ArrayList<>(); // 存储查询到的数据
        Product product = null; //

        while (true) {
            int num = 0;
            String key;
            System.out.println("*************简单的产品信息管理系统*******************");
            System.out.println("1 关键字产品查询");
            System.out.println("2 新增产品");
            System.out.println("3 编辑产品");
            System.out.println("4 删除产品");
            System.out.println("0 退出");
            System.out.println("请输入操作数：");

            Scanner scanner = new Scanner(System.in);
            try{
                num = scanner.nextInt();
            }catch (Exception e){
                System.out.println("请输入数字！");
                continue;
            }
//            System.out.println(num);


            switch (num){
                case 0:
                    System.out.println("系统退出！");
                    return;
                case 1:
                    System.out.println("请输入查询关键字：");
                    key = scanner.next();
                    products = productDao.getProducts(key);

                    if (products.size() > 0){
                        for (Product p : products) {
                            System.out.println(p);
                        }
                        System.out.println("已查询所有匹配项！");
                    }else {
                        System.out.println("找不到匹配产品！");
                    }
                    break;
                case 2:
                    product = new Product();
                    System.out.println("产品名称：");
                    product.setP_name(scanner.next());

                    System.out.println("产品编码：");
                    product.setP_code(scanner.next());

                    System.out.println("产品规格：");
                    product.setP_spec(scanner.next());

                    System.out.println("产品库存：");
                    product.setP_stock(scanner.nextInt());

                    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                    Date date = new Date(System.currentTimeMillis());
                    product.setP_cTime(formatter.format(date));

                    if (productDao.p_insert(product)){
                        System.out.println(product.getP_name()+"增加成功！");
                    }
                    break;
                case 3:
                    products = productDao.getAllProducts();
                    for (Product p : products){
                        System.out.println(p);
                    }
                    System.out.println("输入产品名称以选择所编辑的产品：");
                    key = scanner.next();
                    product = productDao.getProductByName(key);
                    if(product == null){
                        System.out.println("未找到对应商品！");
                        continue;
                    }
                    System.out.println("请输入你要修改的信息对应的数字：");
                    System.out.println("1 产品编码");
                    System.out.println("2 产品规格");
                    System.out.println("3 产品库存");
                    try{
                        num = scanner.nextInt();
                    }catch (Exception e){
                        System.out.println("请输入数字！");
                        continue;
                    }
                    switch (num){
                        case 1:
                            System.out.println("请输入新产品编码：");
                            product.setP_code(scanner.next());
                            break;
                        case 2:
                            System.out.println("请输入新产品规格：");
                            product.setP_spec(scanner.next());
                            break;
                        case 3:
                            System.out.println("请输入新产品库存：");
                            try{
                                product.setP_stock(scanner.nextInt());
                            }catch (Exception e){
                                System.out.println("请输入数字！");
                                continue;
                            }
                            break;
                        default:
                            System.out.println("请输入正确操作数！");
                            continue;
                    }
                    if(productDao.p_update(product)){
                        System.out.println(product.getP_name()+ "产品信息更新成功！");
                    }else {
                        System.out.println("产品信息更新失败！");
                    }
                    break;
                case 4:
                    products = productDao.getAllProducts();
                    for (Product p : products){
                        System.out.println(p);
                    }
                    System.out.println("请输入需要删除的产品名称：");
                    if(productDao.p_delete(scanner.next())){
                        System.out.println("产品删除成功！");
                    }else {
                        System.out.println("找不到要删除的产品，请检查产品名称！");
                    }
                    break;
                default:
                    System.out.println("请输入正确操作数！");
                    continue;
            }
        }
    }
}
