package com.example.documents;

public class MoveDocumentContent {

    public MoveDocumentContent() {
        moveToDatabaseArea();
        moveToFileStorageArea();
    }

    public void moveToDatabaseArea() {
        // Get the storage area where you want to move the document content.
        DatabaseStorageArea dsa = Factory.DatabaseStorageArea.fetchInstance(os, new Id("{3C6CEE68-D8CC-44A5-AEE7-CADE9752AA75}"), null);

        // Get the Document object whose content you want to move.
        Document doc = Factory.Document.getInstance(os, ClassNames.DOCUMENT, new Id("{33074B6E-FD19-4C0D-96FC-D809633D35BE}"));

        // Move the content and save the Document object.
        doc.moveContent(dsa);
        doc.save(RefreshMode.REFRESH);
    }

    public void moveToFileStorageArea() {
        // Get the storage area associated with the new document class.
        FileStorageArea newDocClassFSA = Factory.FileStorageArea.fetchInstance(os, new Id("{3C6CEE68-D8CC-44A5-AEE7-CADE9752AA77}"), null);

        // Get the Document object whose content you want to move.
        Document doc = Factory.Document.getInstance(os, ClassNames.DOCUMENT, new Id("{33074B6E-FD19-4C0D-96FC-D809633D35BE}"));

        // Get the Document object whose class you want to change
        // and whose content you want to move.
        Document doc = Factory.Document.getInstance(os, ClassNames.DOCUMENT, new Id("{33074B6E-FD19-4C0D-96FC-D809633D35BF}"));

        // Change the document class.
        doc.changeClass("newDocClass");

        // Move the content to the storage area associated with the new document class
        // and save the Document object.
        doc.moveContent(newDocClassFSA);
        doc.save(RefreshMode.REFRESH);
    }

}
