package gov.loc.repository.bagit.writer.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import gov.loc.repository.bagit.BagFile;
import gov.loc.repository.bagit.utilities.TempFileHelper;

public class FileSystemHelper {

	private static final int BUFFERSIZE = 65536;
		
	public static void write(BagFile bagFile, File file) {
		try {
			File parentDir = file.getParentFile();
			if (! parentDir.exists()) {
				FileUtils.forceMkdir(parentDir);
			}
			
			FileOutputStream out = new FileOutputStream(TempFileHelper.getTempFile(file));
			InputStream in = bagFile.newInputStream();
			try {
				byte[] dataBytes = new byte[BUFFERSIZE];
				int nread = in.read(dataBytes);
				while (nread > 0) {
					out.write(dataBytes, 0, nread);
				    nread = in.read(dataBytes);
				}
			} finally {
				IOUtils.closeQuietly(in);
				IOUtils.closeQuietly(out);
			}
			TempFileHelper.switchTemp(file);
		}
		catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		
	}
		
}