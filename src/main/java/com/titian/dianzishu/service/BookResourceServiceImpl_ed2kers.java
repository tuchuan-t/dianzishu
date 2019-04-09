package com.titian.dianzishu.service;

import com.titian.dianzishu.bean.BookResource;
import com.titian.dianzishu.mapper.BookResourceMapper;
import com.titian.dianzishu.pageElements.Ed2kersPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class BookResourceServiceImpl_ed2kers implements GetBookResourceService {

    @Resource
    private WebDriver driverL;
    @Resource
    private WebDriver driverD;
    @Resource
    private BookResource bookResource;
    @Resource
    private BookResourceMapper bookResourceMapper;

    /**
     * 列表页数据条数
     */
    private int listNum = 0;
    private String nowPage = "";
    private String detailurl = "";



    @Override
    public int searchBookResource(String listurl) {

        try {
            /** 打开列表页*/
            driverL.get(listurl);

            /** 大循环，列表获取 **/

            while (true) {
                /** *获取当前页数 **/
                nowPage = driverL.findElement(By.cssSelector(Ed2kersPage.nowPage)).getText();

                /**获取当前列表页条数*/
                listNum = driverL.findElements(By.cssSelector(Ed2kersPage.nowBookNum)).size();

                /**采集当前页书目  ed2kers列表中排出第一行不是书目，所以从1开始取**/
                for (int i = 1; i <= listNum; i++) {

                    /**
                     * 获取详情页url
                     */
                    detailurl = driverL.findElement(By.xpath(Ed2kersPage.d_f_Url + i+Ed2kersPage.d_b_Url)).getAttribute("href");

                    /**
                     * 打开详情页
                     */
                    driverD.get(detailurl);

                    /**
                     * 获取详情页内容
                     */
                    bookResource.setResourcename(driverD.findElement(By.cssSelector(Ed2kersPage.resourcename)).getText());

                    /** 分类**/
                    String classify = driverD.findElement(By.cssSelector(Ed2kersPage.classify)).getText();
                    classify = classify.substring(classify.indexOf("类别:"),classify.indexOf("状态:"));
                    bookResource.setClassify(classify);
                    bookResource.setFilesize(driverD.findElement(By.cssSelector(Ed2kersPage.filesize)).getText());
                    bookResource.setFilelink(driverD.findElement(By.cssSelector(Ed2kersPage.filelink)).getText());
                    bookResource.setIfpackage(driverD.findElement(By.cssSelector(Ed2kersPage.resourcename)).getText());
                    bookResource.setRemark("ed2ker--nowPage--num:" + nowPage + "--" + i);
                    /**
                     * 存表
                     */
                    bookResourceMapper.insertBookResource(bookResource);
                    bookResource = null;

                    /** 获取当前页并判断是否是最后一页 **/
                    if (driverL.findElement(By.xpath(Ed2kersPage.nextPage)) == null)
                        break;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }


}
