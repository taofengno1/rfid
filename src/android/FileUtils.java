package com.realidtek.rfid;

import java.io.*;
import java.util.*;

/**
 * �ļ�������.<br>
 * <br>
 * CreateDate: 2013-6-14<br>
 * Copyright: Copyright(c) 2013-6-14<br>
 * Company: ��������<br>
 * 
 * @since v1.0.0
 * @Description 2013-6-14::��˳::��������</br>
 */
public class FileUtils {
	/**
	 * �ж�SD���ϵ��ļ����Ƿ����
	 */
	public boolean isExist(String fileName) {
		File file = new File(fileName);
		return file.exists();
	}

	public static boolean copy(String sName, String dName) {
		boolean re = false;
		try {
			FileInputStream in = new java.io.FileInputStream(sName);
			createFile(dName);
			FileOutputStream out = new FileOutputStream(dName);
			byte[] bt = new byte[1024];
			int count;
			while ((count = in.read(bt)) > 0) {
				out.write(bt, 0, count);
			}
			in.close();
			out.close();
			re = true;
		} catch (Exception ex) {
			re = false;
			ex.printStackTrace();
		}

		return re;
	}

	public static File createFile(String fileName) {
		File file = new File(fileName);
		System.out.println("file---->" + file);
		try {
			if (!file.exists())
				file.createNewFile();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return file;
	}

	/**
	 * �ж�·���Ƿ����.<br>
	 * �������򴴽�<br>
	 * 
	 * @param path
	 * @return
	 * @Description 2013-7-1::��˳::�����˷���</br>
	 */
	public static boolean pathExists(String path) {
		boolean re = true;

		try {
			File pathFile = new File(path);
			if (!pathFile.exists()) {
				re = pathFile.mkdirs();
			}
		} catch (Exception e) {
			e.printStackTrace();
			re = false;
		}

		return re;
	}

	/**
	 * ɾ���ļ����ļ���
	 * 
	 * @param fileName
	 * @return
	 */
	public void deleteFile(String objName) {
		File file = new File(objName);
		delete(file);
	}

	/**
	 * ͨ���ݹ鷽ʽɾ���ļ����ļ���
	 * 
	 * @param file
	 */
	private void delete(File file) {
		if (file.isDirectory()) {

			File[] files = file.listFiles();
			for (File f : files) {
				delete(f);
			}
		} else {
			file.delete();
		}
	}

	public File writeTextFile(String fileName, String context, boolean isAppend) {

		try {
			File file = createFile(fileName);
			OutputStream output = new FileOutputStream(file, isAppend);
			output.write(context.getBytes());
			output.flush();
			output.close();
			return file;
		} catch (Exception e) {
		}
		return null;
	}

	public Byte[] readFile(String fileName) {
		File f = new File(fileName);
		InputStream file;
		try {
			List<Byte> bufferList = new ArrayList<Byte>();
			file = new FileInputStream(f);
			// BufferedReader br = new BufferedReader(new
			// InputStreamReader(file));
			// byte bt = -1;
			// bt = (byte) file.read();

			int length = 0;
			byte[] buffer = new byte[1024];

			while ((length = file.read(buffer)) > 0) {
				for (int i = 0; i < length; i++) {
					bufferList.add(Byte.valueOf(buffer[i]));
				}
			}

			Byte[] bufArray = new Byte[bufferList.size()];
			return bufferList.toArray(bufArray);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}