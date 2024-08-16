package com.ch.co.ipc.aidl;

import com.ch.co.ipc.aidl.Book;
import com.ch.co.ipc.aidl.IOnNewBookArrivedListener;

interface IBookManager {
     List<Book> getBookList();
     void addBook(in Book book);
     void registerListener(IOnNewBookArrivedListener listener);
     void unregisterListener(IOnNewBookArrivedListener listener);
}