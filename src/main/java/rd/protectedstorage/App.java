package rd.protectedstorage;

import java.io.File;
import java.net.URI;
import java.util.List;

public class App {

    static void testResourceUtil() {
        final URI uri;
        final URI exeURI;

        //Get 7zr.exe
        uri = ResourceUtil.getJarURI();
        exeURI = ResourceUtil.getFile(uri, "7zr.exe");

        System.out.println(exeURI.getPath());

        //Run .exe
        File exeFile = new File(exeURI.getPath());

        ProcessOutput output = ProcessUtil.runProcess(exeFile.getParent(), new String[]{exeFile.getAbsolutePath(), "h -Werror"});
        for (String string : output.getError()) {
            System.out.println(string);
        }
        for (String string : output.getStandard()) {
            System.out.println(string);
        }
    }

    public static void main(String[] args) {
        testResourceUtil();
    }
}
