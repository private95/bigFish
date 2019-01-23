package com.egoo.seckill.bigfish.service;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.egoo.seckill.bigfish.domain.enums.WithDrawStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 导出excel
 * @author lixing
 */
@Service
@Slf4j
public class ExcelService {



    public boolean excute(WithDrawStatus withDrawStatus, HttpServletResponse response) throws IOException {

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

        ServletOutputStream out = response.getOutputStream();
//        OutputStream out = null;
        try {

            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);


//          out = new FileOutputStream("C:\\Users\\28107\\Desktop\\bigfish-"+format.format(new Date())+".xlsx");

//          ExcelWriter writer = EasyExcelFactory.getWriter(out);
            //写第一个sheet, sheet1  数据全是List<String> 无模型映射关系
            Sheet sheet1 = new Sheet(1, 3);
            sheet1.setSheetName("提现列表");

            //设置列宽 设置每列的宽度
            Map columnWidth = new HashMap();
            columnWidth.put(0,3000);
            sheet1.setColumnWidthMap(columnWidth);
            //标题
            sheet1.setHead(createListStringHead());
            //or 设置自适应宽度
            sheet1.setAutoWidth(Boolean.TRUE);

            //内容
            writer.write1(createListObject(withDrawStatus), sheet1);
            response.setContentType("application/octet-stream; charset=utf-8");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename=aohan-"+format.format(new Date())+".xlsx");
            writer.finish();

        }catch (Exception ex){
            log.error("导出excel异常！,异常信息：{}",ex.getMessage());
            return false;
        }
        return true;

    }


    private static List<List<String>> createListStringHead(){
        String[] headStr = {"提现单号","提现金额","时间","银行卡号","银行名称","提现状态","用户编号","收款人名称"};

        List<List<String>> head = new ArrayList<List<String>>();

        for (int i =0 ;i<headStr.length;i++){
            List<String> headCoulumn = new ArrayList<String>();
            headCoulumn.add(headStr[i]);
            head.add(headCoulumn);
        }
        return head;

    }

    private static  List<List<Object>>  createListObject(WithDrawStatus withDrawStatus){
        List<List<Object>> rows = new ArrayList<List<Object>>();

        //TODO 根据状态查询提现列表

        for (int i =0 ; i<100;i++){
            List<String> headCoulumn = new ArrayList<String>();
            //TODO 列表里面的每一实体的字段
        }
        return rows;
    }

    public static List<List<Object>> createTestListObject() {
        List<List<Object>> object = new ArrayList<List<Object>>();
        for (int i = 0; i < 8; i++) {
            List<Object> da = new ArrayList<Object>();
            da.add("字符串"+i);
            da.add("字符串"+i);
            da.add("字符串"+i);
            da.add("字符串"+i);
            da.add("字符串"+i);
            da.add("字符串"+i);
            da.add("字符串"+i);
            da.add("字符串"+i);
            object.add(da);
        }
        return object;
    }

}
