package com.example.atmf.daggerdemo.entry;

/**
 * Created by edz on 2018/three/28.
 */

public class EntryC {
    EntryB entryB;

    public EntryC(EntryB entryB) {
        this.entryB = entryB;
    }

    public EntryB getEntryB() {
        return entryB;
    }
}
