package com.nasa.api.model.validity;

import java.util.List;
import java.util.Map;

public class ValidityChecks {
    
    private final int hoursRequired;
    private final List<Integer> solsChecked;
    private final Map<Integer, ValidityCheck> checks;

    public ValidityChecks(int hoursRequired, List<Integer> solsChecked, Map<Integer, ValidityCheck> checks) {
        this.hoursRequired = hoursRequired;
        this.solsChecked = solsChecked;
        this.checks = checks;
    }

    public int getHoursRequired() {
        return hoursRequired;
    }

    public List<Integer> getSolsChecked() {
        return solsChecked;
    }

    public Map<Integer, ValidityCheck> getChecks() {
        return checks;
    }

}
