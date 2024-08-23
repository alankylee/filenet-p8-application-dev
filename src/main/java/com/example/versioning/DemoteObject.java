package com.example.versioning;

public class DemoteObject {

    public DemoteObject() {
        Document doc = Factory.Document.getInstance(os, ClassNames.DOCUMENT, new Id("{9B289B8A-DDD9-42DC-9D51-6B485509B68A}"));
        doc.demoteVersion();
        doc.save(RefreshMode.REFRESH);
    }

}
