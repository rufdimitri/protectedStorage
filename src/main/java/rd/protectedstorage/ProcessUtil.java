package rd.protectedstorage;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProcessUtil {
    public static ProcessOutput runProcess(String pathTofile, String[] filenameWithParams) {
        ProcessBuilder pb = new ProcessBuilder(filenameWithParams);
        pb.directory(new File(pathTofile));
        try {
            Process process = pb.start();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));
            ProcessOutput output = new ProcessOutput();

            String line = "";
            //Standart output:
            while ( (line = reader.readLine()) != null ) {
                output.getStandard().add(line);
            }

            BufferedReader errReader =
                    new BufferedReader(new InputStreamReader(process.getErrorStream()));
            //Error output:
            while ( (line = errReader.readLine()) != null ) {
                output.getError().add(line);
            }
            return output;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
