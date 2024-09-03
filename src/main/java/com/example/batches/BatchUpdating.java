package com.example.batches;

public class BatchUpdating {

    public BatchUpdating() {
        // Get an instance of the UpdatingBatch class.
        Domain myDomain = os.get_Domain();

        // The RefreshMode parameter is set to REFRESH to indicate that the property cache for 
        UpdatingBatch ub = UpdatingBatch.createUpdatingBatchInstance(myDomain, RefreshMode.REFRESH);

        // Add object updates to the batch.
        // Assume, in this case, that these documents have already been checked out.
        // No property filters are used (filter parameters are null).
        Document doc1 = Factory.Document.fetchInstance(os, new Id("{F905DBD6-5A69-4252-9985-2D3DD28D7FBA}"), null);
        Document doc2 = Factory.Document.fetchInstance(os, new Id("{35026B90-B443-40CA-B5C3-66BEAD13E2B7}"), null);

        // First update to be included in batch.
        doc1 = (Document) doc1.get_Reservation();
        doc1.checkin(null, CheckinType.MAJOR_VERSION);

        // Second update to be included in batch.
        doc2 = (Document) doc2.get_Reservation();
        doc2.checkin(null, CheckinType.MAJOR_VERSION);

        // Third update to be included in batch. Sets the document title and assigns the 
        // specified property values (Properties.putValue) to the retrieved properties for the 
        // doc (the inherited EngineObject.getProperties). 
        doc1.getProperties().putValue("DocumentTitle", "doc1");

        // Fourth update to be included in batch.
        doc2.getProperties().putValue("DocumentTitle", "doc2");

        // Adds all four updates (to the two Document objects) to the UpdatingBatch instance. 
        ub.add(doc1, null);
        ub.add(doc2, null);

        // Execute the batch update operation.
        ub.updateBatch();
    }

}
