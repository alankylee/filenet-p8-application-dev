package com.example.documents;

public class CreateDocument {

    public CreateDocument() {
        
        // Create a document instance.
        Document doc = Factory.Document.createInstance(os, ClassNames.DOCUMENT);

        // Set document properties.
        doc.getProperties().putValue("DocumentTitle", "New Document via Java API");
        doc.set_MimeType("text/plain");
        StorageArea sa = Factory.StorageArea.getInstance(os, new Id("{DE42374D-B04B-4F47-A62E-CAC9AC9A5719}"));
        doc.set_StorageArea(sa);

        doc.save(RefreshMode.NO_REFRESH);

        // Check in the document.
        doc.checkin(AutoClassify.DO_NOT_AUTO_CLASSIFY, CheckinType.MAJOR_VERSION);

        doc.save(RefreshMode.NO_REFRESH);

        // File the document.
        Folder folder = Factory.Folder.getInstance(os, ClassNames.FOLDER, new Id("{42A3FC29-D635-4C37-8C86-84BAC73FFA3F}"));
        ReferentialContainmentRelationship rcr = folder.file(
                doc,
                AutoUniqueName.AUTO_UNIQUE, "New Document via Java API",
                DefineSecurityParentage.DO_NOT_DEFINE_SECURITY_PARENTAGE);

        rcr.save(RefreshMode.NO_REFRESH);
        
    }

}
