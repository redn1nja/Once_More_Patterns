package org.example.Selery;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

public class Stamper<T> implements Visitor<T>{
    private static Integer onGroup;
    private static boolean inGroup = false;
    private static Integer ID = 1;
    private static List<String> stack = new ArrayList<String>();
    private static int counter = 0;
    @Override
    public void onSignature(Task<T> sign) {
        sign.setHeader(ID.toString(), sign.getId());
        if(inGroup){
            stack.add(sign.getId());
            stack.add(ID.toString());
            counter ++;
        }
        ID++;
    }

    @Override
    public void onGroupStart(Task<T> group) {
        onGroup = ID;
        if(inGroup){

        }
        inGroup = true;
        ID = 1;
    }

    @Override @SneakyThrows
    public void onGroupEnd(Task<T> group) {
        ID = onGroup;
        while (counter!=0){
            String toSetID = stack.get(stack.size()-1);
            stack.remove(stack.size()-1);
            String toSetUUID = stack.get(stack.size()-1);
            stack.remove(stack.size()-1);
            group.setHeader(toSetID, toSetUUID);
            counter--;
        }
        inGroup = false;
    }
}
