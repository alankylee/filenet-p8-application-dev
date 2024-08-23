package com.example.versioning;

public class DeleteVersionSeriesObject {

    public DeleteVersionSeriesObject() {
        // Get document and put VersionSeries object in property cache.
        PropertyFilter pf = new PropertyFilter();
        pf.addIncludeProperty(new FilterElement(null, null, null, PropertyNames.VERSION_SERIES, null));
        Document doc = Factory.Document.fetchInstance(os, "{3488C44F-D4BB-455F-AEED-553E9EADCC4E}", pf);

        // Get VersionSeries object.
        VersionSeries verSeries = doc.get_VersionSeries();

        // Delete VersionSeries object.
        verSeries.delete();
        verSeries.save(RefreshMode.REFRESH);
    }

}
