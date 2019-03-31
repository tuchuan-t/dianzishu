package com.titian.dianzishu.service;

import com.titian.dianzishu.bean.BookResource;
import com.titian.dianzishu.mapper.BookResourceMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class BookResourceServiceImpl implements BookResourceService {

    @Resource
    private WebDriver driver;
    @Resource
    private WebDriver driverlist;
    @Resource
    private BookResource bookResource;
    @Resource
    private BookResourceMapper bookResourceMapper;

    /**
     * 列表页数据条数
     */
    private int listNum = 0;
    private String detailurl = "";


    @Override
    public int insertBookResource(BookResource bookResource,String listurl) {

        try {
            /**
             * 打开列表页
             */
            driverlist.get(listurl);

            /**
             * 获取当前列表页条数
             */
            listNum = driver.findElements(By.cssSelector("")).size();

            for(int i = 0;i<=listNum;i++){

                /**
                 * 获取详情页url
                 */
                detailurl = driverlist.findElement(By.cssSelector(""+i)).getAttribute("href");

                /**
                 * 打开详情页
                 */
                driver.get(detailurl);

                /**
                 * 获取详情页内容
                 */
                bookResource.setResourcename(driver.findElement(By.cssSelector("")).getText());
                bookResource.setClassify(driver.findElement(By.cssSelector("")).getText());
                bookResource.setFilesize(driver.findElement(By.cssSelector("")).getText());
                bookResource.setFilelink(driver.findElement(By.cssSelector("")).getText());
                bookResource.setIfpackage(driver.findElement(By.cssSelector("")).getText());
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        return bookResourceMapper.insertBookResource(bookResource);
    }


}
