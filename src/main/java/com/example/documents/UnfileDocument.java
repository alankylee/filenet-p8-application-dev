package com.example.documents;

public class UnfileDocument {

    public UnfileDocument() {
        static PropertyFilter pf = new PropertyFilter();

        pf.addIncludeProperty (
        new FilterElement(null, null, null, "Containers", null));
        // Get document to be unfiled.
        Document doc = Factory.Document.fetchInstance(os, new Id("{8854236F-02D6-40AB-B4B2-59B6756154D8}"), pf);

        // Iterate all folders that contain the document, until the desired folder is found.
        ReferentialContainmentRelationshipSet rcrs = doc.get_Containers();
        Iterator iter = rcrs.iterator();

        while (iter.hasNext ())
        {
            ReferentialContainmentRelationship rcr = (ReferentialContainmentRelationship) iter.next();
            Folder folder = (Folder) rcr.get_Tail();
            if (folder.get_Id().equals(new Id("{C40106FE-B510-4222-BB42-6D2FD5D21123}"))) {
                rcr.delete();
                rcr.save(RefreshMode.REFRESH);
                break;
            }
        }
    }

}
