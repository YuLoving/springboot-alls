package nj.zj.study.utils;

import java.io.UnsupportedEncodingException;  

import java.security.InvalidKeyException;  
import java.security.NoSuchAlgorithmException;  
import javax.crypto.BadPaddingException;  
import javax.crypto.Cipher;  
import javax.crypto.IllegalBlockSizeException;  
import javax.crypto.NoSuchPaddingException;  
import javax.crypto.spec.SecretKeySpec; 
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
/**
 * 
 * @Description:TODO
 * AES加密、解密以及base64编码、解码的工具类
 * @author: ZTY 
 * @date:   2019年4月8日 下午2:42:01
 */
public class Jiami {
	 private  static final String AES="AES";  
	 private  static final String UTF8="UTF-8";  
	    
	   /**
	    * AES加密
	    * @param content
	    * @param pkey  密钥
	    * @return
	    * @throws DecoderException
	    */
	    private static byte[] encrypt(String content, String pkey) throws DecoderException {  
	        try {  
	            String private_key=pkey;
	            byte[] encodeFormat=null;
				try {
					//秘钥 Hex解码为什么秘钥要进行解码，因为秘钥是某个秘钥明文进行了Hex编码后的值，所以在使用的时候要进行解码
					encodeFormat = Hex.decodeHex(private_key.toCharArray());
				} catch (DecoderException e) {
					e.printStackTrace();
				}
	            SecretKeySpec key = new SecretKeySpec(encodeFormat, AES);  
	            // Cipher对象实际完成加密操作  
	            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");    
	            // 加密内容进行编码  
	            byte[] byteContent = content.getBytes(UTF8);  
	            // 用密匙初始化Cipher对象  
	            cipher.init(Cipher.ENCRYPT_MODE, key); 
	            // 正式执行加密操作  
	            byte[] result = cipher.doFinal(byteContent);  
	            return result;  
	        } catch (NoSuchAlgorithmException e) {  
	            e.printStackTrace();  
	        } catch (NoSuchPaddingException e) {  
	            e.printStackTrace();  
	        } catch (InvalidKeyException e) {  
	            e.printStackTrace();  
	        } catch (UnsupportedEncodingException e) {  
	            e.printStackTrace();  
	        } catch (IllegalBlockSizeException e) {  
	            e.printStackTrace();  
	        } catch (BadPaddingException e) {  
	            e.printStackTrace();  
	        }  
	        return null;  
	    }  
	  
	    /**
	     * AES解密
	     * @param contents
	     * @param password
	     * @return
	     * @throws DecoderException
	     */
	    private static byte[] decrypt(String contents, String password) throws DecoderException {  
	        try { 
	        	//密文使用Hex解码
	        	byte[]content = Hex.decodeHex(contents.toCharArray());  
	        	//秘钥 Hex解码为什么秘钥要进行解码，因为秘钥是某个秘钥明文进行了Hex编码后的值，所以在使用的时候要进行解码
	            byte[] encodeFormat = Hex.decodeHex(password.toCharArray()); 
	            SecretKeySpec key = new SecretKeySpec(encodeFormat, AES);  
	            // Cipher对象实际完成加密操作  
	            Cipher cipher = Cipher.getInstance(AES);  
	            // 用密匙初始化Cipher对象  
	            cipher.init(Cipher.DECRYPT_MODE, key);  
	            // 正式执行解密操作  
	            byte[] result = cipher.doFinal(content);  
	            return result;  
	        } catch (NoSuchAlgorithmException e) {  
	            e.printStackTrace();  
	        } catch (NoSuchPaddingException e) {  
	            e.printStackTrace();  
	        } catch (InvalidKeyException e) {  
	            e.printStackTrace();  
	        } catch (IllegalBlockSizeException e) {  
	            e.printStackTrace();  
	        } catch (BadPaddingException e) {  
	            e.printStackTrace();  
	        }  
	        return null;  
	    }  
	  
	    /**
	     * Aes加密
	     * @param context 明文
	     * @param private_key 秘钥
	     * @return 
	     * @throws DecoderException
	     */
	   public static String  encryption(String context,String private_key) throws DecoderException{
		   //加密后的明文也就变成了密文
		   byte[] encryptResult = encrypt(context, private_key);  
		   //密码文Hex编码
	       String encryptResultStr = Hex.encodeHexString(encryptResult);
		   return encryptResultStr;
	   }
	   
	   /**
	    * Aes解密
	    * @param context 密文
	    * @param private_key 秘钥
	    * @return
	    * @throws DecoderException
	    * @throws UnsupportedEncodingException
	    */
	   public static String decryption(String context,String private_key) throws DecoderException, UnsupportedEncodingException{
		  //这里的密文解密前先进行了Hex解码
		   byte[] decryptResult = decrypt(context, private_key); 
		   String result = new String(decryptResult, UTF8); 
		   return result;
	   }
	  
	   
	   /**
	    * base64 编码、解码
	    * 
	    */
	  
	   public static String encode(byte[] binaryData) throws UnsupportedEncodingException {  
	        return new String(Base64.encodeBase64(binaryData), "UTF-8");  
	    }  
	      
	    public static byte[] decode(String base64String) throws UnsupportedEncodingException {  
	        return Base64.decodeBase64(base64String.getBytes("UTF-8"));  
	    }  
	      
	      

	    public static void main(String[] args) throws UnsupportedEncodingException, DecoderException { 
	    	/*//加密内容
	        String content = "123456787654321";  
	        //AES加密解密秘钥
	        String password = "37f3a0703bae26bba6e8c23849178043";
	        // 加密  
	        System.out.println("加密前：" + content);  
	        // 调用加密方法
	        String encryptResultStr = encryption(content, password);  
	        System.out.println("AES加密后：" + encryptResultStr);  
	        //通过base64编码
	        String encodeStr = encode(encryptResultStr.getBytes());
	        System.out.println("base64编码后：" + encodeStr);  
	        */
	    	 String password ="Yucb6Lkt";
	    	
	        //通过base64解码
	        byte[] decodeStr = decode("W3fQXxtIX2MiULrVbtpP0oNgRHqwBEAvE9eFsXpdtzOhOif/5s2U3EJ7Qs5IE+SX");
	        String newdecodeStr = new String(decodeStr);
	        System.out.println("base64解码后：" +newdecodeStr);
	        
	        
	        // 调用解密方法
	        String result  = decryption(newdecodeStr, password);  
	        // 解密内容进行解码  
	        System.out.println("AES解密后：" + result); 
	        
	        
	        
	    }  
}
