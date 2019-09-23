package com.useful_person.core.validator.code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import net.bytebuddy.utility.RandomString;

@RestController
public class ValidatorCodeController {

	public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";
	
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

	private static Random random = new Random();


	@GetMapping("/code/captcha.jpg")
	public void getImageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ImageCode imageCode = buildImageCode(request);
		sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode.getCode());
		ImageIO.write(imageCode.getImage(), "jpeg", response.getOutputStream());
	}

	private ImageCode buildImageCode(HttpServletRequest request) {
		int width = 400;
		int heigth = 30;
		int font_size = 16;
		String randomStr = RandomString.make(8);
//		Color randomColor = getRandColor(0, 0);
		// 构建缓冲图像
		BufferedImage bi = new BufferedImage(100, 30, BufferedImage.TYPE_INT_BGR);
		// 设置绘图上下文
		Graphics2D g = bi.createGraphics();
		g.setColor(getRandColor( 200 , 250 ));
		g.fillRect(0, 0, width, heigth);
		// 设置字体
		g.setFont(new Font("宋体", Font.BOLD, font_size));
		g.setColor(getRandColor( 160 , 200 ));

		for (int i = 0; i < 30; i++) {
			int x1 = random.nextInt(80);
			int y1 = random.nextInt(25);
			int x2 = random.nextInt(80);
			int y2 = random.nextInt(25);
			//g.setColor(this.getRandomColor());
			g.drawLine(x1, y1, x2, y2);
		}
		// 生成干扰线
		for (int i = 0; i < 500; i++) {
			g.setColor(getRandomColor());
			g.drawOval(random.nextInt(80), random.nextInt(25), 0, 0);
		}
		g.setColor( new  Color( 20 + random.nextInt( 110 ), 20 + random.nextInt( 110 ), 20 + random.nextInt( 110 )));
		g.drawString(randomStr, 10, 18);
		g.dispose();
		return new ImageCode(bi, randomStr, 120);
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
	 * @return Color
	 */
	private Color getRandomColor() {
		Random random = new Random();
		int R = random.nextInt(255);
		int G = random.nextInt(255);
		int B = random.nextInt(255);
		Color color = new Color(R, G, B);
		return color;
	}

	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
}
