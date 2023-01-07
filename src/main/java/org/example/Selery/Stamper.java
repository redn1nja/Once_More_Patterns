package org.example.Selery;

public class Stamper<T> implements Visitor<T>{
    private static Integer onGroup;
    private static Integer ID = 1;
    @Override
    public void onSignature(Task<T> sign) {
        sign.setHeader(ID.toString(), String.format("signature #%d", ID));
        ID++;
    }

    @Override
    public void onGroupStart(Task<T> group) {
        onGroup = ID;
        ID = 1;
        group.setHeader(onGroup.toString(), String.format("group #%d", onGroup ));
        onGroup++;
    }

    @Override
    public void onGroupEnd(Task<T> group) {
        ID = onGroup;

    }
}
