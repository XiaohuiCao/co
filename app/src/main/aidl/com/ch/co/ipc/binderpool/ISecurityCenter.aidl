package com.ch.co.ipc.binderpool;

interface ISecurityCenter {
    String encrypt(String content);
    String decrypt(String password);
}