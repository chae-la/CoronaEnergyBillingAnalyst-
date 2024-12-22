package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        boolean useDatabase = false;    // Set to `true` when using database

        Map<String, List<String[]>> meterData = new HashMap<>();

        // Connect to database or use hardcoded data
        if (useDatabase) {
            String url = "jdbc:mysql://localhost:3306/gas_meter_data";  // Example URL for database
            String user = "your_username";                              // Example Username and password
            String password = "your_password";

            try (Connection connection = DriverManager.getConnection(url, user, password)) {
                System.out.println("Connected to the database");

                String query = "SELECT MeterID, Date, Reading FROM MeterReadings ORDER BY MeterID, Date";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    String meterID = resultSet.getString("MeterID");
                    String date = resultSet.getString("Date");
                    String reading = resultSet.getString("Reading");

                    meterData.putIfAbsent(meterID, new ArrayList<>());
                    meterData.get(meterID).add(new String[]{meterID, date, reading});
                }

            } catch (Exception e) {
                System.out.println(e);;
                return;
            }
        } else {
            // Hardcoded data to test code
            List<String[]> hardcodedReadings = new ArrayList<>();
            hardcodedReadings.add(new String[]{"VPTA221083", "01/12/2023", "896548"});
            hardcodedReadings.add(new String[]{"VPTA221083", "01/01/2024", "896548"});
            hardcodedReadings.add(new String[]{"VPTA221083", "01/02/2024", "896548"});
            hardcodedReadings.add(new String[]{"VPTA221083", "01/03/2024", "896549"});
            hardcodedReadings.add(new String[]{"VPTA221083", "01/04/2024", "896548"});
            hardcodedReadings.add(new String[]{"VPTA221083", "01/05/2024", "896548"});
            hardcodedReadings.add(new String[]{"VPTA221083", "01/06/2024", "896548"});
            hardcodedReadings.add(new String[]{"VPTA221083", "01/07/2024", "896678"});
            hardcodedReadings.add(new String[]{"VPTA221083", "01/08/2024", "896856"});
            hardcodedReadings.add(new String[]{"VPTA221083", "01/09/2024", "897121"});

            for (String[] reading : hardcodedReadings) {
                String meterID = reading[0];
                meterData.putIfAbsent(meterID, new ArrayList<>());
                meterData.get(meterID).add(reading);
            }
        }

        // Analysing meter readings for anomalies.
        for (String meterID : meterData.keySet()) {
            List<String[]> readings = meterData.get(meterID);
            int consecutiveMonths = 0;
            int startZeroIndex = -1;
            double previousReading = Double.parseDouble(readings.get(0)[2]);

            for (int i = 1; i < readings.size(); i++) {
                double currentReading = Double.parseDouble(readings.get(i)[2]);
                if (currentReading == previousReading) {
                    consecutiveMonths++;
                    if (startZeroIndex == -1) {
                        startZeroIndex = i-1;
                    }
                } else {
                    if (consecutiveMonths >= 2) {
                        System.out.printf("Anomaly detected for Meter %s:\n", meterID);
                        System.out.printf("Start Date: %s, End Date: %s, Consecutive Months: %d\n",
                                readings.get(startZeroIndex)[1], readings.get(i-1)[1], (consecutiveMonths +1));
                    }
                    consecutiveMonths = 0;
                    startZeroIndex = -1;
                }
                previousReading = currentReading;
            }
        }
    }
}