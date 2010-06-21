package com.family168.springsecuritybook.ch120;

import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.mvc.AbstractController;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;


public class TestScopeController extends AbstractController {
    protected ModelAndView handleRenderRequestInternal(RenderRequest request,
        RenderResponse response) throws Exception {
        ModelAndView mav = new ModelAndView("testScope");
        TestBean requestTestBean = (TestBean) getApplicationContext()
                                                  .getBean("requestTestBean");
        TestBean sessionTestBean = (TestBean) getApplicationContext()
                                                  .getBean("sessionTestBean");
        TestBean globalSessionTestBean = (TestBean) getApplicationContext()
                                                        .getBean("globalSessionTestBean");
        mav.addObject("requestTestBean", requestTestBean);
        mav.addObject("sessionTestBean", sessionTestBean);
        mav.addObject("globalSessionTestBean", globalSessionTestBean);

        return mav;
    }
}
