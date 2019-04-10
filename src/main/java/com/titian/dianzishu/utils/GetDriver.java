package com.titian.dianzishu.utils;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/*
 *  获取Webdriver
 */
public class GetDriver {

    private static WebDriver driver = null;


    /**
     *  linux获取
     *  获取谷歌驱动 chromedriver
     */
    public static WebDriver getChromeDriverOS(){

        try {
            String path = GetDriver.class.getProtectionDomain().getCodeSource().getLocation().getPath()+"webdriver/chromedriver";
                    // "C:\\webdriver\\chromedriver.exe";
//            String path = GetDriver.class.getProtectionDomain().getCodeSource().getLocation().getPath()+"driver/chromedriver.exe";
            System.out.println(path);
            System.setProperty("webdriver.chrome.driver", path);
            driver = new ChromeDriver();
        }catch (Exception e){
            System.err.println("错误原因：获取webdriver失败，请检查ChromeDriver.exe路径是否正确");
        }
        return driver;
    }

    /**
     *  windows获取
     *  获取谷歌驱动 chromedriver
     */
    public static WebDriver getChromeDriverWin(){

        try {
            String path = GetDriver.class.getProtectionDomain().getCodeSource().getLocation().getPath()+"webdriver/chromedriver.exe";
            System.setProperty("webdriver.chrome.driver", path);
            driver = new ChromeDriver();
        }catch (Exception e){
            System.err.println("错误原因：获取webdriver失败，请检查ChromeDriver.exe路径是否正确");
        }
        return driver;
    }

    /**
     *  获取 phantomjs
     */
    public static WebDriver getChromeHeadlessDriver(){

        try {
            String path = GetDriver.class.getProtectionDomain().getCodeSource().getLocation().getPath()+"webdriver/chromedriver.exe";
            System.setProperty("webdriver.chrome.driver", path);
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--disable-gpu");
            driver = new ChromeDriver(options);
//            driver = new ;
        }catch (Exception e){
            System.err.println("错误原因：启动chromeheadless失败");
        }
        return driver;
    }


    /** 获取htmlUtil */
//    public static WebDriver getHtmlUtilDriver(){
//        driver = new HtmlUnitDriver();
//        return driver;
//    }
//    获取火狐驱动

//    获取IE驱动


/**
    public static void initEnv(){
            try{
                String[] browser = gp.getValue(ConfigKey.valueOf("INITBROWSER").getValue()).split(",");
                for(int i=0;i<browser.length;i++){

                    if(browser[i].equalsIgnoreCase("chrome")){
//                        Runtime.getRuntime().exec("taskkill /f /t /im chrome.exe");
                        Runtime.getRuntime().exec("taskkill /f /t /im chromedriver.exe");
                    }

                }
                Runtime.getRuntime().exec("taskkill /f /t /im chromedriver.exe");
            }catch (Exception e){
                System.err.println("错误原因：初始化环境杀进程失败");
                e.printStackTrace();
            }

    }
    **/


}
