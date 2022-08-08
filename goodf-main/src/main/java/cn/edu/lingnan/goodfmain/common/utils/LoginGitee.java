package cn.edu.lingnan.goodf.common.utils;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

public class LoginGitee {


    /**
     * 用得到的code发送post请求获取access_token
     * @param code
     * @return
     */
    public String sendPostGetAccessToken(String code){

        Map<String, String> headers = new HashMap<>();
        HashMap<String, String> bodys = new HashMap<>();
        bodys.put("grant_type" , "authorization_code");
        bodys.put("code" , code);
        bodys.put("client_id" , "212d67dbcdbc6ce1c7cee277ade5c50df6d206605a4985a564c2894eae92b23b");
        bodys.put("redirect_uri" , "http://localhost:10000/web/loginGitee");
        bodys.put("client_secret" , "48762dc5f37ffb92ce9bdb7f1e67fff2a1baa0e2f3ded1f26e1005441be4ab08");
        Map maps = null;
        try {
            HttpResponse response = HttpUtils.doPost("https://gitee.com","/oauth/token", "POST",headers,new HashMap<>(),bodys);
            maps = (Map) JSON.parse(EntityUtils.toString(response.getEntity()));
//            System.out.println(maps.get("access_token"));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (!"".equals(maps.get("access_token")))
                return getGiteeId(maps);
            return null;
        }
    }

    /**
     * 用access_token发送get请求获取用户信息
     * @param maps
     * @return
     */
    public String getGiteeId(Map maps){
        Map<String, String> headers = new HashMap<>();
        HashMap<String, String> bodys = new HashMap<>();
        bodys.put("access_token" , (String) maps.get("access_token"));
        Map map = null;
        try {
            HttpResponse response = HttpUtils.doGet("https://gitee.com","/api/v5/user", "GET",headers,bodys);
            map = (Map) JSON.parse(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (!"".equals(map.get("id")))
                return map.get("id")+"";
            return null;
        }
    }
}
