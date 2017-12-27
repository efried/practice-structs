package org.friedenberg.structures

import java.util.ArrayList
import java.util.HashMap
import java.util.AbstractMap

class HashMultiMap<K, V> {
    internal var map: MutableMap<K, List<V>> = HashMap()
    internal var total = 0

    fun size(): Int {
        return total
    }

    val isEmpty: Boolean
        get() = total == 0

    internal operator fun get(key: K): Iterable<V> {
        val secondary = map[key]
        if (secondary != null) {
            return secondary
        }
        return ArrayList()
    }

    internal fun put(key: K, value: V) {
        var secondary: MutableList<V>? = map[key] as MutableList<V>?
        if (secondary == null) {
            secondary = ArrayList<V>()
            map.put(key, secondary)
        }
        secondary.add(value)
        total++
    }

    internal fun remove(key: K, value: V): Boolean {
        var wasRemoved = false
        var secondary: MutableList<V>? = map[key] as MutableList<V>?
        if (secondary != null) {
            wasRemoved = secondary.remove(value)
            if (wasRemoved) {
                total--
                if(secondary.isEmpty()) {
                    map.remove(key);
                }
            }
        }
        return wasRemoved
    }

    internal fun removeAll(key: K): Iterable<V> {
        var secondary: MutableList<V>? = map[key] as MutableList<V>?
        if (secondary != null) {
            total -= secondary.size
            map.remove(key)
        } else {
            secondary = ArrayList<V>()
        }
        return secondary;
    }

    internal fun entries(): Iterable<Map.Entry<K,V>> {
        var result = ArrayList<Map.Entry<K,V>>()
        for(secondary: Map.Entry<K, List<V>> in map.entries) {
            val key = secondary.key
            for(value: V in secondary.value) {
                result.add(AbstractMap.SimpleEntry<K,V>(key, value))
            }
        }
        return result
    }
}
