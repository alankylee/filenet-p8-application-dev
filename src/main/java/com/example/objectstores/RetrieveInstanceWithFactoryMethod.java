package com.example.objectstores;

public class RetrieveInstanceWithFactoryMethod {

    public RetrieveInstanceWithFactoryMethod() {

    }

    // Get object store instance.
    private static void getObjectStoreInstance(
            Domain domain,
            String objStoreName) // Example: "ObjectStore1"
    {
        // Get object store (fetchless instantiation).
        ObjectStore ojbStore = Factory.ObjectStore.getInstance(domain, objStoreName);

        // Show object store name.
        objStore.refresh();
        System.out.println("Object store name: " + objStore.get_Name());
    }

    // Fetch object store instance.
    private static void fetchObjectStoreInstance(
            Domain domain,
            String objStoreName) // Example: "ObjectStore1"
    {
        // Define a property filter to limit the returned properties.
        PropertyFilter filter = new PropertyFilter();
        filter.addIncludeProperty(0, null, null, PropertyNames.ROOT_CLASS_DEFINITIONS, null);
        filter.addIncludeProperty(0, null, null, PropertyNames.DISPLAY_NAME, null);

        // Fetch object store using the property filter.            
        ObjectStore objStore = Factory.ObjectStore.fetchInstance(domain, objStoreName, filter);

        // Show object store display name.
        System.out.println("Object store name: " + objStore.get_DisplayName());
    }
}
