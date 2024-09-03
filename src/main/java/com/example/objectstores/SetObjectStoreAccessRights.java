package com.example.objectstores;

public class SetObjectStoreAccessRights {

    public SetObjectStoreAccessRights() {
        
    }

    // Set access rights.
    public static void setAccessRights(
            Domain domain,
            String granteeName, // Example: "CEMPAdmin"
            String objStoreName) // Example: "ObjectStore1"
    {

        final int ACCESS_REQUIRED = AccessRight.WRITE_ANY_OWNER.getValue()
                | AccessRight.REMOVE_OBJECTS.getValue() | AccessRight.STORE_OBJECTS.getValue()
                | AccessRight.CONNECT.getValue() | AccessRight.WRITE_ACL.getValue()
                | AccessRight.READ_ACL.getValue() | AccessRight.MODIFY_OBJECTS.getValue();

        // Create a new access permission object.
        AccessPermission ap = Factory.AccessPermission.createInstance();

        // Set access permissions.  
        ap.set_GranteeName(granteeName);
        ap.set_AccessType(AccessType.ALLOW);
        ap.set_AccessMask(new Integer(ACCESS_REQUIRED));

        // Set and save the new permissions.
        ObjectStore objStore = Factory.ObjectStore.fetchInstance(domain, objStoreName, null);
        AccessPermissionList apl = objStore.get_Permissions();
        apl.add(ap);
        objStore.set_Permissions(apl);
        objStore.save(RefreshMode.REFRESH);
    }
}
