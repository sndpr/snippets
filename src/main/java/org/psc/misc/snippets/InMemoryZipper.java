package org.psc.misc.snippets;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPSClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSessionContext;
import javax.net.ssl.SSLSocket;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class InMemoryZipper {
    private static final Logger LOGGER = LoggerFactory.getLogger(InMemoryZipper.class);

    public static void main(String[] args) throws IOException {
        Map<String, byte[]> archiveContent = new HashMap<>();

        archiveContent.put("file1.txt", "testetste".getBytes());
        archiveContent.put("file2.txt", "someValue abcahsjda 12321".getBytes());
        archiveContent.put("file3.log", "lggogogoglgogloggogolg\noglggo 1sdfsdfsd\nENDOFFILE".getBytes());

        InMemoryZipper inMemoryZipper = new InMemoryZipper();
        ByteArrayOutputStream out = inMemoryZipper.buildArchive(archiveContent);

        byte[] zippedContent = out.toByteArray();
        out.close();
        /*
         * FileOutputStream fos = new FileOutputStream("zipped.zip");
         * fos.write(zippedContent); fos.close();
         */
        //inMemoryZipper.transferFile(zippedContent);
        inMemoryZipper.securelyTransferFile(zippedContent);
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

    public void transferFile(byte[] content) throws IOException {
        final FTPClient ftpClient = new FTPClient();
        ftpClient.connect("localhost", 21);

        ftpClient.login("user", "123");
        LOGGER.info(ftpClient.printWorkingDirectory());
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        ftpClient.enterLocalPassiveMode();

        InputStream input = new ByteArrayInputStream(content);

        boolean transferredSuccessfully = ftpClient.storeFile("zipped.zip", input);
        input.close();
        ftpClient.logout();
        ftpClient.disconnect();

        /*
         * File f = new File("zipped0.zip"); FileOutputStream fos = new
         * FileOutputStream(f); fos.write(content); fos.close(); InputStream input = new
         * FileInputStream(f); boolean transferredSuccessfully =
         * ftpClient.storeFile("/zipped.zip", input);
         * LOGGER.info(String.valueOf(ftpClient.getReplyCode()));
         * 		input.close();
         */
        if (transferredSuccessfully) {
            LOGGER.info("transfer OK");
        } else {
            LOGGER.warn("something went wrong");
        }

    }

    public void securelyTransferFile(byte[] content) throws IOException {
        final TlsSessionResumptionFtpClient ftpClient = new TlsSessionResumptionFtpClient();
        ftpClient.connect("localhost", 21);
        ftpClient.login("user", "123");
        LOGGER.info(ftpClient.printWorkingDirectory());
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        ftpClient.execPBSZ(0);
        ftpClient.execPROT("P");
        ftpClient.enterLocalPassiveMode();

        InputStream input = new ByteArrayInputStream(content);

        boolean transferredSuccessfully = ftpClient.storeFile("zipped.zip", input);
        input.close();
        ftpClient.logout();
        ftpClient.disconnect();

        /*
         * File f = new File("zipped0.zip"); FileOutputStream fos = new
         * FileOutputStream(f); fos.write(content); fos.close(); InputStream input = new
         * FileInputStream(f); boolean transferedSuccessfully =
         * ftpClient.storeFile("/zipped.zip", input);
         * LOGGER.info(String.valueOf(ftpClient.getReplyCode()));
         * 		input.close();
         */
        if (transferredSuccessfully) {
            LOGGER.info("transfer OK");
        } else {
            LOGGER.warn("something went wrong");
        }

    }

    private class TlsSessionResumptionFtpClient extends FTPSClient {

        @Override
        protected void _prepareDataSocket_(final Socket socket) throws IOException {
            if (socket instanceof SSLSocket) {
                final SSLSession session = ((SSLSocket) _socket_).getSession();
                final SSLSessionContext context = session.getSessionContext();
                try {
                    final Field sessionHostPortCache = context.getClass().getDeclaredField("sessionHostPortCache");
                    sessionHostPortCache.setAccessible(true);
                    final Object cache = sessionHostPortCache.get(context);
                    final Method putMethod = cache.getClass().getDeclaredMethod("put", Object.class, Object.class);
                    putMethod.setAccessible(true);
                    final Method getHostMethod = socket.getClass().getDeclaredMethod("getHost");
                    getHostMethod.setAccessible(true);
                    Object host = getHostMethod.invoke(socket);
                    final String key = String.format("%s:%s", host, String.valueOf(socket.getPort())).toLowerCase(Locale.ROOT);
                    putMethod.invoke(cache, key, session);
                } catch (Exception e) {
                    throw new IOException(e);
                }
            }
        }

    }
}
