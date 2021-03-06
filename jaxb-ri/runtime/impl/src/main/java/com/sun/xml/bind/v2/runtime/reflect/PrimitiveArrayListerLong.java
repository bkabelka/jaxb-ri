/*
 * Copyright (c) 1997, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package com.sun.xml.bind.v2.runtime.reflect;

import com.sun.xml.bind.api.AccessorException;
import com.sun.xml.bind.v2.runtime.XMLSerializer;

/**
 * {@link Lister} for primitive type arrays.
 * <p><b>
 *     Auto-generated, do not edit.
 * </b></p>
 * <p>
 *     B y t e ArrayLister is used as the master to generate the rest of the
 *     lister classes. Do not modify the generated copies.
 * </p>
 */
final class PrimitiveArrayListerLong<BeanT> extends Lister<BeanT,long[],Long,PrimitiveArrayListerLong.LongArrayPack> {
    
    private PrimitiveArrayListerLong() {
    }

    /*package*/ static void register() {
        Lister.primitiveArrayListers.put(Long.TYPE,new PrimitiveArrayListerLong());
    }

    public ListIterator<Long> iterator(final long[] objects, XMLSerializer context) {
        return new ListIterator<Long>() {
            int idx=0;
            public boolean hasNext() {
                return idx<objects.length;
            }

            public Long next() {
                return objects[idx++];
            }
        };
    }

    public LongArrayPack startPacking(BeanT current, Accessor<BeanT, long[]> acc) {
        return new LongArrayPack();
    }

    public void addToPack(LongArrayPack objects, Long o) {
        objects.add(o);
    }

    public void endPacking( LongArrayPack pack, BeanT bean, Accessor<BeanT,long[]> acc ) throws AccessorException {
        acc.set(bean,pack.build());
    }

    public void reset(BeanT o,Accessor<BeanT,long[]> acc) throws AccessorException {
        acc.set(o,new long[0]);
    }

    static final class LongArrayPack {
        long[] buf = new long[16];
        int size;

        void add(Long b) {
            if(buf.length==size) {
                // realloc
                long[] nb = new long[buf.length*2];
                System.arraycopy(buf,0,nb,0,buf.length);
                buf = nb;
            }
            if(b!=null)
                buf[size++] = b;
        }

        long[] build() {
            if(buf.length==size)
                // if we are lucky enough
                return buf;

            long[] r = new long[size];
            System.arraycopy(buf,0,r,0,size);
            return r;
        }
    }
}
