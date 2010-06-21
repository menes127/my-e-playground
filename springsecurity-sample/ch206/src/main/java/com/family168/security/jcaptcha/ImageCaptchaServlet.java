package com.family168.security.jcaptcha;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;

//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.apache.commons.lang.StringUtils;

import org.springframework.context.ApplicationContext;

import org.springframework.web.context.support.WebApplicationContextUtils;

import java.awt.image.BufferedImage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet generates CAPTCHA jpeg images based on the JCAPTCHA package. It's
 * configured via spring, and requires a ImageCaptchaService bean with the
 * id=imageCaptchaService
 *
 * @author Jason Thrasher
 * @author Lingo
 * @since 2007-04-07
 */
public class ImageCaptchaServlet extends HttpServlet {
    /**
     * serial.
     */
    private static final long serialVersionUID = -1L;

    /**
     * service name.
     */
    private String captchaServiceName = "imageCaptchaService";

    /**
     * 初始�?.
     *
     * @param servletConfig ServletConfig
     * @throws ServletException servlet异常
     */
    public void init(ServletConfig servletConfig) throws ServletException {
        if (StringUtils.isNotBlank(servletConfig.getInitParameter(
                        "captchaServiceName"))) {
            captchaServiceName = servletConfig.getInitParameter(
                    "captchaServiceName");
        }

        super.init(servletConfig);
    }

    /**
     * 处理get请求.
     *
     * @param httpServletRequest 请求
     * @param httpServletResponse 响应
     * @throws ServletException servlet异常
     * @throws IOException io异常
     */
    protected void doGet(HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse)
        throws ServletException, IOException {
        byte[] captchaChallengeAsJpeg = null;

        // the output stream to render the captcha image as jpeg into
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();

        try {
            // get the image captcha service defined via the SpringFramework
            ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
            Object bean = ctx.getBean(captchaServiceName);
            ImageCaptchaService imageCaptchaService = (ImageCaptchaService) bean;

            // get the session id that will identify the generated captcha.
            // the same id must be used to validate the response, the session id
            // is a good candidate!
            String captchaId = httpServletRequest.getSession().getId();

            // call the ImageCaptchaService getChallenge method
            BufferedImage challenge = imageCaptchaService.getImageChallengeForID(captchaId,
                    httpServletRequest.getLocale());
            ImageIO.write(challenge, "jpg", jpegOutputStream);

            // a jpeg encoder
            //JPEGImageEncoder jpegEncoder = JPEGCodec.createJPEGEncoder(jpegOutputStream);
            //jpegEncoder.encode(challenge);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);

            return;
        } catch (CaptchaServiceException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            return;
        }

        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();

        // flush it in the response
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");

        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }
}
