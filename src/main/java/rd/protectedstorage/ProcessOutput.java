package rd.protectedstorage;

import java.util.ArrayList;
import java.util.List;

public class ProcessOutput {
    private List<String> standard = new ArrayList<>();
    private List<String> error = new ArrayList<>();

    public List<String> getStandard() {
        return standard;
    }

    public void setStandard(List<String> standard) {
        this.standard = standard;
    }

    public List<String> getError() {
        return error;
    }

    public void setError(List<String> error) {
        this.error = error;
    }
}
