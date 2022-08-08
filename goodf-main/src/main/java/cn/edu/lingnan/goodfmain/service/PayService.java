package cn.edu.lingnan.goodf.service;

import cn.edu.lingnan.goodf.vo.PayVo;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

public interface PayService {

    String pay(String numId) throws Exception;

    String handlePayResult(PayVo vo );
}
