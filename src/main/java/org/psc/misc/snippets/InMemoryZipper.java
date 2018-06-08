package org.psc.misc.snippets;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InMemoryZipper {
	private static final Logger LOGGER = LoggerFactory.getLogger(InMemoryZipper.class);

	public static void main(String[] args) throws IOException {
		Map<String, byte[]> archiveContent = new HashMap<>();
		
		archiveContent.put("file1.txt", "testetste".getBytes());
		archiveContent.put("file2.txt", "someValue abcahsjda 12321".getBytes());
		archiveContent.put("file3.log", "lggogogoglgogloggogolg\noglggo 1sdfsdfsd\nENDOFFILE".getBytes());
		
		InMemoryZipper inMemoryZipper = new InMemoryZipper();
		ByteArrayOutputStream out = inMemoryZipper.buildArchive(archiveContent);
		FileOutputStream fos = new FileOutputStream("zipped.zip");
		fos.write(out.toByteArray());
		fos.close();
	}

	public ByteArrayOutputStream buildArchive(Map<String, byte[]> content) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		ZipOutputStream zipOutputStream = new ZipOutputStream(out);

		content.entrySet().stream().forEach(e -> {
			ZipEntry entry = new ZipEntry(e.getKey());
			try {
				zipOutputStream.putNextEntry(entry);
				zipOutputStream.write(e.getValue());
				zipOutputStream.closeEntry();
			} catch (IOException e1) {
				LOGGER.error(e1.getMessage());
			}
		});
		
		zipOutputStream.close();
		return out;
	}
}
