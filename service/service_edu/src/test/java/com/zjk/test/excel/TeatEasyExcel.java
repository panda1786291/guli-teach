package com.zjk.test.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;

import javax.jnlp.ClipboardService;
import java.util.ArrayList;
import java.util.List;

public class TeatEasyExcel {
    public static void main(String[] args) {

        //实现excel的写操作
        //1 设置写入文件夹的地址和excel文件名称
        String fileName = "D:\\Computer\\UploadFiles\\zjk.xlsx";

//        //
//        EasyExcel.write(fileName, DemoData.class)
//                .sheet("学生列表")
//                .doWrite(getDate());
        EasyExcel.read(fileName,DemoData.class,new ExcelListener())
                .sheet().doRead();
    }

    private static List<DemoData> getDate(){
        ArrayList<DemoData> list = new ArrayList<>();
        for (int i=0;i<10;i++){
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("lucy"+i);
            list.add(data);
        }
        return list;
    }
}
