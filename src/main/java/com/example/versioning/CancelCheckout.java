package com.example.versioning;

public class CancelCheckout {

    public CancelCheckout() {
        // Create Document instance and check it out.
        Document doc = Factory.Document.getInstance(os, ClassNames.DOCUMENT,
                new Id("{D6DCDE1F-EF67-4A2E-9CDB-391999BCE8E5}"));
        doc.checkout(ReservationType.EXCLUSIVE, null, null, null);
        doc.save(RefreshMode.REFRESH);

        // Get the reservation object.
        Document reservation = (Document) doc.get_Reservation();

        // Cancel checkout on Document object and
        // save the reservation object.
        doc.cancelCheckout();
        reservation.save(RefreshMode.REFRESH);
    }

}
