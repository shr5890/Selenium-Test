package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.FileUtils;

public class CopyDirectory {
	public static void main(String args[]) throws IOException{
		File srcDir = new File("D:\\UX Refresh Workspace\\Cucumber_Implementation\\Cucumber_Implementation\\Results");
		File destDir = new File("D:\\UX Refresh Workspace\\Cucumber_Implementation\\Cucumber_Implementation\\Results\\1");
		FileUtils.copyDirectory(srcDir, destDir);
	}
	public void copyDirectory(File source, File target) throws IOException {
		if (!target.exists()) {
			target.mkdir();
		}
		for (String f : source.list()) {
			copy(new File(source, f), new File(target, f));
		}
	}
	public void copy(File sourceLocation, File targetLocation) throws IOException {
		if (sourceLocation.isDirectory()) {
			copyDirectory(sourceLocation, targetLocation);
		} else {
			copyFile(sourceLocation, targetLocation);
		}
	}
	public void copyFile(File source, File target) throws IOException {        
		try (
				InputStream in = new FileInputStream(source);
				OutputStream out = new FileOutputStream(target)
				) {
			byte[] buf = new byte[1024];
			int length;
			while ((length = in.read(buf)) > 0) {
				out.write(buf, 0, length);
			}
		}
	}
}
