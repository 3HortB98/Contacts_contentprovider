package com.example.mycontacts

import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.app.ActivityCompat
import android.support.v4.app.LoaderManager
import android.support.v4.content.ContextCompat
import android.support.v4.content.CursorLoader
import android.support.v4.content.Loader
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity(),LoaderManager.LoaderCallbacks<Cursor> {

    private lateinit var simpleCursorAdapter: SimpleCursorAdapter

    override fun onCreateLoader(p0: Int, p1: Bundle?): Loader<Cursor> {
        val  projection = arrayOf(ContactsContract.Contacts._ID,
            ContactsContract.Contacts.LOOKUP_KEY,
                ContactsContract.Contacts.DISPLAY_NAME_PRIMARY)
        return CursorLoader(this,
            ContactsContract.Contacts.CONTENT_URI,
            projection,
            null,
            null,
            null
            )
    }

    override fun onLoadFinished(p0: Loader<Cursor>, cursor: Cursor?) {
       simpleCursorAdapter.swapCursor(cursor)
    }

    override fun onLoaderReset(p0: Loader<Cursor>) {
        simpleCursorAdapter.swapCursor(null)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fromColumns = arrayOf(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY)

        val toViews = intArrayOf(R.id.tvName)

        simpleCursorAdapter = SimpleCursorAdapter(this,
            R.layout.item_contacts,
            null,
            fromColumns,
            toViews,
            0)

        lvContacts.adapter = simpleCursorAdapter
        /*if(checkSelfPermission(android.Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED){
            supportLoaderManager.initLoader(0, null, this)
        }else {
            if(shouldShowRequestPermissionRationale(android.Manifest.permission.READ_CONTACTS)){
                Toast.makeText(this,"Contact permission is needed",Toast.LENGTH_SHORT).show()
            }

            requestPermissions(new String[]{android.Manifest.permission.READ_CONTACTS},REQUEST_READ_CONTACTS)
        }*/

            supportLoaderManager.initLoader(100, null, this)

    }



}
