package nj.zj.study.utils;

import lombok.Data;

/**
 * 封装 FastDFS 上传工具类
 * 
 * @author zty
 *
 */
@Data
public class FastDFSFile {
	private String name;
	private byte[] content;
	private String ext;
	private String md5;
	private String author;

	/**
	 * 构造方法
	 */

	public FastDFSFile(String name, byte[] content, String ext, String height, String width, String author) {
		super();
		this.name = name;
		this.content = content;
		this.ext = ext;
		this.author = author;
	}

	public FastDFSFile(String name, byte[] content, String ext) {
		super();
		this.name = name;
		this.content = content;
		this.ext = ext;

	}

}
