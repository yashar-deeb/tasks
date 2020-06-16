package com.company;

import java.util.Map;
import java.util.Set;

public class ManagerListDirectionItem {
    private SimpleHashMap<String, DirectionItem> directionItemMap = new SimpleHashMap<String, DirectionItem>(16);

    public DirectionItem getItem(String key) {
        return directionItemMap.get(key);
    }

    public void addItem(DirectionItem item) { directionItemMap.put(item.prefix, item); }

    public void removeItem(String key) {
        directionItemMap.remove(key);
    }

    public int getSize() {
        return directionItemMap.size();
    }

    public Set<Map.Entry<String, DirectionItem>> getEntrySet() {
        return directionItemMap.entrySet();
    }

    public boolean containsKey(String key) { return directionItemMap.containsKey(key); }
}
