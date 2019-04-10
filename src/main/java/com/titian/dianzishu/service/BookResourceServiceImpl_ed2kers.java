package com.titian.dianzishu.service;

import com.titian.dianzishu.bean.BookResource;
import com.titian.dianzishu.mapper.BookResourceMapper;
import com.titian.dianzishu.pageElements.Ed2kersPage;
import com.titian.dianzishu.utils.GetDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("getBookResourceService")
public class BookResourceServiceImpl_ed2kers implements GetBookResourceService {

    private WebDriver driverL = GetDriver.getChromeDriverOS();
    private WebDriver driverD = GetDriver.getChromeDriverOS();
    private BookResource bookResource = new BookResource();
    @Resource
    private BookResourceMapper bookResourceMapper;

    /**
     * 列表页数据条数
     */
    private int listNum = 0;
    private String nowPage = "";
    private String detailurl = "";
    private String tmpclassify ="";
    private String tmpfilelink = "";
    private int tmpfileNum = 0;



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
                for (int i = 2; i <= listNum; i++) {

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
                    String tmpRsname = driverD.findElement(By.cssSelector(Ed2kersPage.resourcename)).getText();
                    bookResource.setResourcename(tmpRsname);

                    /** 分类**/
                    tmpclassify = driverD.findElement(By.cssSelector(Ed2kersPage.classify)).getText();
                    tmpclassify = tmpclassify.substring(tmpclassify.indexOf("类别:")+3,tmpclassify.indexOf("状态:")-1);
                    bookResource.setClassify(tmpclassify);

                    /** 文件大小  **/
                    bookResource.setFilesize(driverD.findElement(By.cssSelector(Ed2kersPage.filesize)).getText());

                    /** 资源链接 */
                    tmpfileNum = driverD.findElements(By.xpath(Ed2kersPage.fileNum)).size();
                    if (tmpfileNum > 0) {
                        for (int j = 1; j <= tmpfileNum; j++) {
                            if(tmpfilelink=="") {
                                tmpfilelink = driverD.findElement(By.xpath(Ed2kersPage.d_f_fileLink + j + Ed2kersPage.d_b_fileLink)).getAttribute("href");
                            }else{
                                tmpfilelink = tmpfilelink + ";" + driverD.findElement(By.xpath(Ed2kersPage.d_f_fileLink + j + Ed2kersPage.d_b_fileLink)).getAttribute("href");
                            }
                        }
                    } else {
                        continue;
                    }
                    bookResource.setFilelink(tmpfilelink);
                    bookResource.setRemark("ed2ker--nowPage--num:" + nowPage + "--" + (i-1));
                    /**
                     * 存表
                     */
                    bookResourceMapper.insertBookResource(bookResource);
                    tmpfilelink = "";

                    bookResource.cleanBookResource();
                    Thread.sleep(1000);
                }

                /** 获取当前页并判断是否是最后一页 **/
                if (driverL.findElement(By.xpath(Ed2kersPage.nextPage)) == null){
                    break;
                }else{
                    driverL.findElement(By.xpath(Ed2kersPage.nextPage)).click();
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }


}
