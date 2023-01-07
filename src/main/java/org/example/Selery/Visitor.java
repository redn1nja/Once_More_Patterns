package org.example.Selery;

public interface Visitor<T> {
//    private static Integer ID = 0;
    void onSignature(Task<T> sign);
    void onGroupStart(Task<T> group);
    void onGroupEnd(Task<T> group);
}
