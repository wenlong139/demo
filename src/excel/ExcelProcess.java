package excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author qinwenlong
 * @Date 2022/7/22
 **/
public class ExcelProcess {
    public static void main(String[] args) {
        String fileName = "/Users/qinwenlong/personal/tutu.xlsx";
        String fileName1 = "/Users/qinwenlong/personal/tutu1.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, Info.class, new ReadListener<Info>() {
            /**
             * 单次缓存的数据量
             */
            public static final int BATCH_COUNT = 100;
            /**
             *临时存储
             */
            private List<Info> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

            @Override
            public void invoke(Info data, AnalysisContext context) {
                cachedDataList.add(data);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                List<Info> infoList =
                        cachedDataList.stream().filter(info -> info.getSupplierName() != null).collect(Collectors.toList());
                Map<String, List<Info>> map =
                        infoList.stream().collect(Collectors.groupingBy(Info::getSupplierName));
                int i = 0;
                try (ExcelWriter excelWriter = EasyExcel.write(fileName1, Info.class).build()) {
                    for (Map.Entry<String, List<Info>> entry : map.entrySet()) {
                        Map<String, List<Info>> idMap =
                                entry.getValue().stream().collect(Collectors.groupingBy(Info::getId));
                        List<Info> toWriteInfos = new ArrayList<>();

                        for (Map.Entry<String, List<Info>> idEntry : idMap.entrySet()) {
                            List<Info> infos = idEntry.getValue();
                            double sum = infos.stream().mapToDouble(Info::getMoney).sum();
                            Info info = infos.get(0);
                            info.setMoney(sum);
                            toWriteInfos.add(info);
                        }

                        // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来。这里最终会写到5个sheet里面
                        // 每次都要创建writeSheet 这里注意必须指定sheetNo 而且sheetName必须不一样
                        WriteSheet writeSheet = EasyExcel.writerSheet(i, entry.getKey()).head(Info.class).build();

                        // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
                        excelWriter.write(toWriteInfos, writeSheet);
                        i++;

                    }
                    System.out.println("===");

                }
                System.out.println(cachedDataList);
            }

            /**
             * 加上存储数据库
             */
            private void saveData() {
            }
        }).sheet().doRead();
    }
}
