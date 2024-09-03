package com.example.objectstores;

public class CreateInstanceWithFactoryMethod {

    public CreateInstanceWithFactoryMethod() {

    }

    // Create an object store instance.
    public static void createObjectStoreInstance(
            Connection conn,
            String[] adminName, // Example: "Domain Admins, CEMPAdmin"
            String[] userName, // Example: "Domain Users, CEMPUser"
            CmDatabaseConnection dbConn, // Defines the data sources
            String objStoreName, // Object store name. Example: "ObjectStore1"
            String scriptPath) // Creation script file path. Example: "C:\os_script.txt"

            throws Exception {

        // Get the default domain.
        Domain domain = Factory.Domain.getInstance(conn, null);

        // Create an object store.
        String[] admins = {adminName};
        String[] users = {userName};
        ObjectStore objStore = null;

        if (scriptPath == null) {
            // Create without using a schema script.
            System.out.println("Creating object store without a schema script");
            objStore = Factory.ObjectStore.createInstance(domain, admins, users);
        } else {
            // Create using a schema script.
            // The getScript method reads in the script from a file.
            String creationScript = getScript(scriptPath);
            System.out.println("Creating object store with a schema script");

            // To print the contents of the script file, uncomment the next line.
            // System.out.println(creationScript); 
            objStore = Factory.ObjectStore.createInstance(domain, admins, users, creationScript);
        }

        // Set properties.
        objStore.set_DisplayName(objStoreName);
        objStore.set_SymbolicName(objStoreName);
        objStore.set_DatabaseConnection(dbConn);

        // Save object store and display name.
        objStore.save(RefreshMode.REFRESH);
        System.out.println("Object store name: " + objStore.get_Name());
    }
    
}
