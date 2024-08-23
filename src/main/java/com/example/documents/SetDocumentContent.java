package com.example.documents;

public class SetDocumentContent {

    public SetDocumentContent() {
        // Get document.
        Document doc = Factory.Document.getInstance(os, ClassNames.DOCUMENT, new Id("{8FD91CF0-E991-426D-9BCB-B63F0E30E604}"));

        // Check out the Document object and save it.
        doc.checkout(com.filenet.api.constants.ReservationType.EXCLUSIVE, null, doc.getClassName(), doc.getProperties());
        doc.save(RefreshMode.REFRESH);

        // Get the Reservation object from the Document object.
        Document reservation = (Document) doc.get_Reservation();

        // Specify internal and external files to be added as content.
        File internalFile = new File("C:\\docs\\mydoc.txt");
        // non-Windows: File internalFile = new File("/tmp/docs/mydoc.txt");
        String externalFile = "ftp://ftp.mycompany.com/docs/relnotes.txt";

        // Add content to the Reservation object.
        try {
            // First, add a ContentTransfer object.
            ContentTransfer ctObject = Factory.ContentTransfer.createInstance();
            FileInputStream fileIS = new FileInputStream(internalFile.getAbsolutePath());
            ContentElementList contentList = Factory.ContentTransfer.createList();
            ctObject.setCaptureSource(fileIS);
            // Add ContentTransfer object to list.
            contentList.add(ctObject);

            // Second, add a ContentReference object.
            ContentReference crObject = Factory.ContentReference.createInstance(os);
            crObject.set_ContentLocation(externalFile);
            crObject.set_ContentType("text/plain"); // Must be set for ContentReference.
            // Add ContentReference object to list.
            contentList.add(crObject);

            reservation.set_ContentElements(contentList);
            reservation.save(RefreshMode.REFRESH);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Check in Reservation object as major version.
        reservation.checkin(AutoClassify.DO_NOT_AUTO_CLASSIFY, CheckinType.MAJOR_VERSION);
        reservation.save(RefreshMode.REFRESH);
    }

}
