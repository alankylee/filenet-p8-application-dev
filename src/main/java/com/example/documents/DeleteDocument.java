package com.example.documents;

public class DeleteDocument {

    public DeleteDocument() {
        Document doc = Factory.Document.getInstance(os, ClassNames.DOCUMENT, new Id("{A26C3EF4-7C02-4D01-84CD-D3D7F1D5DA19}"));
        doc.delete();
        doc.save(RefreshMode.NO_REFRESH);
    }
}
