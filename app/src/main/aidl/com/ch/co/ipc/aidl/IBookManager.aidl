package com.ch.co.ipc.aidl;

import com.ch.co.ipc.aidl.Book;
import com.ch.co.ipc.aidl.IOnNewBookArrivedListener;

interface IBookManager {

     // 从远程服务端获取图书列表
     List<Book> getBookList();

     // 往图书列表添加一本书
     void addBook(in Book book);
     void registerListener(IOnNewBookArrivedListener listener);
     void unregisterListener(IOnNewBookArrivedListener listener);
}