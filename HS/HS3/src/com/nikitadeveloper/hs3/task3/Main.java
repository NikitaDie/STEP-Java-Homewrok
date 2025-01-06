package com.nikitadeveloper.hs3.task3;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Test addPerson method
        String personId = TaxInspectionDatabase.addPerson("John Doe");
        System.out.println();

        // Test addFine method
        TaxInspectionDatabase.addFine(personId, FineType.SPEEDING, 100.0, "Kyiv", "Speeding in a residential area");
        System.out.println();

        // Test printDatabase method to check added data
        TaxInspectionDatabase.printDatabase();
        System.out.println();

        // Test printDataByPersonId method
        TaxInspectionDatabase.printDataByPersonId(personId);
        System.out.println();

        // Test updatePerson method (update name and city)
        Map<String, String> personUpdates = new HashMap<>();
        personUpdates.put("name", "Jane Doe2");
        personUpdates.put("city", "Lviv");
        TaxInspectionDatabase.updatePerson(personId, personUpdates);
        System.out.println();

        // Test printDataByPersonId again to verify updates
        TaxInspectionDatabase.printDataByPersonId(personId);
        System.out.println();

        // Test updateFine method (update fine amount and description)
        String fineId = TaxInspectionDatabase.getPerson(personId).getFines().get(0);

        Map<String, String> fineUpdates = new HashMap<>();
        fineUpdates.put("amount", "150.0");
        fineUpdates.put("description", "Speeding in a school zone");
        fineUpdates.put("city", "Lviv");
        TaxInspectionDatabase.updateFine(fineId, fineUpdates);
        System.out.println();

        // Test printDataByPersonId again to verify fine update
        TaxInspectionDatabase.printDataByPersonId(personId);
        System.out.println();

        // Test printDataByPersonId again to verify fine removal
        TaxInspectionDatabase.printDataByPersonId(personId);
        System.out.println();

        // Test printDataByCity method
        TaxInspectionDatabase.printDataByCity("Lviv");
        System.out.println();

        // Test printDataByFineType method
        TaxInspectionDatabase.printDataByFineType("Speeding");
        System.out.println();

        // Test removeFine method
        TaxInspectionDatabase.removeFine(personId, fineId);
        System.out.println();
    }
}
