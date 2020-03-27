package com.company;

import java.util.ArrayList;
import java.util.List;

public class ManagerListDirectionItem {
    private List<DirectionItem> directionItemList = new ArrayList<>();

    public DirectionItem getItem(int index) {
        return directionItemList.get(index);
    }

    public void setItem(int index, DirectionItem item) {
        directionItemList.set(index, item);
    }

    public void addItem(DirectionItem item) {
        directionItemList.add(item);
    }

    public void removeItem(int index) {
        directionItemList.remove(index);
    }

    public int getSize() {
        return directionItemList.size();
    }
}
