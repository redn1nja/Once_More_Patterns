package org.example.Selery;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Stamper<T> implements Visitor<T>{
    private static Integer onGroup;
    private final static List<Boolean> inGroup = new ArrayList<>();
    private static Integer ID = 1;
    private final static List<HashMap<String, String>> nested = new ArrayList<>();
    @Override
    public void onSignature(Task<T> sign) {
        sign.setHeader(sign.getId(), ID.toString());
        if(!inGroup.isEmpty()){
            HashMap<String, String> stack = nested.get(nested.size()-1);
            stack.put(sign.getId(), ID.toString());
            nested.remove(nested.size()-1);
            nested.add(stack);
        }
        ID++;
    }

    @Override
    public void onGroupStart(Task<T> group) {
        onGroup = ID;
        nested.add(new HashMap<String, String>());
        inGroup.add(true);
        ID = 1;
    }

    @Override @SneakyThrows
    public void onGroupEnd(Task<T> group) {
        ID = onGroup;
        HashMap<String, String> toPut = nested.get(nested.size()-1);
        nested.remove(nested.size()-1);
        if (!nested.isEmpty()) {
            HashMap<String, String> combines = nested.get(nested.size() - 1);
            nested.remove(nested.size() - 1);
            combines.putAll(toPut);
            nested.add(combines);
        }
        for(String key: toPut.keySet()){
            group.setHeader(key, toPut.get(key));
        }
        inGroup.remove(inGroup.size()-1);
    }
}
