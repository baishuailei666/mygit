//package exportExcel;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import java.io.OutputStream;
//import java.io.UnsupportedEncodingException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletResponse;
//
//import mvc.model.Feedback;
//import mvc.model.JResponse;
//import mvc.model.apis.DownloadExcel;
//import mvc.service.IAppInfoService;
//import mvc.service.IBusinessService;
//
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import javax.annotation.Resource;
//
//@Controller
//@RequestMapping(value = "/excel/*")
//public class ExcelController {
//    @Resource
//    private IAppInfoService appInfoService;
//    @Resource
//    private IBusinessService businessService;
//
//    @RequestMapping(value = "exportfeedback")
//    @ResponseBody
//    public JResponse exportFeedBack(HttpServletResponse response,
//                                    @RequestParam(value="query", required=false) String searchText,
//                                    @RequestParam(value="type", required=false) String strType,
//                                    @RequestParam(value="startDate", required=false) String startDate,
//                                    @RequestParam(value="endDate", required=false) String endDate){
//
//        String fileName = "反馈明细"+System.currentTimeMillis()+".xls"; //文件名
//        String sheetName = "反馈明细";//sheet名
//
//        String []title = new String[]{"Id","导航图标","反馈类型","内容","联系方式","应用Id","应用版本","反馈时间"};//标题
//
//        List<Feedback> list = appinfoService.getAllFeedbackForExcel(searchText, strType, startDate, endDate);//内容list
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        String [][]values = new String[list.size()][];
//        for(int i=0;i<list.size();i++){
//            values[i] = new String[title.length];
//            //将对象内容转换成string
//            Feedback obj = list.get(i);
//            values[i][0] = obj.getId()+"";
//            values[i][1] = obj.getFiles();
//            values[i][2] = obj.getFbType();
//            values[i][3] = obj.getContent();
//            values[i][4] = obj.getContactInfo();
//            values[i][5] = obj.getAppId();
//            values[i][6] = obj.getVersionName();
//            values[i][7] = sdf.format(obj.getCreateTime());
//
//        }
//
//        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, values, null);
//
//        //将文件存到指定位置
//        try {
//            this.setResponseHeader(response, fileName);
//            OutputStream os = response.getOutputStream();
//            wb.write(os);
//            os.flush();
//            os.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return JResponse.success("ok");
//    }
//
//    public void setResponseHeader(HttpServletResponse response, String fileName) {
//        try {
//            try {
//                fileName = new String(fileName.getBytes(),"ISO8859-1");
//            } catch (UnsupportedEncodingException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            response.setContentType("application/octet-stream;charset=ISO8859-1");
//            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
//            response.addHeader("Pargam", "no-cache");
//            response.addHeader("Cache-Control", "no-cache");
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//}
