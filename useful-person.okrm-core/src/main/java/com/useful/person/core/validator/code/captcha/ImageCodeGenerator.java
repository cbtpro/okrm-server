package com.useful.person.core.validator.code.captcha;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import com.useful.person.core.properties.ImageCodeProperties;
import com.useful.person.core.properties.SecurityProperties;
import com.useful.person.core.validator.code.ValidatorCodeGenerator;

/**
 * 
 * @author peter
 *
 */
public class ImageCodeGenerator implements ValidatorCodeGenerator {

	@Autowired
	private SecurityProperties securityProperties;

	private static Random random = new Random();

	@Override
	public BufferedImage buildImageCode(ServletWebRequest request, String randomStr) {
		ImageCodeProperties imageCodeProperties = securityProperties.getCode().getImage();
		int width = ServletRequestUtils.getIntParameter(request.getRequest(), "width", imageCodeProperties.getWidth());
		int height = ServletRequestUtils.getIntParameter(request.getRequest(), "height",
				imageCodeProperties.getHeight());
		return getBufferedImage(width, height, randomStr);
	}

	@Override
	public String getRandomCodeBase64(ServletWebRequest request, String randomStr) throws IOException {
		ImageCodeProperties imageCodeProperties = securityProperties.getCode().getImage();
		int width = ServletRequestUtils.getIntParameter(request.getRequest(), "width", imageCodeProperties.getWidth());
		int height = ServletRequestUtils.getIntParameter(request.getRequest(), "height",
				imageCodeProperties.getHeight());
		BufferedImage image = getBufferedImage(width, height, randomStr);
		String base64Str = "";
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(image, "jpeg", bos);
		byte[] bytes = bos.toByteArray();
		Base64.Encoder encoder = Base64.getEncoder();
		base64Str = encoder.encodeToString(bytes);
		return base64Str;
	}
	/**
	 * 获取四个随机数
	 * 
	 * @return String
	 */
//	private String getRandomStr() {
//		String str = "";
//		for (int i = 0; i < 6; i++) {
//			int temp = random.nextInt(10);
//			str += temp;
//		}
//		return str;
//	}
	/**
	 * get a random color
	 * 
	 * @return Color
	 */
	private Color getRandomColor() {
		Random random = new Random();
		int r = random.nextInt(255);
		int g = random.nextInt(255);
		int b = random.nextInt(255);
		Color color = new Color(r, g, b);
		return color;
	}

	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		int maxRgb = 255;
		if (fc > maxRgb) {
			fc = maxRgb;
		}
		if (bc > maxRgb) {
			bc = maxRgb;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	public SecurityProperties getSecurityProperties() {
		return securityProperties;
	}

	public void setSecurityProperties(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}

	/**
	 * 生成验证码的图片对象
	 * @param width
	 * @param height
	 * @param fontSize
	 * @param randomStr
	 * @return BufferedImage
	 */
	private BufferedImage getBufferedImage(int width, int height, String randomStr) {
		int fontSize = 24;
//		Color randomColor = getRandColor(0, 0);
		// 构建缓冲图像
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		// 设置绘图上下文
		Graphics2D g = bi.createGraphics();
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		// 设置字体
		g.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, fontSize));
		g.setColor(getRandColor(0, 254));

		// 生成干扰线
		int linesCount = 10;
		for (int i = 0; i < linesCount; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(30);
			int yl = random.nextInt(20);
			g.setColor(this.getRandomColor());
			g.drawLine(x, y, x + xl, y + yl);
		}
		// 生成干扰噪点
		int noiseCount = 50;
		for (int i = 0; i < noiseCount; i++) {
			g.setColor(getRandomColor());
			g.drawOval(random.nextInt(width), random.nextInt(height), 0, 0);
		}
		// 打印验证码
		for (int i = 0; i < randomStr.length(); i++) {
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
			String ch = String.valueOf(randomStr.charAt(i));
			g.drawString(ch, 3 + (i * fontSize), 25);
		}
		g.dispose();
		return bi;
	}
}
