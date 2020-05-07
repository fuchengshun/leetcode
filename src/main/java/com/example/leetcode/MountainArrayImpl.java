package com.example.leetcode;

public class MountainArrayImpl implements MountainArray {
    private int[] mountainArr;

    public MountainArrayImpl(int[] mountainArr) {
        this.mountainArr = mountainArr;
    }

    @Override
    public int get(int index) {
        return this.mountainArr[index];
    }

    @Override
    public int length() {
        return this.mountainArr.length;
    }
}
