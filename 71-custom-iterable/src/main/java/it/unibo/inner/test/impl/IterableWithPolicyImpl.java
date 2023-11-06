package it.unibo.inner.test.impl;

import java.util.Iterator;
import java.util.function.Predicate;

import it.unibo.inner.api.IterableWithPolicy;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {

    private T[] newArray;

    public IterableWithPolicyImpl(T[] myArray) {
        this.newArray = myArray;
    }

    class MyIteratorImpl implements Iterator<T>{

        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < IterableWithPolicyImpl.this.newArray.length;
        }
    
        @Override
        public T next() {
            return IterableWithPolicyImpl.this.newArray[index++];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIteratorImpl();
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {

    }

}