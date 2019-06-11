package nj.zj.study.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.servlet.Servlet;
import javax.servlet.ServletOutputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**  

* <p>Description: 谷歌二维码的工具类</p>  

* @author ZTY  

* @date 2019年6月11日  

*/
@Service
public class QRCodeService {
	private static final Logger logger=LoggerFactory.getLogger(QRCodeService.class);
	
	public String crateQRCode(String content, int width,int height) throws IOException{
		
		String resultImage="";
		if(StringUtils.isNotBlank(content)) {
			ServletOutputStream stream=null;
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			@SuppressWarnings("rawtypes")
			HashMap<EncodeHintType, Comparable> hints = new HashMap<>();
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); // 指定字符编码为“utf-8”
			hints.put(EncodeHintType.ERROR_CORRECTION,ErrorCorrectionLevel.M);// 指定二维码的纠错等级为中级
			hints.put(EncodeHintType.MARGIN,2); // 设置图片的边距
			
			try {
				QRCodeWriter writer = new QRCodeWriter();
				BitMatrix bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, width, height,hints);
				BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
				ImageIO.write(bufferedImage, "png", os);
				/**
				 * 原生转码前面没有 data:image/png;base64 这些字段，
				 * 返回给前端是无法被解析，可以让前端加，也可以在下面加上
                 */
				byte[] encodeBase64 = Base64.encodeBase64(os.toByteArray());
				resultImage = new String("data:image/png;base64," +new String(encodeBase64));
				return resultImage;
			}catch (Exception e) {
				logger.error("=========二维码工具类报错："+e.getMessage());
				e.printStackTrace();
			} finally {
				//不管怎样，流一定要关闭
				 if (stream != null) {
	                    stream.flush();
	                    stream.close();
	                }

			}
			
			
		}
		 return null;

	}
}
