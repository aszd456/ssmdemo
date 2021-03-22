package com.springmvc.controller;

import com.springmvc.exception.BusinessException;
import com.springmvc.exception.ErrorEnum;
import com.springmvc.util.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName TestExceptionController
 * @Description TODO
 * @Author zhouliansheng
 * @Date 2021/3/22 11:18
 * @Version 1.0
 **/
@Controller
@RequestMapping("test")
public class TestExceptionController {

    @GetMapping("/query")
    @ResponseBody
    public Object query() {
        BusinessException be = new BusinessException(ErrorEnum.NO_PERMISSION.getErrorCode(),
                ErrorEnum.NO_PERMISSION.getErrorMsg());
        return AjaxResult.defineError(be);
    }
}
