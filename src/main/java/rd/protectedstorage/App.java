package rd.protectedstorage;

import java.net.URI;

public class App {

    static void testResourceUtil() {
        final URI uri;
        final URI exeURI;

        uri = ResourceUtil.getJarURI();
        exeURI = ResourceUtil.getFile(uri, "7zr.exe");

        System.out.println(exeURI.getPath());

        //TODO
        //File exeFile =
        //ProcessUtil.runProcess(exeURI.toString())
    }

    public static void main(String[] args) {
        testResourceUtil();
    }
}
