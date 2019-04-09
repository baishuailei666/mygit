//package exportExcel.demo;
//
//import com.xlauncher.entity.OperationLog;
//import com.xlauncher.service.OperationLogService;
//import com.xlauncher.util.DatetimeUtil;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//import java.util.List;
//
//
///**
// * 导出Excel接口
// * @date 2018-05-16
// * @author 白帅雷
// */
//@Controller
//@RequestMapping(value = "/excel")
//public class ExportExcelController {
//    @Autowired
//    OperationLogService operationLogService;
//
//    /**
//     * 运维操作日志导出
//     *
//     * @param request request
//     * @param response response
//     * @param token 用户令牌
//     */
//    @ResponseBody
//    @RequestMapping(value = "/operation", method = RequestMethod.GET)
//    public void operationExportExcel(HttpServletRequest request, HttpServletResponse response, @RequestHeader("token") String token) {
//        //文件名
//        String fileName = "d:\\密云水库操作日志\\" + DatetimeUtil.getDate(DatetimeUtil.getDate(System.currentTimeMillis())) + " 密云水库运维操作日志.xls";
//        //sheet名
//        String sheetName = "操作日志详情";
//        //标题
//        String[] title = new String[]{"ID", "操作人员", "操作时间", "操作类型", "操作描述", "操作模块", "操作类别"};
//        //获取数据
//        List<OperationLog> logList = this.operationLogService.operationExcelList();
//        String[][] values = new String[logList.size()][];
//        for (int i = 0; i < logList.size(); i++) {
//            values[i] = new String[title.length];
//            // 将对象内容转换成string
//            OperationLog log = logList.get(i);
//            values[i][0] = log.getId() + "";
//            values[i][1] = log.getOperationPerson();
//            values[i][2] = log.getOperationTime();
//            values[i][3] = log.getOperationType();
//            values[i][4] = log.getOperationDescription();
//            values[i][5] = log.getOperationModule();
//            values[i][6] = log.getOperationCategory();
//        }
//        //创建HSSFWorkbook
//        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, values, null);
//        ExcelUtil.outputExcel(wb,fileName);
//
//    }
//
//    /**
//     * 运营操作日志导出
//     *
//     * @param request request
//     * @param response response
//     * @param token 用户令牌
//     */
//    @ResponseBody
//    @RequestMapping(value = "/operating", method = RequestMethod.GET)
//    public void operatingExportExcel(HttpServletRequest request, HttpServletResponse response, @RequestHeader("token") String token) {
//        //文件名
//        String fileName = "d:\\密云水库操作日志\\" + DatetimeUtil.getDate(DatetimeUtil.getDate(System.currentTimeMillis())) + " 密云水库运营操作日志.xls";
//        //sheet名
//        String sheetName = "操作日志详情";
//        //标题
//        String[] title = new String[]{"ID", "操作人员", "操作时间", "操作类型", "操作描述", "操作模块", "操作类别"};
//        //获取数据
//        List<OperationLog> operatingLogList = this.operationLogService.operatingExcelList();
//        String[][] operatingValues = new String[operatingLogList.size()][];
//        for (int j = 0; j < operatingLogList.size(); j++) {
//            operatingValues[j] = new String[title.length];
//            // 将对象内容转换成string
//            OperationLog operatingLog = operatingLogList.get(j);
//            operatingValues[j][0] = operatingLog.getId() + "";
//            operatingValues[j][1] = operatingLog.getOperationPerson();
//            operatingValues[j][2] = operatingLog.getOperationTime();
//            operatingValues[j][3] = operatingLog.getOperationType();
//            operatingValues[j][4] = operatingLog.getOperationDescription();
//            operatingValues[j][5] = operatingLog.getOperationModule();
//            operatingValues[j][6] = operatingLog.getOperationCategory() + "";
//        }
//        //创建HSSFWorkbook
//        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, operatingValues, null);
//        ExcelUtil.outputExcel(wb,fileName);
//    }
//
//    /**
//     * 事件操作日志导出
//     *
//     * @param request request
//     * @param response response
//     * @param token 用户令牌
//     */
//    @ResponseBody
//    @RequestMapping(value = "/event", method = RequestMethod.GET)
//    public void eventExportExcel(HttpServletRequest request, HttpServletResponse response, @RequestHeader("token") String token) {
//        //文件名
//        String fileName = "d:\\密云水库操作日志\\" + DatetimeUtil.getDate(DatetimeUtil.getDate(System.currentTimeMillis())) + " 密云水库事件操作日志.xls";
//        //sheet名
//        String sheetName = "操作日志详情";
//        //标题
//        String[] title = new String[]{"ID", "操作人员", "操作时间", "操作类型", "操作描述", "操作模块", "操作类别"};
//        //获取数据
//        List<OperationLog> excelLogList = this.operationLogService.eventExcelList();
//        String[][] eventValues = new String[excelLogList.size()][];
//        for (int k = 0; k < excelLogList.size(); k++) {
//            eventValues[k] = new String[title.length];
//            // 将对象内容转换成string
//            OperationLog operatingLog = excelLogList.get(k);
//            eventValues[k][0] = operatingLog.getId() + "";
//            eventValues[k][1] = operatingLog.getOperationPerson();
//            eventValues[k][2] = operatingLog.getOperationTime();
//            eventValues[k][3] = operatingLog.getOperationType()+ "";
//            eventValues[k][4] = operatingLog.getOperationDescription();
//            eventValues[k][5] = operatingLog.getOperationModule();
//            eventValues[k][6] = operatingLog.getOperationCategory();
//        }
//        //创建HSSFWorkbook
//        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, eventValues, null);
//        ExcelUtil.outputExcel(wb,fileName);
//    }
//
//
//
//
//    /**
//     * 发送响应流方法
//     *
//     * @param response response
//     * @param fileName 文件名
//     */
//    private void setResponseHeader(HttpServletResponse response, String fileName) {
//        try {
//            try {
//                fileName = new String(fileName.getBytes(),"ISO8859-1");
//            } catch (UnsupportedEncodingException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            response.setContentType("application/ms-excel;charset=UTF-8");
//            response.setHeader("Content-Disposition", "attachment;filename="
//                    .concat(String.valueOf(URLEncoder.encode(fileName + ".xls", "UTF-8"))));
//        } catch (Exception ex) {
//            // TODO Auto-generated catch block
//            System.out.println("输出流错误");
//            ex.printStackTrace();
//        }
//    }
//
//}
