package com.nikolas.astroweather.Database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.nikolas.astroweather.Database.model.Node
import android.content.ContentValues









class DatabaseHelper(context: Context,
                     factory: SQLiteDatabase.CursorFactory?) : SQLiteOpenHelper(context, "database",
    factory, 1) {

    val no = Node(1,"sad")
    override fun onCreate(db: SQLiteDatabase?) {

        db!!.execSQL(no.CREATE_TABLE)
        this.insertNote(Node(1,""))
        this.insertNote(Node(2,""))
        this.insertNote(Node(3,""))
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + no.TABLE_NAME)
        onCreate(db)
    }


    fun insertNote(note: Node): Long {
        // get writable database as we want to write data
        val db = this.writableDatabase

        val values = ContentValues()
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(no.COLUMN_DATA, note.data)

        // insert row
        val id = db.insert(no.TABLE_NAME, null, values)

        // close db connection
        db.close()

        // return newly inserted row id
        return id
    }

    fun getNode(id: Long): Node {

        val db = this.readableDatabase

        val cursor = db.query(
            no.TABLE_NAME,
            arrayOf(no.COLUMN_ID, no.COLUMN_DATA),
            no.COLUMN_ID + "=?",
            arrayOf(id.toString()), null, null, null, null
        )

        cursor?.moveToFirst()

        // prepare note object
        val node = Node(
            cursor!!.getInt(cursor.getColumnIndex(no.COLUMN_ID)),
            cursor.getString(cursor.getColumnIndex(no.COLUMN_DATA))
        )

        // close the db connection
        cursor.close()

        return node
    }

    fun updateNote(node: Node): Int {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(no.COLUMN_DATA, node.data)

        // updating row
        return db.update(
            no.TABLE_NAME, values, no.COLUMN_ID + " = "+
            node.id,null
        )


    }

}