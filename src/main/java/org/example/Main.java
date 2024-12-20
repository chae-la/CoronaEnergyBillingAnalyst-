package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        // hardcoded sample data.
        List<String[]> meterReadings = new ArrayList<>();
        meterReadings.add(new String[]{"VPTA221083", "01/12/2023", "896548"});
        meterReadings.add(new String[]{"VPTA221083", "01/01/2024", "896548"});
        meterReadings.add(new String[]{"VPTA221083", "01/02/2024", "896548"});
        meterReadings.add(new String[]{"VPTA221083", "01/03/2024", "896548"});
        meterReadings.add(new String[]{"VPTA221083", "01/04/2024", "896548"});
        meterReadings.add(new String[]{"VPTA221083", "01/05/2024", "896548"});
        meterReadings.add(new String[]{"VPTA221083", "01/06/2024", "896548"});
        meterReadings.add(new String[]{"VPTA221083", "01/07/2024", "896589"});
        meterReadings.add(new String[]{"VPTA221083", "01/08/2024", "896678"});
        meterReadings.add(new String[]{"VPTA221083", "01/09/2024", "896856"});
        meterReadings.add(new String[]{"VPTA221083", "01/10/2024", "897121"});

        Map<String, List<String[]>> meterData = new HashMap<>();

        for(String[] reading : meterReadings) {
            String meterID = reading[0];
        }

    }
}