package rd.protectedstorage;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProcessUtil {
    public static List<String> runProcess(String pathTofile, String[] filenameWithParams) {
        ProcessBuilder pb = new ProcessBuilder(filenameWithParams);
        pb.directory(new File(pathTofile));
        try {
            Process process = pb.start();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));
            List<String> output = new ArrayList<>();
            String line = "";
            while ( (line = reader.readLine()) != null ) {
                output.add(line);
            }
            return output;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
