package com.example.batches;

public class BatchUpdateToDeleteObjects {

    public BatchUpdateToDeleteObjects() {

    }

    // Uses SearchSQL and SearchScope to find documents of the specified title, then fetches 
    // these Document objects. The onlyReservation parameter indicates whether or not you 
    // want only documents that are currently checked out.
    private static Document[] findDocs(
            ObjectStore os,
            String docTitle,
            boolean onlyReservation,
            int findCount) throws Exception {
        // Declare document array.
        Document[] docArr = new Document[findCount];

        // Build the SQL statement.
        String sql = "select d.this from Document d "
                + "where DocumentTitle = '" + docTitle + "'";
        SearchSQL searchSQL = new SearchSQL();
        searchSQL.setQueryString(sql);

        SearchScope searchScope = new SearchScope(os);
        IndependentObjectSet objectSet = searchScope.fetchObjects(searchSQL, null, null, null);

        int count = 0;
        Iterator iter = objectSet.iterator();
        while (iter.hasNext() == true && count < findCount) {
            Document doc = (Document) iter.next();
            doc.refresh();
            boolean statusReservation = false;
            if (doc.get_VersionStatus() == VersionStatus.RESERVATION) {
                statusReservation = true;
            }

            if (onlyReservation == false || statusReservation == true) {
                System.out.println("Document: " + doc.get_Id().toString());
                docArr[count] = doc;
                count++;
            }
        }

        // Throw an exception if unable to find documents.
        if (count < findCount) {
            String message = "Unable to find " + findCount + " ";
            if (onlyReservation == true) {
                message = message + "reserved ";
            }
            message = message + " documents with title '" + docTitle + "'";
            throw new Exception(message);
        }

        // Return document array.
        return docArr;
    }

    public static void updatePendingDeletion(Domain myDomain) throws Exception {
        PropertyFilter myOSFilter = null;
        String myOS = "LaurelTree";
        ObjectStore os = Factory.ObjectStore.fetchInstance(myDomain, myOS, myOSFilter);

        // The RefreshMode parameter indicates that the property cache for this instance is to be 
        // refreshed with the updated data.
        UpdatingBatch ub = UpdatingBatch.createUpdatingBatchInstance(myDomain, RefreshMode.NO_REFRESH);

        // Get the documents. Calls findDocs routine below to get up to three documents in the 
        // specified object store having a DocumentTitle of "Batch Deleting Example". These 
        // documents might or might not be currently checked out.
        Document[] docArr = findDocs(os, "Batch Deleting Example", false, 3);
        Document doc1 = docArr[0];
        Document doc2 = docArr[1];
        Document doc3 = docArr[2];

        // Override update sequence number check.
        doc1.setUpdateSequenceNumber(null);
        doc2.setUpdateSequenceNumber(null);
        doc3.setUpdateSequenceNumber(null);

        // The inherited IndependentlyPersistedObject.delete method adds this pending action.
        doc1.delete();
        doc2.delete();
        doc3.delete();

        // Adds the Document objects with pending deletions to the UpdatingBatch instance.
        ub.add(doc1, null);
        ub.add(doc2, null);
        ub.add(doc3, null);
        ub.updateBatch();

    }
}
