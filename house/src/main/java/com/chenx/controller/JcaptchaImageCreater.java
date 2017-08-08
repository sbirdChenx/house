package com.chenx.controller;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.awt.image.ImageFormatException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by ChenX on 2017/8/4.
 */
@Controller
public class JcaptchaImageCreater {
	@Autowired
	private ImageCaptchaService imageCaptchaService;

	@RequestMapping("/captcha")
	public void getCaptcha(HttpServletRequest request, HttpServletResponse response){
		try {
			ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
			String captchaId = request.getSession().getId();
			BufferedImage challenge = imageCaptchaService.getImageChallengeForID(captchaId, request.getLocale());

			response.setHeader("Cache-Control", "no-store");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0L);
			response.setContentType("image/jpeg");

			ImageIO.write(challenge, "jpeg", jpegOutputStream);
			byte[] captchaChallengeAsJpeg = jpegOutputStream.toByteArray();

			ServletOutputStream respOs = response.getOutputStream();
			respOs.write(captchaChallengeAsJpeg);
			respOs.flush();
			respOs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*byte[] captchaChallengeAsJpeg = null;
		// the output stream to render the captcha image as jpeg into
		ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
		try {
			// get the session id that will identify the generated captcha.
			// the same id must be used to validate the response, the session id
			// is a good candidate!
			String captchaId = request.getSession().getId();
			// call the ImageCaptchaService getChallenge method
			BufferedImage challenge = imageCaptchaService
					.getImageChallengeForID(captchaId, request
							.getLocale());
			JPEGImageEncoder jpegEncoder = JPEGCodec
					.createJPEGEncoder(jpegOutputStream);
			jpegEncoder.encode(challenge);
			captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
			// flush it in the response
			response.setHeader("Cache-Control", "no-store");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/jpeg");
			ServletOutputStream responseOutputStream = response
					.getOutputStream();
			responseOutputStream.write(captchaChallengeAsJpeg);
			responseOutputStream.flush();
			responseOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}*/


	}
}
