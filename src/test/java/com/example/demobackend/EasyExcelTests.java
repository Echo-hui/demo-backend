package com.example.demobackend;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.listener.PageReadListener;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EasyExcelTests {
    @Test
    void readExcel() {
        System.out.println("readExcel");
        EasyExcel.read("D:\\113test.xlsx", new PageReadListener<>(dataList -> {
            for (Object item : dataList) {
                System.out.println(item.toString());
            }
        })).sheet().doRead();
        String path = "D:\\113test.xlsx";
        List<Map<Integer, String>> headerList = readExcelHeader(path);
        System.out.println(headerList.toString());


    }

    public static List<Map<Integer, String>> readExcelHeader(String filePath) {
        List<Map<Integer, String>> headerList = new ArrayList<>();
        EasyExcel.read(filePath, new AnalysisEventListener<Map<Integer, String>>() {
            @Override
            public void invoke(Map<Integer, String> data, AnalysisContext context) {
                // 数据行的处理逻辑（这里不读取实际数据行）
            }
            @Override
            public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
                // 收集表头
                headerList.add(headMap);
            }
            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                // 全部解析完成后执行
            }
        }).sheet().doRead();

        return headerList;
    }
}


