package excel;

import lombok.Data;
import com.alibaba.excel.annotation.ExcelProperty;

/**
 * @Author qinwenlong
 * @Date 2022/7/22
 **/
@Data
public class Info {

    @ExcelProperty("企业名称")
    private String enterpriseName;
    @ExcelProperty("服务商")
    private String supplierName;
    @ExcelProperty("姓名")
    private String name;
    @ExcelProperty("银行卡")
    private String cardNo;
    @ExcelProperty("电话")
    private String telephone;
    @ExcelProperty("身份证号")
    private String id;
    @ExcelProperty("金额")
    private Double money;


}
