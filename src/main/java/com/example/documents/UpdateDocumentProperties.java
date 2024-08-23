package com.example.documents;

public class UpdateDocumentProperties {

    public UpdateDocumentProperties() {

        // Get document and populate property cache.
        PropertyFilter pf = new PropertyFilter();
        pf.addIncludeProperty(new FilterElement(null, null, null, "DocumentTitle", null));
        Document doc = Factory.Document.fetchInstance(os, new Id("{F4DD983C-B845-4255-AC7A-257202B557EC}"), pf);

        // Return document properties.
        com.filenet.api.property.Properties props = doc.getProperties();

        // Change property value.
        props.putValue("DocumentTitle", "Document with Updated Title via Java API");

        // Save and update property cache.
        doc.save(RefreshMode.REFRESH);

    }

}
