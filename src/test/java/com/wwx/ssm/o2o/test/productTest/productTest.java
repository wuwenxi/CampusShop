package com.wwx.ssm.o2o.test.productTest;

import com.wwx.ssm.o2o.bean.ImageHolder;
import com.wwx.ssm.o2o.dao.ProductMapper;
import com.wwx.ssm.o2o.entity.Product;
import com.wwx.ssm.o2o.entity.ProductCategory;
import com.wwx.ssm.o2o.entity.Shop;
import com.wwx.ssm.o2o.enums.ProductEnum;
import com.wwx.ssm.o2o.execution.ProductExecution;
import com.wwx.ssm.o2o.service.ProductService;
import com.wwx.ssm.o2o.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class productTest extends BaseTest {

    @Autowired
    ProductMapper mapper;

    @Autowired
    ProductService service;

    @Test
    public void test01(){
        ProductCategory category = new ProductCategory();
        category.setProductCategoryId(2);
        Shop shop = new Shop();
        shop.setShopId(41);
        int num = mapper.insertProduct(
                new Product(null,"灌汤包","灌汤包",null,"10.00",
                        "10.00",100, new Date(),new Date(),
                        1,null,category, shop));
        if(num <= 0){
            System.out.println("添加失败");
        }else {
            System.out.println("添加成功");
        }
    }

    @Test
    public void test02(){
        Product product = new Product();
        List<Product> list = mapper.queryProductList(product,0,3);

        int count = mapper.queryProduct(product);
        System.out.println(count);
        for(Product product1:list){
            System.out.println(product1);
        }

        product.setProductName("包");
        List<Product> list1 = mapper.queryProductList(product,0,100);

        int count1 = mapper.queryProduct(product);
        System.out.println(count1);
        for(Product product2:list1){
            System.out.println(product2);
        }
    }

    @Test
    public void test03(){
        //Product product = mapper.queryProductById(1);
        ProductExecution execution = service.getProductById(1);
        if(execution.getState().equals(ProductEnum.SUCCESS.getStatus())){
            System.out.println(execution.getProduct().getProductImgList());
        }
    }

    @Test
    public void test04(){
        ProductCategory category = new ProductCategory();
        category.setProductCategoryId(2);
        Shop shop = new Shop();
        shop.setShopId(41);
        int num = mapper.updateProduct(new Product(3,"灌汤包","正宗的灌汤包",null,"10.00",
                "8.00",100, null,new Date(),
                1,null,category, shop));
        if(num <= 0){
            System.out.println("更新失败");
        }else {
            System.out.println("更新成功");
        }
    }

    @Test
    public void test05() throws Exception{
        ProductCategory category = new ProductCategory();
        category.setProductCategoryId(2);
        Shop shop = new Shop();
        shop.setShopId(40);
        File file1 = new File("F:\\新建文件夹\\timg.jpg");
        InputStream is1 = new FileInputStream(file1);
        ImageHolder image = new ImageHolder(file1.getName(),is1);

        File file2 = new File("F:\\新建文件夹\\3.jpg");
        InputStream is2 = new FileInputStream(file2);
        ImageHolder image1 = new ImageHolder(file1.getName(),is2);
        File file3 = new File("F:\\新建文件夹\\5.jpg");
        InputStream is3 = new FileInputStream(file3);
        ImageHolder image2 = new ImageHolder(file1.getName(),is3);

        List<ImageHolder> imageList = new ArrayList<ImageHolder>();
        imageList.add(image1);
        imageList.add(image2);
        Product product = new Product(2,"灌汤包","正宗的灌汤包",null,"10.00",
                "8.00",100, null,new Date(),
                1,null,category, shop);
        ProductExecution execution = service.modifyProduct(product,image,imageList);
        if(execution.getState().equals(ProductEnum.SUCCESS.getStatus())){
            System.out.println("成功");
        }
    }

    @Test
    public void updateProductCategoryToNull(){
        int num = mapper.updateProductCategoryToNull(2);
        System.out.println(num);
    }
}
