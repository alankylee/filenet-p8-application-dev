package com.example.batches;

public class BatchRetrieving {

    public BatchRetrieving() {
        // Get an instance of the RetrievingBatch class.
        Domain myDomain = os.get_Domain();
        RetrievingBatch rb = RetrievingBatch.createRetrievingBatchInstance(myDomain);

        // Add objects to the batch. New objects are used for the purposes here.
        Document doc1 = Factory.Document.createInstance(os, null);

        doc1.checkin(null, null);
        doc1.save(RefreshMode.NO_REFRESH);

        Document doc2 = Factory.Document.createInstance(os, null);

        doc2.checkin(null, null);
        doc2.save(RefreshMode.NO_REFRESH);

        // No filtering is used.
        rb.add(doc1, null);
        rb.add(doc2, null);

        // Execute the batch retrieval operation:
        rb.RetrieveBatch();
        
    }
    
}
