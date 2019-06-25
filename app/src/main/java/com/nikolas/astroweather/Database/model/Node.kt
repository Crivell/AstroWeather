package com.nikolas.astroweather.Database.model

class Node {
    val TABLE_NAME = "DataBase"
    val COLUMN_ID = "ID"
    val COLUMN_DATA = "Data"

    var id:Int
    var data:String

    constructor(id: Int, data: String) {
        this.id = id
        this.data = data
    }

    val CREATE_TABLE : String= ("CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_DATA+ " TEXT"
            + ")")


}