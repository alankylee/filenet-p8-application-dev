package com.example.objectstores;

public class RetrieveFromDomain {

    public RetrieveFromDomain() {

    }

    // Instantiate object store from domain.
    public static void instantiateObjectStoreFromDomain(Connection conn, String domainName)
    {
        // Get domain.
        Domain domain = Factory.Domain.fetchInstance(conn, domainName, null);
        ObjectStoreSet osColl = domain.get_ObjectStores();

        // Get each object store.
        Iterator iterator = osColl.iterator();
        while (iterator.hasNext()) {
            // Get next object store.
            ObjectStore objStore = (ObjectStore) iterator.next();

            // Get the display name of the object store.
            String objStoreName = objStore.get_DisplayName();
            System.out.println("Object store name = " + objStoreName);
        }
    }

}
