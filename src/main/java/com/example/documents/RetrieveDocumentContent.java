package com.example.documents;

import java.io.IOException;
import java.io.InputStream;

public class RetrieveDocumentContent {

    public RetrieveDocumentContent() {
        // Get document and populate property cache.
        PropertyFilter pf = new PropertyFilter();
        pf.addIncludeProperty(new FilterElement(null, null, null, PropertyNames.CONTENT_SIZE, null) );
        pf.addIncludeProperty(new FilterElement(null, null, null, PropertyNames.CONTENT_ELEMENTS, null) );
        Document doc=Factory.Document.fetchInstance(os, "{9FEC3C69-57B2-4E29-872A-0EE452881555}", pf );

        // Print information about content elements.
        System.out.println("No. of document content elements: " + doc.get_ContentElements().size() + "\n" +
        "Total size of content: " + doc.get_ContentSize() + "\n");

        // Get content elements and iterate list.
        ContentElementList docContentList = doc.get_ContentElements();
        Iterator iter = docContentList.iterator();
        while (iter.hasNext() )
        {
            ContentTransfer ct = (ContentTransfer) iter.next();

            // Print element sequence number and content type of the element.
            System.out.println("\nElement Sequence number: " + ct.get_ElementSequenceNumber().intValue() + "\n" +
            "Content type: " + ct.get_ContentType() + "\n");

            // Get and print the content of the element.
            InputStream stream = ct.accessContentStream();
            String readStr = "";
            try
            {
                int docLen = 1024;
                byte[] buf = new byte[docLen];
                int n = 1;
                while (n > 0)
                {
                n =  stream.read(buf, 0, docLen);
                readStr = readStr + new String(buf);
                buf = new byte[docLen];
                }
                System.out.println("Content:\n " + readStr);
                stream.close();
            }
            catch(IOException ioe)
            {
                ioe.printStackTrace();
            }
        }
    }

}
