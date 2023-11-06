package it.unibo.inner.test.impl;

import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import it.unibo.inner.api.IterableWithPolicy;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {

    private T[] newArray;
    Predicate<T> myPredicate;

    public IterableWithPolicyImpl(T[] myArray) {
        this(myArray, new Predicate<T>() {
            @Override
            public boolean test(T elem) {
                return true;
            }
        });
    }

    public IterableWithPolicyImpl(T[] myArray, Predicate<T> myPredicate) {
        this.newArray = myArray;
        this.myPredicate = myPredicate;
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.myPredicate = filter;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIteratorImpl();
    }

    class MyIteratorImpl implements Iterator<T>{

        private int index = 0;

        @Override
        public boolean hasNext() {
            while (this.index < newArray.length) {
                T elem = newArray[this.index];
                if (myPredicate.test(elem)) {
                    return true;
                }
                this.index++;
            }
            return false;
        }

        @Override
        public T next() {
            if(hasNext()) {
                T tempElem = newArray[this.index];
                this.index++;
                return tempElem;
            }
            throw new NoSuchElementException();
        }
    }

    

    

}