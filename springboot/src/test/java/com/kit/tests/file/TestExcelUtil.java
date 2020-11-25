package com.kit.tests.file;

import com.kit.util.ExcelUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wangheng
 * @date 2020/11/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestExcelUtil {

    ExcelUtil excelTest = new ExcelUtil();

    @Test
    public void test() {
    }

    @Test
    public void testAnalyzeExcel() {

        Workbook wb = excelTest.getExcel("F:\\workFiles\\需求\\运维平台展示功能任务\\赣榆转发点表_20200513(组串对照).xlsx");

        if (wb == null)
            System.out.println("文件读入出错");
        else {
            excelTest.analyzeExcel(wb);
        }
    }

}
