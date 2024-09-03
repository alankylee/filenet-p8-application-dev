package com.example.objectstores;

public class RetrieveFromRepositoryObject {

    public RetrieveFromRepositoryObject() {
        
    }

    // Get object store from repository object subclass.
    private static void getObjectStoreFromSubclass(Document doc) {
        // Get the object store in which the document is stored. 
        ObjectStore objStore = doc.getObjectStore();

        // Get the display name of the returned object store.
        objStore.refresh();
        System.out.println("Object store name = " + objStore.get_DisplayName());
    }
    
}
