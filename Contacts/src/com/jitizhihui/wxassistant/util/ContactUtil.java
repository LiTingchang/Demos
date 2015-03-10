
package com.jitizhihui.wxassistant.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.PhoneLookup;
import android.provider.ContactsContract.RawContacts;
import android.util.Log;

import com.jitizhihui.wxassistant.constants.Constants;
import com.jitizhihui.wxassistant.mode.Contact;

public class ContactUtil {

    public static boolean insertContact(ContentResolver contactAdder,
            String firstName, String mobileNumber) {
        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
        ops.add(ContentProviderOperation
                .newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .build());
        ops.add(ContentProviderOperation
                .newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(
                        ContactsContract.Data.MIMETYPE,
                        ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(
                        ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME,
                        firstName).build());
        ops.add(ContentProviderOperation
                .newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(
                        ContactsContract.Data.MIMETYPE,
                        ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER,
                        mobileNumber)
                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
                        Phone.TYPE_MOBILE).build());

        try {
            contactAdder.applyBatch(ContactsContract.AUTHORITY, ops);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public static void deleteContact(ContentResolver contactHelper,
            String number) {

        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
        String[] args = new String[] {
                String.valueOf(getContactID(
                        contactHelper, number))
        };

        ops.add(ContentProviderOperation.newDelete(RawContacts.CONTENT_URI)
                .withSelection(RawContacts.CONTACT_ID + "=?", args).build());
        try {
            contactHelper.applyBatch(ContactsContract.AUTHORITY, ops);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        }
    }

    private static long getContactID(ContentResolver contactHelper,
            String number) {
        Uri contactUri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI,
                Uri.encode(number));

        String[] projection = {
                PhoneLookup._ID,
                PhoneLookup.DISPLAY_NAME
        };
        Cursor cursor = null;

        try {
            cursor = contactHelper.query(contactUri, projection, null, null,
                    null);

            if (cursor.moveToFirst()) {
                int personID = cursor.getColumnIndex(PhoneLookup._ID);
                int personName = cursor.getColumnIndex(PhoneLookup.DISPLAY_NAME);

                // TODO
                if (!cursor.getString(personName).startsWith(Constants.NAME_START)) {
                    return -1;
                }

                return cursor.getLong(personID);
            }

            return -1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
                cursor = null;
            }
        }

        return -1;
    }

    public static List<String> getpreExistingNumbers(Context context) {
        List<String> preExistingNumberList = new ArrayList<String>();

        Cursor phones = context.getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

        try {
            while (phones.moveToNext())
            {
                String name = phones.getString(phones
                        .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phoneNumber = phones.getString(phones
                        .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                preExistingNumberList.add(phoneNumber);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            phones.close();
        }

        return preExistingNumberList;
    }

    public static Map<String, String> getContacts(Context context) {
        Map<String, String> preExistingNumberList = new HashMap<String, String>();

        Cursor phones = context.getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

        try {
            while (phones.moveToNext())
            {
                String name = phones.getString(phones
                        .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phoneNumber = phones.getString(phones
                        .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                preExistingNumberList.put(name, phoneNumber);
            }
        } catch (Exception e) {
        } finally {
            phones.close();
        }

        return preExistingNumberList;
    }
    
    
    public static List<Contact> getContactsList(Context context) {
        List<Contact> contacts = new ArrayList<Contact>();

        Cursor phones = context.getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

        try {
            while (phones.moveToNext())
            {
                String name = phones.getString(phones
                        .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phoneNumber = phones.getString(phones
                        .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                
                if(!name.startsWith(Constants.NAME_START)) {
                    contacts.add(new Contact(name, phoneNumber));
                }
            }
        } catch (Exception e) {
        } finally {
            phones.close();
        }

        return contacts;
    }
}
