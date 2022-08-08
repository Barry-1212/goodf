package cn.edu.lingnan.goodf.common.utils;


import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SendTelCode {


    /**
     //     * 创建指定数量的随机字符串
     //     * @param numberFlag 是否是数字
     //     * @param length
     //     * @return
     //     */
    public static String createRandom(boolean numberFlag, int length){
        String retStr = "";
        String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
        int len = strTable.length();
        boolean bDone = true;
        do {
            retStr = "";
            int count = 0;
            for (int i = 0; i < length; i++) {
                double dblR = Math.random() * len;
                int intR = (int) Math.floor(dblR);
                char c = strTable.charAt(intR);
                if (('0' <= c) && (c <= '9')) {
                    count++;
                }
                retStr += strTable.charAt(intR);
            }
            if (count >= 2) {
                bDone = false;
            }
        } while (bDone);
        return retStr;
    }


    /**
     * 用得到第三方的appcode发送post请求发送手机验证码
     * @param tel
     * @param httpSession
     * @return
     */
    public boolean sendCode(String tel , HttpSession httpSession){
//        String telCode = UUID.randomUUID().toString().substring(0,6);

        boolean b = false;
        String telCode = createRandom(true , 6);


        //调用发送短信第三方接口1.0
//        String host = "https://dfsns.market.alicloudapi.com";
//        String path = "/sms/smsSend";
//        String method = "POST";
//        String appcode = "b621d6f739744fbea56f8fbd5f131296";
//        Map<String, String> headers = new HashMap<>();
//        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
//        headers.put("Authorization", "APPCODE " + appcode);
//        //根据API的要求，定义相对应的Content-Type
//        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
//        Map<String, String> querys = new HashMap<String, String>();
//        Map<String, String> bodys = new HashMap<String, String>();
//        bodys.put("content", "code:"+telCode);
//        bodys.put("phone_number", tel);
//        bodys.put("template_id", "TPL_0000");

        //调用发送短信第三方接口2.0
        String host = "https://gyytz.market.alicloudapi.com";
        String path = "/sms/smsSend";
        String method = "POST";
        String appcode = "b621d6f739744fbea56f8fbd5f131296";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("mobile", tel);
        querys.put("param", "**code**:"+telCode+",**minute**:5");
        querys.put("smsSignId", "2e65b1bb3d054466b82f0c9d125465e2");
        querys.put("templateId", "908e94ccf08b4476ba6c876d13f084ad");
        Map<String, String> bodys = new HashMap<String, String>();


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            //发送post请求
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
//            System.out.println(response.toString());
            httpSession.setAttribute("telCode" , telCode);
            System.out.println("验证码：" + httpSession.getAttribute("telCode"));
            //将返回的内容转变为map的json串
            Map<String , String> map = (Map) JSON.parse(EntityUtils.toString(response.getEntity()));
            if (map.get("code").equals("0"))
                b = true;
            //获取response的body
//            System.out.println("发短信"+EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return b;
        }
    }

}
