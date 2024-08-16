package com.ch.co.ipc.aidl;

import com.ch.co.ipc.aidl.Book;

interface IOnNewBookArrivedListener {
    void onNewBookArrived(in Book newBook);
}
