package com.example.versioning;

public class WorkWithVersioningProperties {

    public WorkWithVersioningProperties() {
        // Get document and put Versionable object in property cache.
        PropertyFilter pf = new PropertyFilter();
        pf.addIncludeProperty(new FilterElement(null, null, null, PropertyNames.VERSIONS, null));
        Document doc = Factory.Document.fetchInstance(os, "{9B289B8A-DDD9-42DC-9D51-6B485509B68A}", pf);

        // Return all document versions.
        VersionableSet versions = doc.get_Versions();
        Versionable version;

        // Iterate the set and print information about each version.
        Iterator iter = versions.iterator();
        while (iter.hasNext()) {
            version = (Versionable) iter.next();
            System.out.println("Status of version: " + version.get_VersionStatus().toString()
                    + "\nNumber of current version: " + version.get_MajorVersionNumber() + "." + version.get_MinorVersionNumber()
                    + "\nIs reserved: " + version.get_IsReserved()
                    + "\nIs current version: " + version.get_IsCurrentVersion()
                    + "\nIs frozen version: " + version.get_IsFrozenVersion()
                    + "\n----------------------"
            );
        }
    }

}
