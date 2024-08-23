package com.example.versioning;

public class PromoteObject {

    public PromoteObject() {
        Document doc = Factory.Document.getInstance(os, ClassNames.DOCUMENT, new Id("{9B289B8A-DDD9-42DC-9D51-6B485509B68A}"));
        doc.promoteVersion();
        doc.save(RefreshMode.REFRESH);
    }

}
