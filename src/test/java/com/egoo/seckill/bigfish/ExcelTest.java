package com.egoo.seckill.bigfish;


import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.egoo.seckill.bigfish.excel.EasyExcelFactory;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class ExcelTest {



  /*  @Test
    public void test1() throws FileNotFoundException {
        OutputStream out = new FileOutputStream("E:/excel-model/bigfish.xlsx");
        ExcelWriter writer = EasyExcelFactory.getWriter(out);
        //写第一个sheet, sheet1  数据全是List<String> 无模型映射关系
        Sheet sheet1 = new Sheet(1, 3);
        sheet1.setSheetName("提现列表");

        //设置列宽 设置每列的宽度
        Map columnWidth = new HashMap();
        columnWidth.put(0,2000);
        columnWidth.put(1,3000);
        columnWidth.put(2,4000);
        columnWidth.put(3,5000);
        sheet1.setColumnWidthMap(columnWidth);
        sheet1.setHead(createTestListStringHead());
        //or 设置自适应宽度
        sheet1.setAutoWidth(Boolean.TRUE);
        writer.write1(createTestListObject(), sheet1);

        writer.finish();
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/
}
