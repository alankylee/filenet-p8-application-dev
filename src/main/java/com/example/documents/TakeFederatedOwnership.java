package com.example.documents;

public class TakeFederatedOwnership {

    public TakeFederatedOwnership() {
        
        Document doc = Factory.Document.getInstance(os, ClassNames.DOCUMENT, new Id("{F4DD983C-B845-4255-AC7A-257202B557EC}"));
        doc.takeFederatedOwnership();
        doc.save(RefreshMode.NO_REFRESH);

    }
    
}
