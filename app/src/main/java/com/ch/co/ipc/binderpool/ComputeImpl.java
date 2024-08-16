package com.ch.co.ipc.binderpool;

import com.ch.co.ipc.binderpool.ICompute;

public class ComputeImpl extends ICompute.Stub {

    @Override
    public int add(int a, int b) {
        return a + b;
    }

}
