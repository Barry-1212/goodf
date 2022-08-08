package cn.edu.lingnan.goodf.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;


public class Pay {

    //↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    private static final String app_id = "2021000121620886";

    // 商户私钥，您的PKCS8格式RSA2私钥
    private static final String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCfLUNBrayEZWJ6q/R92fJzGMYsT7DZYuiiMcOrQ5esQwNs7UlP7F3alSqV4D49TZGsfvu6BlH1IDexQ/karz6h/EYDubrrcJivjYd6+j/BwiNhq6PzzM/edbCT22Sx2/+UI6fBHtBT0TfT+W2IEwKS1LH0GfEc46UeBOsRjMswqVUDh+8BYWPusXdR/cmXAV/SPV5NLCL4GFXksd42nWYyOCjlkFJNuy0CW346t6O0qm/ZEqpAjDbksNiW70JHvdvZv4t1Tk4uyai6KO9AyVo5xr4CNQfyKa0r+DhlFSFFVJygqWQhaHV5srS5HtFFTIujgMqUcnq5e6Hj5+nxMpa5AgMBAAECggEAIeu1X3OxRe2NFPM8AO3HGvv2j07UMx8+0AGm3wJiehU1z6qpatzGC2SqVJIa8VlsZ8QPxM6//GrHM4F2vlcGE+L80KJt0OqHvzF745scpx/GBnRbVgMZcSDHZlrklHp7dog6oY79ej8llIic7NiQPdYRriExHqTsdP87UNweGNpGvNVKWubusW+6spYhWZWosnULBfOwfSI0MZTQsJFQHcCW9vm2hHb4Id3AjSOtyhAa7Jsye06A37bJQWRP/h/Pmk1vlYfq/fp4u9xuuISvz9iOr9v3yBMN5YykkNYQAHntZ7MZrBilBA1vlBAaVx9f8/H3WlsoglUqpSGGH60MYQKBgQDZQXspC9DNGJVO4kO31msTWTY6AN24siJ84dmAeJCn5psZR4WN7J8/aTN5o25653r8OuohmMlE+Uc8Uz5y0mP/WZvRV1kyfges3DFgfWmjygVYCrFknPplkKEXT+mJGA3qLrrHPtQGrP02DBs7PqLOXI5ZAweY9B7WdXqUf6ZLTQKBgQC7kEO0nYT5qoyz/zzD2qJa127v5Fxf64GcFdp8zhtlHhrd1yWJ3bDU+9kwtrx55jCR7EeQth1F55soVz0Vp7nhTQ1whZGs54nOldWi1e9Xn3Gm6+rhZexWlwEoiMkuseDbQUY+K07w27HfSQv5URbI1nAEwP5Qfq/i0LXvZT7LHQKBgDQCWgKMgpLmxK+auQ1kdkOugq3GEU4qztjIlHIN6stXCGA34AAG2mSqwZjoCAXCLo39aJlcYypw5p3Ja9S/CxNRF105JPBSfXHjUKrBuy1rqnXWMXtdnQ/9XKMhDU41hPc/8pTKNKXYedGbelVnfEAcpjMm54W1Q0Ch5RNxr5StAoGAD0zLWRZoXH1fEOfHKyrQh5x/Pi5goqpxXiwiMIAXl/4GrwX31/EvlZAdvW2Knl76o0yk4dcZODSWYYoMP5H5BbnlWMJsibAxe5qZXLka1wj4mmcwN8a6qq2SuJ2/24FDGF+QvU2HGPqy3cX1phbJRwTHCnw7wpEzA8uGq8qUfrkCgYEAjMO18dBIFehzQPzUFuVl8OtzBBTFf/oZcCcrt3MM3EsWIcQ1ju83kzr0QfRNatsnhjz488RuAV3b3EquCz2Q+3FJDb6LcV09X/V7AVKWyTC8PqyA5V+4CY14wZ7MUhsIEtpK29F9IoAGAO+Lx+Su4gWwt3GXndVvbCMt8q0aDAc=";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    private static final String format = "json";
    private static final String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAupOsbtVlyhQrLcKXBlvfq49nwxwGpaktoqydqFiqDjw3CBIf2jk7yzvXZBzcFxfqSsjfDR6fY3dSmZRdU/9gOGduI7Zx2qbCLMV3sjn5ZWJIvbHY1JU9driVVCkm/SfZISJx58TOYFHEWZwDWgIOdiUFEoWok8i5DlLr+ItFYerHGRLVZ4x2FDDpxnJUnDQAg/+Yz082xh7P2VzxVsMUZFCz5VILpGp3YiPO4Q15w0zdZcT9F656L0RU8ASr49IphZOPZvZstxVTikG6zSADamtEhauqieHNRKfGmC6w3Nc+/ujU0ORI0GgE6x/MSecmCZuctVz0Ro2/pawI1iC5BQIDAQAB";
    /**
     * 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
     * natapp内网穿透
     * 1、先启动natapp，运行natapp -authtoken=682200e69a5441db。
     * 2、将Forwarding里的前面的公网IP复制到notify_url的/handlePayResult与引号之间。
     * 3、该公网IP只能natapp启动的过程中生效。
     */
    private static final String notify_url = " http://3aqbei.natappfree.cc/handlePayResult";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    private static final String return_url = "http://localhost:10000/returnInfo";

    // 签名方式
    private static final String sign_type = "RSA2";

    // 字符编码格式
    private static final String charset = "utf-8";

    // 支付宝网关
    private static final String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    public static String zhifubPay(String money  ,  String num) throws Exception{



        //订单
//        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//        String user = UUID.randomUUID().toString().replace("-", "").toUpperCase();
//        String num = time + user;


        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl,app_id,merchant_private_key,format,charset,alipay_public_key,sign_type);
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setNotifyUrl(notify_url);
        request.setReturnUrl(return_url);
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", num);
        bizContent.put("total_amount", money);
        bizContent.put("subject", "预约支付");
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");
//bizContent.put("time_expire", "2022-08-01 22:00:00");

//// 商品明细信息，按需传入
//JSONArray goodsDetail = new JSONArray();
//JSONObject goods1 = new JSONObject();
//goods1.put("goods_id", "goodsNo1");
//goods1.put("goods_name", "子商品1");
//goods1.put("quantity", 1);
//goods1.put("price", 0.01);
//goodsDetail.add(goods1);
//bizContent.put("goods_detail", goodsDetail);

//// 扩展信息，按需传入
//JSONObject extendParams = new JSONObject();
//extendParams.put("sys_service_provider_id", "2088511833207846");
//bizContent.put("extend_params", extendParams);

        request.setBizContent(bizContent.toString());
            String response = alipayClient.pageExecute(request).getBody();
//            System.out.println("支付宝返回数据" + response);
            return response;

    }


}
