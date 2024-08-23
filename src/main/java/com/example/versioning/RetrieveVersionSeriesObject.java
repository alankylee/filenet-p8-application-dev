package com.example.versioning;

public class RetrieveVersionSeriesObject {

    public RetrieveVersionSeriesObject() {
        // Get document and put VersionSeries object in property cache.
        PropertyFilter pf = new PropertyFilter();
        pf.addIncludeProperty(new FilterElement(null, null, null, PropertyNames.VERSION_SERIES, null));
        Document doc = Factory.Document.fetchInstance(os, "{393472DA-7958-48CF-8C9F-7EA0E6B25678}", pf);

        // Get VersionSeries object.
        VersionSeries verSeries = doc.get_VersionSeries();

        // Get the storage area where you want to move the document content.
        FileStorageArea fsa = Factory.FileStorageArea.fetchInstance(os, new Id("{DE42374D-B04B-4F47-A62E-CAC9AC9A5719}"), null);

        // Move content.
        verSeries.moveContent(fsa);
        verSeries.save(RefreshMode.REFRESH);
    }

}
