package com.example.documents;

import java.lang.instrument.ClassDefinition;

public class CustomizeDocumentClass {

    public CustomizeDocumentClass() {
        customizeClass();
    }

    public void customizeClass()
    {
        //  Fetch selected class definition from the server.
        ClassDefinition classDef = Factory.ClassDefinition.fetchInstance(os, "Email", null );

        // Create new PropertyTemplate for object property with optional value.
        PropertyTemplateObject newPropTemplate = Factory.PropertyTemplateObject.createInstance(os);
        newPropTemplate.set_Cardinality (Cardinality.SINGLE);
        newPropTemplate.set_IsValueRequired(Boolean.FALSE);

        // Set required properties to locale-specific string.
        LocalizedString locStr1 = getLocalizedString("eMail Signature", os.get_LocaleName() );
        // Create LocalizedString collection.
        newPropTemplate.set_DisplayNames (Factory.LocalizedString.createList() );
        newPropTemplate.get_DisplayNames().add(locStr1);

        LocalizedString locStr2 = getLocalizedString("Signature of sender",
        os.get_LocaleName() );
        newPropTemplate.set_DescriptiveTexts(Factory.LocalizedString.createList() );
        newPropTemplate.get_DescriptiveTexts().add(locStr2);

        // Save property template to the server.
        newPropTemplate.save(RefreshMode.REFRESH);

        // Create property definition from property template.
        PropertyDefinitionObject newPropDef = (PropertyDefinitionObject)newPropTemplate.createClassProperty();

        // Set RequiredClass property to Email Items.
        newPropDef.set_RequiredClassId(new Id("{BFA64F40-5C45-45B1-B540-B5BA3CA08AAB}") );

        // Get PropertyDefinitions property from the property cache.
        PropertyDefinitionList propDefs = classDef.get_PropertyDefinitions();

        // Add new property definition to class definition.
        propDefs.add(newPropDef);

        classDef.save(RefreshMode.REFRESH);
    }

    private LocalizedString getLocalizedString(String text, String locale)
    {
        LocalizedString locStr = Factory.LocalizedString.createInstance ();
        locStr.set_LocalizedText(text);
        locStr.set_LocaleName (locale);
        return locStr;
    }

}
