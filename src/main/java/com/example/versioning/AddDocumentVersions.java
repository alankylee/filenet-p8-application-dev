package com.example.versioning;

import java.io.File;
import java.io.FileInputStream;

public class AddDocumentVersions {

    public AddDocumentVersions() {
        // Add the first of two new versions.
        // This version will have no content.
        Document doc = Factory.Document.getInstance(os, ClassNames.DOCUMENT, new Id("{D6DCDE1F-EF67-4A2E-9CDB-391999BCE8E5}") );

        // Check out the Document object and save it.
        doc.checkout(ReservationType.OBJECT_STORE_DEFAULT, null, null, null);
        doc.save(RefreshMode.REFRESH);          

        // Get the reservation object from the Document object.
        Document reservation = (Document) doc.get_Reservation();

        // Check in reservation object as minor version.
        reservation.checkin(AutoClassify.DO_NOT_AUTO_CLASSIFY, CheckinType.MINOR_VERSION);
        reservation.save(RefreshMode.REFRESH);
        ////////////////////////////////////////////////////////////////////

        // Add the second of two new versions.
        // This version will have content.
        VersionSeries verSeries = doc.get_VersionSeries();

        // Print information about new version 1 of 2.
        Versionable version = verSeries.get_CurrentVersion();
        System.out.println("Status of current version: " + version.get_VersionStatus().toString() +
            "\n Number of current version: " + version.get_MajorVersionNumber() +"."+ version.get_MinorVersionNumber() );

        // Check out the VersionSeries object and save it.
        verSeries.checkout(ReservationType.OBJECT_STORE_DEFAULT, null, null, null);
        verSeries.save(RefreshMode.REFRESH);

        // Get the reservation object from the VersionSeries object.
        reservation = (Document) verSeries.get_Reservation();

        // Add content to reservation object.
        File file = new File("C:\\officePolicy.doc");
        try {
            //Create a ContentTransfer object
            ContentTransfer ctObject = Factory.ContentTransfer.createInstance();
            FileInputStream fileIS = new FileInputStream(file.getAbsolutePath());
            ContentElementList contentList = Factory.ContentTransfer.createList();
            ctObject.setCaptureSource(fileIS);
            // Add ContentTransfer object to list and set on reservation
            contentList.add(ctObject);
            reservation.set_ContentElements(contentList);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage() );
        }
        
        // Check in reservation object as major version.
        reservation.checkin(null, CheckinType.MAJOR_VERSION);
        reservation.save(RefreshMode.REFRESH);
        
        // Print information about new version 2 of 2.
        version = verSeries.get_CurrentVersion();
        System.out.println("Status of current version: " + version.get_VersionStatus().toString() +
            "\n Number of current version: " + version.get_MajorVersionNumber() +"."+ version.get_MinorVersionNumber() );
    }

}
