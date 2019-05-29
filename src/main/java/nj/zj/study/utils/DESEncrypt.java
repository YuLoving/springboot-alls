package nj.zj.study.utils;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

/**
 *
 * @author Administrator
 *
 */
public class DESEncrypt {

	/**
	 * 鍔犲瘑
	 *
	 * @param key
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String encode(String key, String data) {

		try {
			DESKeySpec dks = new DESKeySpec(key.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			// key鐨勯暱搴︿笉鑳藉灏忎簬8浣嶅瓧鑺�
			Key secretKey = keyFactory.generateSecret(dks);
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(key.getBytes());// 鍚戦噺
			AlgorithmParameterSpec paramSpec = iv;
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, paramSpec);
			byte[] bytes = cipher.doFinal(data.getBytes("utf-8"));
			return Base64Utils.encodeToString(bytes);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 瑙ｅ瘑
	 *
	 * @param key
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	public static byte[] decode(String key, byte[] data) throws Exception {
		try {
			DESKeySpec dks = new DESKeySpec(key.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			// key鐨勯暱搴︿笉鑳藉灏忎簬8浣嶅瓧鑺�
			Key secretKey = keyFactory.generateSecret(dks);
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(key.getBytes());
			AlgorithmParameterSpec paramSpec = iv;
			cipher.init(Cipher.DECRYPT_MODE, secretKey, paramSpec);
			byte[] bs = cipher.doFinal(data);
			return bs;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * 鑾峰彇缂栫爜鍚庣殑鍊�(utf-8)
	 *
	 * @param key
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String decodeValueUTf8(String key, String data) {
		byte[] datas;
		String value = null;
		try {
			data = data.replaceAll(" ", "+");
			data = data.replaceAll("\n", "");
			data = data.replaceAll("\r", "");
			datas = decode(key, Base64Utils.decodeFromString(data));
			value = new String(datas, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return value;
	}

	public static void main(String[] args) throws Exception {
		String jiami = encode("TVEBUr2q", "name=xxxx&identityCard=xxxxxxx");

		System.out.println(jiami);
	}



}
