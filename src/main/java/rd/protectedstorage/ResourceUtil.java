package rd.protectedstorage;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 *  Functions to work with resources in JAR, e.g 7zr.exe file
 */
//TODO extract .exe file and run it, do NOT delete it every time program closes
//TODO remove other not used funktions
public class ResourceUtil {
    public static void main(final String[] args) {
        final URI uri;
        final URI exe;

        uri = getJarURI();
        exe = getFile(uri, "Main.class");
        System.out.println(exe);
    }

    private static URI getJarURI() {
        final ProtectionDomain domain;
        final CodeSource source;
        final URL url;
        final URI uri;

        domain = ResourceUtil.class.getProtectionDomain();
        source = domain.getCodeSource();
        url = source.getLocation();
        try {
            uri = url.toURI();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        return (uri);
    }

    private static URI getFile(final URI where,
                               final String fileName) {
        final File location;
        final URI fileURI;

        try {

            location = new File(where);

            // not in a JAR, just return the path on disk
            if (location.isDirectory()) {
                fileURI = URI.create(where.toString() + fileName);
            } else {
                final ZipFile zipFile;

                zipFile = new ZipFile(location);

                try {
                    fileURI = extract(zipFile, fileName);
                } finally {
                    zipFile.close();
                }
            }

            return (fileURI);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    private static URI extract(final ZipFile zipFile,
                               final String fileName)
            throws IOException {
        final File tempFile;
        final ZipEntry entry;
        final InputStream zipStream;
        OutputStream fileStream;

        tempFile = File.createTempFile(fileName, Long.toString(System.currentTimeMillis()));
        tempFile.deleteOnExit();
        entry = zipFile.getEntry(fileName);

        if (entry == null) {
            throw new FileNotFoundException("cannot find file: " + fileName + " in archive: " + zipFile.getName());
        }

        zipStream = zipFile.getInputStream(entry);
        fileStream = null;

        try {
            final byte[] buf;
            int i;

            fileStream = new FileOutputStream(tempFile);
            buf = new byte[1024];
            i = 0;

            while ((i = zipStream.read(buf)) != -1) {
                fileStream.write(buf, 0, i);
            }
        } finally {
            close(zipStream);
            close(fileStream);
        }

        return (tempFile.toURI());
    }

    private static void close(final Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (Throwable t) {
                throw new RuntimeException(t);
            }
        }
    }
}