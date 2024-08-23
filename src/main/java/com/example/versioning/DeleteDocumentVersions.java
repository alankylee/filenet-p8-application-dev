package com.example.versioning;

public class DeleteDocumentVersions {

    public DeleteDocumentVersions() {
        static PropertyFilter pf = new PropertyFilter();
        
        //......

        pf.addIncludeProperty (
        new FilterElement(null, null, null, "VersionSeries Id", null) );
        Document doc = Factory.Document.fetchInstance(os, new Id("{91CD21FA-5F65-4D5B-AE3E-ECE529C7AC88}"), pf);

        // Get set of document versions from VersionSeries
        VersionSeries vs = doc.get_VersionSeries();
        VersionableSet vss = vs.get_Versions();

        // Iterate versions and delete the third one.
        Iterator vssiter = vss.iterator();

        while (vssiter.hasNext ()) {
            Versionable ver = (Versionable) vssiter.next();
            System.out.println("Major = " + ver.get_MajorVersionNumber() + "; Minor = " + ver.get_MinorVersionNumber());
            if (ver.get_MajorVersionNumber().intValue() == 3) {
                // To delete, cast the Versionable object to Document.
                Document verdoc = (Document) ver;
                verdoc.delete();
                verdoc.save(RefreshMode.REFRESH);
            }
        }
    }

}
