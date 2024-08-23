package com.example.documents;

public class RetrieveDocument {

    public RetrieveDocument() {
        useGetInstance();
        useFetchInstance();
    }

    public void useGetInstance() {
        // Get document and populate property cache.
        PropertyFilter pf = new PropertyFilter();
        pf.addIncludeProperty(new FilterElement(null, null, null, "DocumentTitle", null));
        pf.addIncludeProperty(new FilterElement(null, null, null, PropertyNames.MIME_TYPE, null));
        Document doc = Factory.Document.getInstance(os, ClassNames.DOCUMENT, new Id("{F4DD983C-B845-4255-AC7A-257202B557EC}"));

        // Fetch selected properties from the server.
        doc.fetchProperties(pf);

        // Return document properties.
        com.filenet.api.property.Properties props = doc.getProperties();

        // Iterate the set and print property values.
        Iterator iter = props.iterator();
        System.out.println("Property" + "\t" + "Value");
        System.out.println("------------------------");
        while (iter.hasNext()) {
            Property prop = (Property) iter.next();
            if (prop.getPropertyName().equals("DocumentTitle")) {
                System.out.println(prop.getPropertyName() + "\t" + prop.getStringValue()); 
            }else if (prop.getPropertyName().equals(PropertyNames.MIME_TYPE,)) {
                System.out.println(prop.getPropertyName() + "\t" + prop.getStringValue());
            }
        }
    }

    public void useFetchInstance() {
        // Get document and populate property cache.
        PropertyFilter pf = new PropertyFilter();
        pf.addIncludeProperty(new FilterElement(null, null, null, "DocumentTitle", null));
        pf.addIncludeProperty(new FilterElement(null, null, null, PropertyNames.MIME_TYPE, null));
        Document doc = Factory.Document.fetchInstance(os, new Id("{F4DD983C-B845-4255-AC7A-257202B557EC}"), pf);

        // Return document properties.
        com.filenet.api.property.Properties props = doc.getProperties();

        // Iterate the set and print property values. 
        Iterator iter = props.iterator();
        System.out.println("Property" + "\t" + "Value");
        System.out.println("------------------------");
        while (iter.hasNext()) {
            Property prop = (Property) iter.next();
            if (prop.getPropertyName().equals("DocumentTitle")) {
                System.out.println(prop.getPropertyName() + "\t" + prop.getStringValue()); 
            }else if (prop.getPropertyName().equals(PropertyNames.MIME_TYPE)) {
                System.out.println(prop.getPropertyName() + "\t" + prop.getStringValue());
            }
        }
    }

}
