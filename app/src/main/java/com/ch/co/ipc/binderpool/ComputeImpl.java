package com.ch.co.ipc.binderpool;

public class ComputeImpl extends ICompute.Stub {

    @Override
    public int add(int a, int b) {
        return a + b;
    }

}
