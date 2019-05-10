package com.yy.testruleonline.utils;

import java.util.ArrayList;
import java.util.HashMap;

public class RuleHashMap<K> extends HashMap<K, Object> {
    public void putList(K key, Object value) {
        Object data = get(key);
        if (data == null) {
            ArrayList<Object> dataList = new ArrayList<>();
            dataList.add(value);
            put(key, dataList);
        } else if (data instanceof ArrayList) {
            ((ArrayList) data).add(value);
          
        } else {
            throw new RuntimeException("data put error");
        }

    }

}
