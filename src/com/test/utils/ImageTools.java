package com.test.utils;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;

public class ImageTools {

	/**
	 * 
	 * @param file 待剪切的文件
	 * @param x
	 *            图片左上角x坐标
	 * @param y
	 *            图片左上角y左边
	 * @param width
	 *            图片宽度
	 * @param height
	 *            图片高度
	 * @param destDir
	 *            文件生成目录
	 * @return
	 */
	public static File cutting(File file, int x, int y, int width, int height,
			File destDir) {
		// div的宽度和高度 与前端界面相关
			final int div_width=320;
			final int div_height = 220;
		try {
			String endName = file.getName();
			endName = endName.substring(endName.indexOf(".") + 1);
			Iterator<ImageReader> readers = ImageIO
					.getImageReadersByFormatName(endName);
			ImageReader reader = (ImageReader) readers.next(); // lllllloiu
			InputStream is = new FileInputStream(file);
			ImageInputStream iis = ImageIO.createImageInputStream(is);
			reader.setInput(iis, true);
			// 获取图片的市价大小
			int a = reader.getHeight(0);
			int b = reader.getWidth(0);
			ImageReadParam param = reader.getDefaultReadParam();
			// Rectangle rect1 = param.getSourceRegion();
			// 对图片进行按比例剪切 由于前台传来的参数是相对div而言的坐标
			x = (b) * x / div_width;
			y = (a) * y / div_height;
			width = (b) * width / div_width;
			height = (a) * height / div_height;
			Rectangle rect = new Rectangle(x, y, width, height);

			param.setSourceRegion(rect);
			BufferedImage bi = reader.read(0, param);
			System.out.println(file);
			File newFile = destDir;
			if (!newFile.exists())
				newFile.mkdirs();
			newFile = new File(newFile, file.getName());
			ImageOutputStream out = ImageIO
					.createImageOutputStream(new FileOutputStream(newFile));
			ImageIO.write(bi, endName, out);
			file = newFile;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file;
	}
	/**
	 * 按比例缩放图片文件 [width height]
	 * @param uploadedFile
	 * @param width
	 * @return
	 */
	public static int[] getImgFileSize(File file, int width) {
		int[] imgSize = new int[2];
		try {
			FileInputStream fis = new FileInputStream(file);
			BufferedImage bufferedImg = ImageIO.read(fis);
			imgSize[0] = bufferedImg.getWidth();
			imgSize[1] = bufferedImg.getHeight();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(imgSize[0]>width){
			imgSize[1] = (int)(width*imgSize[1]/imgSize[0]);
			imgSize[0] = width;
		}
		return imgSize;
	}
}