package com.wwx.ssm.o2o.service.impl;

import com.wwx.ssm.o2o.dao.ProductImgMapper;
import com.wwx.ssm.o2o.entity.ProductImg;
import com.wwx.ssm.o2o.enums.ProductImgEnum;
import com.wwx.ssm.o2o.exception.ProductImgException;
import com.wwx.ssm.o2o.execution.ProductImgExecution;
import com.wwx.ssm.o2o.service.ProductImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImgServiceImpl implements ProductImgService {

    @Autowired
    ProductImgMapper mapper;

    public ProductImgExecution getProductImgList(Integer productId) {
        try {
            List<ProductImg> list = mapper.queryProductImgList(productId);
            if(list != null && list.size() > 0){
                return new ProductImgExecution(ProductImgEnum.SUCCESS,list);
            }
            return new ProductImgExecution(ProductImgEnum.ERROR);
        } catch (Exception e) {
            throw new ProductImgException("errorMsg:"+e.getMessage());
        }
    }

    public ProductImgExecution batchAddProductImg(List<ProductImg> productImgList) {

        if(productImgList != null && productImgList.size() > 0){
            try {
                int num = mapper.batchInsertProductImg(productImgList);
                if(num <= 0){
                    return new ProductImgExecution(ProductImgEnum.ERROR);
                }
                return new ProductImgExecution(ProductImgEnum.SUCCESS);
            } catch (Exception e) {
                throw new ProductImgException("errorMsg:"+e.getMessage());
            }
        }
        return new ProductImgExecution(ProductImgEnum.ERROR);
    }

    public ProductImgExecution deleteProductImgByProductId(Integer productId) {
        if(productId != null && productId >0){
            try {
                int num = mapper.deleteProductImgByProductId(productId);
                if(num <= 0){
                    return new ProductImgExecution(ProductImgEnum.ERROR);
                }
                return new ProductImgExecution(ProductImgEnum.SUCCESS);
            } catch (Exception e) {
                throw new ProductImgException("errorMsg:"+e.getMessage());
            }
        }
        return new ProductImgExecution(ProductImgEnum.ERROR);
    }
}
