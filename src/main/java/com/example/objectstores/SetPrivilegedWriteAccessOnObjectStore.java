package com.example.objectstores;

public class SetPrivilegedWriteAccessOnObjectStore {

    public SetPrivilegedWriteAccessOnObjectStore() {
        
    }

    // Set write access.
    private static void setWriteAccess(
            ObjectStore objStore,
            String granteeName,
            String granteePassword,
            String originalCreator,
            Date originalCreateDate,
            String originalModifier,
            Date originalModifyDate) {
        // Create a new access permission object.
        AccessPermission ap = Factory.AccessPermission.createInstance();

        // Set access permissions  
        ap.set_GranteeName(granteeName);
        ap.set_AccessType(AccessType.ALLOW);
        ap.set_AccessMask(
                new Integer(AccessRight.WRITE_ANY_OWNER_AS_INT + AccessRight.REMOVE_OBJECTS_AS_INT
                        + AccessRight.MODIFY_OBJECTS_AS_INT + AccessRight.STORE_OBJECTS_AS_INT + AccessRight.CONNECT_AS_INT
                        + AccessRight.WRITE_ACL_AS_INT + AccessRight.READ_ACL_AS_INT + AccessRight.PRIVILEGED_WRITE_AS_INT));

        // Add the permission to the list for the Object Store.
        objStore.get_Permissions().add(ap);

        // Save the object store with its permissions.
        objStore.save(RefreshMode.REFRESH);

        // Login in as the user who has the newly granted
        // privileged write access.
        Connection conn = objStore.getConnection();
        Subject sub = UserContext.createSubject(conn, granteeName, granteePassword, "FileNetP8");
        UserContext.get().pushSubject(sub);
        try {
            // Create a document "doc".
            Document doc = Factory.Document.createInstance(objStore, "Document");

            // Set system-level properties on the created document "doc". 
            doc.set_Creator(originalCreator);
            doc.set_DateCreated(originalCreateDate);
            doc.set_LastModifier(originalModifier);
            doc.set_DateLastModified(originalModifyDate);

            // Perform additional actions as desired.
            // Save the document.            
            doc.save(RefreshMode.REFRESH);
            System.out.println("Document created: " + doc.get_Id());
        } finally {
            UserContext.get().popSubject();
        }
    }
}
