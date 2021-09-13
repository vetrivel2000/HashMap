package myhash;

import pojo.Entry;

import java.util.LinkedList;
import java.util.Objects;

public class HashMapHandler<K,V> {
    LinkedList<Entry<K,V>> map[];
    int size=0,currentCapacity=16;
    public HashMapHandler()
    {
        map=new LinkedList[currentCapacity];
    }
    public void put(K key, V value) {
        if (size == currentCapacity) {
            resize();
        }

        int index = Objects.hash(key) % currentCapacity;

        if (map[index] == null) {
            map[index] = new LinkedList<>();
        }

        for (Entry<K, V> entry1 : map[index]) {
            if (entry1.getKey().equals(key)) {
                entry1.setValue(value);
                return;
            }
        }

        Entry<K, V> entry = new Entry<>(key, value);
        map[index].add(entry);
        size++;
    }
    public V get(K key)
    {
        int index=Objects.hash(key)%currentCapacity;
        if(map[index]==null)
        {
            return null;
        }
        for (Entry<K, V> entry1 : map[index]) {
            if (entry1.getKey().equals(key)) {
                return entry1.getValue();
            }
        }
        return null;
    }
    public boolean containsKey(K key) {
        int index = Objects.hash(key) % currentCapacity;

        if (map[index] == null) {
            return false;
        }
        for (Entry<K, V> entry1 : map[index]) {
            if (entry1.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }
    public void remove(K key) {
        int index = Objects.hash(key) % currentCapacity;

        if (map[index] == null) {
            return;
        }

        for (int i = 0; i < map[index].size(); i++) {
            Entry<K, V> entry = map[index].get(i);
            if (entry.getKey().equals(key)) {
                map[index].remove(entry);
            }
        }
    }
    private void resize() {

        LinkedList<Entry<K, V>>[] oldMap = map;
        map = new LinkedList[currentCapacity*2];
        size = 0;
        for (int i = 0; i < oldMap.length; i++) {
            if (oldMap[i] != null) {
                for (Entry<K, V> entry: oldMap[i]) {
                    put(entry.getKey(), entry.getValue());
                }
            }
        }
        currentCapacity*=2;
    }
}
