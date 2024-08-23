package com.example.documents;

public class AssignStoragePolicy {

    public AssignStoragePolicy() {
        // Get document and populate property cache.
        PropertyFilter pf = new PropertyFilter();
        pf.addIncludeProperty(new FilterElement(null, null, null, PropertyNames.STORAGE_POLICY, null));
        Document doc = Factory.Document.fetchInstance(os, "{33074B6E-FD19-4C0D-96FC-D809633D35BE}", pf);

        // Print name of current storage policy.
        System.out.println("Current storage policy: " + doc.get_StoragePolicy().get_DisplayName());

        // Set new storage policy and print name.
        StoragePolicy sp = Factory.StoragePolicy.getInstance(os, new Id("{BA065E85-7D01-48E3-9266-4EFDC8D8FAE3}"));
        doc.set_StoragePolicy(sp);
        doc.save(RefreshMode.REFRESH);
        System.out.println("New storage policy: " + doc.get_StoragePolicy().get_DisplayName());
    }

}
