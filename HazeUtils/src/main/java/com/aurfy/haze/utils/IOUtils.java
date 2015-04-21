package com.aurfy.haze.utils;

import static com.aurfy.haze.utils.StringUtils.formatMessage;

import java.awt.image.BufferedImage;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipFile;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * IO Utility.
 * 
 */
public final class IOUtils extends org.apache.commons.io.IOUtils {

	private static final Logger log = LoggerFactory.getLogger(IOUtils.class);
	private static final MimetypesFileTypeMap mimetypesFileTypeMap = getMimetypesFileTypeMap();
	private static final int DEFAULT_BUFFER_SIZE = 2048;
	public static final char FILE_PATH_SEPARATOR = '/';

	public static String readFully(InputStream in) throws IOException {
		return readFully(in, StringUtils.DEFAULT_ENCODING);
	}

	public static String readFully(InputStream in, String encoding) throws IOException {
		try {
			StringBuilder sb = new StringBuilder();
			byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
			int length;
			while ((length = in.read(buffer)) != -1) {
				sb.append(new String(buffer, 0, length, encoding));
			}
			return sb.toString();
		} finally {
			close(in);
		}
	}

	public static void close(Closeable c) {
		if (c != null) {
			try {
				c.close();
			} catch (IOException e) {
				// ignore
				if (log.isWarnEnabled()) {
					log.warn("Failed to close " + c.getClass().getSimpleName(), e);
				}
			}
		}
	}

	public static void close(ZipFile zipFile) {
		if (zipFile != null) {
			try {
				zipFile.close();
			} catch (IOException e) {
				// ignore
				if (log.isWarnEnabled()) {
					log.warn("Failed to close zip file", e);
				}
			}
		}
	}

	/**
	 * List all the files in the given folder, sub-folders are not included.
	 * 
	 * @throws IOException
	 */
	public static List<String> listFiles(String folder) throws IOException {
		File file = new File(folder);
		if (!file.canRead() || !file.isDirectory()) {
			throw new IOException(formatMessage("''{0}'' is not a folder or is not readable", folder));
		}
		File[] files = file.listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				return pathname.isFile();
			}
		});
		if (files == null) {
			return new ArrayList<String>(0);
		} else {
			List<String> result = new ArrayList<String>(files.length);
			for (File f : files) {
				result.add(f.getName());
			}
			return result;
		}
	}

	public static void saveFile(InputStream in, String targetFile) throws IOException {
		FileOutputStream out = null;
		byte[] buffer = new byte[1024];
		int n = -1;

		try {
			File file = new File(targetFile);
			file.getParentFile().mkdirs();
			out = new FileOutputStream(file);
			while ((n = in.read(buffer)) != -1) {
				out.write(buffer, 0, n);
			}
			out.flush();
		} finally {
			close(out);
		}
	}

	public static void saveFile(byte[] content, String targetFile) throws IOException {
		FileOutputStream out = null;
		try {
			File file = new File(targetFile);
			file.getParentFile().mkdirs();
			out = new FileOutputStream(file);
			out.write(content);
			out.flush();
		} finally {
			close(out);
		}
	}

	public static FileInfo saveFile(byte[] content, String rootFolder, String relativePath) throws IOException {
		FileInfo info = createFileInfo(rootFolder, relativePath, content.length);
		FileOutputStream out = null;
		try {
			File file = new File(info.getFullPath());
			file.getParentFile().mkdirs();
			out = new FileOutputStream(file);
			out.write(content);
			out.flush();
			updateLastModified(info.getFullPath(), info.getCreateTime());
			return info;
		} finally {
			close(out);
		}
	}

	public static FileInfo saveFile(InputStream in, String rootFolder, String relativePath, long size)
			throws IOException {
		FileInfo info = createFileInfo(rootFolder, relativePath, size);
		saveFile(in, info.getFullPath());
		updateLastModified(info.getFullPath(), info.getCreateTime());
		return info;
	}

	public static boolean saveImage(BufferedImage image, FileInfo fileInfo) throws IOException {
		File file = new File(fileInfo.getFullPath());
		file.getParentFile().mkdirs();
		boolean result = ImageIO.write(image, fileInfo.getSuffix(), new FileOutputStream(fileInfo.getFullPath()));
		return updateLastModified(fileInfo.getFullPath(), fileInfo.getCreateTime()) && result;
	}

	public static boolean updateLastModified(String fullPath, Date newDate) {
		return new File(fullPath).setLastModified(newDate.getTime());
	}

	public static boolean deleteFile(String fullPath) {
		return new File(fullPath).delete();
	}

	public static FileInfo createFileInfo(String rootFolder, String relativePath, long size) {

		FileInfo info = new FileInfo();

		String srcFileName = rootFolder + relativePath;
		String suffix = getSuffix(srcFileName);
		String fileName = getFileName(relativePath);

		info.setId(RandomStringUtils.randomAlphanumeric(16));
		info.setName(fileName);
		info.setSuffix(suffix);
		info.setSize(size);
		info.setMimeType(mimetypesFileTypeMap.getContentType(srcFileName));
		info.setCreateTime(new Date());
		info.setRelativePath(relativePath);
		info.setFullPath(srcFileName);

		return info;
	}

	public static FileInfo createFileInfo(String fullPath) throws IOException {

		File f = new File(fullPath);
		if (!f.exists()) {
			throw new FileNotFoundException(fullPath);
		}

		FileInfo info = new FileInfo();

		String suffix = getSuffix(fullPath);
		String fileName = getFileName(fullPath);

		info.setId(RandomStringUtils.randomAlphanumeric(16));
		info.setName(fileName);
		info.setSuffix(suffix);
		info.setSize(f.length());
		info.setMimeType(mimetypesFileTypeMap.getContentType(fullPath));
		info.setCreateTime(new Date());
		info.setRelativePath(null);
		info.setFullPath(fullPath);

		return info;
	}

	public static String getFileName(String filePath) {
		if (StringUtils.isBlank(filePath)) {
			return filePath;
		}
		String newPath = convertToZipEntryPath(filePath);
		int index = newPath.lastIndexOf(FILE_PATH_SEPARATOR);
		if (index != -1) {
			return newPath.substring(index + 1);
		} else {
			return newPath;
		}
	}

	public static String getSuffix(String filename) {
		if (StringUtils.isEmpty(filename)) {
			return "";
		}
		int index = filename.lastIndexOf('.');
		if (index != -1) {
			return filename.substring(index + 1);
		} else {
			return "";
		}
	}

	private static MimetypesFileTypeMap getMimetypesFileTypeMap() {
		MimetypesFileTypeMap result = new MimetypesFileTypeMap();
		return result;
	}

	public static String convertToZipEntryPath(String currentPath) {
		return currentPath.replace('\\', FILE_PATH_SEPARATOR);
	}
}
