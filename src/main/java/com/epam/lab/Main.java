package com.epam.lab;

import static com.epam.lab.DatabaseCreation.createSchemaFromDump;

public class Main {
    public static void main(String[] args) {
        createSchemaFromDump();

        //Read meta data about table
       /* MetaData metaData = new MetaData();
        metaData.readDatabaseProductNameVersion();
        metaData.readTablesName();
        metaData.readColumnsNumber();
        metaData.readMetaDataAboutColumns();
        metaData.getPrimaryKey();*/
    }
}