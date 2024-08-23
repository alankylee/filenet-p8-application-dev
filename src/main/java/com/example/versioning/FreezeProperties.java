package com.example.versioning;

public class FreezeProperties {

    // Use the freeze method to prevent changes to the custom properties of a checked-in document version.
    public FreezeProperties() {
        Document doc = Factory.Document.getInstance(os, ClassNames.DOCUMENT, new Id("{9B289B8A-DDD9-42DC-9D51-6B485509B68A}"));
        doc.freeze();
        doc.save(RefreshMode.REFRESH);
    }

}
