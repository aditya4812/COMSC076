import java.util.ArrayList;

//Exercise 27.2 by Aditya Mehta

public class MyHashMap<K, V> implements MyMap<K, V> {
	// default hash-table size of 4
	private static int DEFAULT_INITIAL_CAPACITY = 4;

	// maximum hash-table size, 2^30
	private static int MAXIMUM_CAPACITY = (int) Math.pow(2, 30);

	private int capacity;

	private static float DEFAULT_MAX_LOAD_FACTOR = 0.5f;

	private float loadFactorThreshold;

	// number of entries in the map
	private int size = 0;

	ArrayList<MyMap.Entry<K, V>> table;

	public MyHashMap() {
		this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
	}

	public MyHashMap(int initialCapacity) {
		this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
	}

	public MyHashMap(int initial, float factor) {
		if (initial > MAXIMUM_CAPACITY) {
			this.capacity = MAXIMUM_CAPACITY;
		} else {
			this.capacity = trimToPowerOf2(initial);
		}
		this.loadFactorThreshold = factor;
		table = new ArrayList<>();
		int i = 0;
		while (i < capacity) {
			table.add(null);
			i++;
		}
	}

	@Override
	public void clear() {
		size = 0;
		removeEntries();
	}

	@Override
	public boolean containsKey(K key) {
		if (get(key) == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean containsValue(V value) {
		for (int i = 0; i < capacity; i++) {
			if (table.get(i) != null && table.get(i).getValue() == value)
				return true;
		}

		return false;
	}

	@Override
	public java.util.Set<MyMap.Entry<K, V>> entrySet() {
		java.util.Set<MyMap.Entry<K, V>> set = new java.util.HashSet<>();
		int i = 0;
		while (i < capacity) {
			if (table.get(i) != null) {
				set.add(table.get(i));
			}
			i++;
		}

		return set;
	}

	@Override
	public V get(K key) {

		int j = 0;
		int i = hash(key.hashCode());

		while (table.get(i) != null) {
			if (table.get(i).getKey().equals(key)) {
				return table.get(i).getValue();
			}
			i += Math.pow(j++, 2);
			i %= capacity;
		}

		return null;
	}

	@Override
	public boolean isEmpty() {

		if (size == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public java.util.Set<K> keySet() {
		java.util.Set<K> set = new java.util.HashSet<>();

		for (int i = 0; i < capacity; i++) {
			if (table.get(i) != null) {
				set.add(table.get(i).getKey());
			}
		}

		return set;
	}

	@Override
	public V put(K key, V value) {
		int i = hash(key.hashCode());
		int j = 0;

		while (table.get(i) != null) {

			if (table.get(i).getKey().equals(key)) {
				Entry<K, V> entry = table.get(i);
				V old = entry.getValue();

				entry.value = value;
				table.set(i, entry);

				return old;
			}

			i += Math.pow(j++, 2);
			i %= capacity;
		}

		if (size >= capacity * loadFactorThreshold) {
			if (capacity == MAXIMUM_CAPACITY) {
				throw new RuntimeException("Exceeding maximum capacity");
			}
			rehash();
		}

		table.set(i, new MyMap.Entry<K, V>(key, value));

		size = size + 1;

		return value;
	}

	@Override
	public void remove(K key) {
		int i = hash(key.hashCode());
		int j = 0;

		while (table.get(i) != null) {
			if (table.get(i).getKey().equals(key)) {
				table.remove(i);
				size = size - 1;
				break;
			}
			i += Math.pow(j++, 2);
			i %= capacity;
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public java.util.Set<V> values() {
		java.util.Set<V> set = new java.util.HashSet<>();

		for (int i = 0; i < capacity; i++) {
			if (table.get(i) != null) {
				set.add(table.get(i).getValue());
			}
		}

		return set;
	}

	private int hash(int hashCode) {
		return supplementalHash(hashCode) & (capacity - 1);
	}

	private static int supplementalHash(int h) {
		h ^= (h >>> 20) ^ (h >>> 12);
		return h ^ (h >>> 7) ^ (h >>> 4);
	}

	private int trimToPowerOf2(int initialCapacity) {
		int capacity = 1;
		while (capacity < initialCapacity) {
			capacity <<= 1;
		}

		return capacity;
	}

	private void removeEntries() {
		table.clear();
	}

	private void rehash() {
		java.util.Set<Entry<K, V>> set = entrySet();
		capacity <<= 1;
		size = 0;
		table.clear();
		for (int i = 0; i < capacity; i++)
			table.add(null);

		for (Entry<K, V> entry : set) {
			put(entry.getKey(), entry.getValue());
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("[");

		for (Entry<K, V> entry : table) {
			if (entry != null && table.size() > 0)
				builder.append(entry);
		}

		builder.append("]");
		return builder.toString();
	}

	// MAIN METHOD
	public static void main(String[] arg) {

		MyMap<String, Integer> m = new MyHashMap<>();
		m.put("Smith", 30);
		m.put("Anderson", 31);
		m.put("Lewis", 29);
		m.put("Cook", 29);
		m.put("Tom", 21);
		m.put("Mark", 21);
		m.put("Smith", 65);
		m.put("William", 21);

		System.out.println("Entries in m: " + m);
		System.out.println("The age of Lewis is " + m.get("Lewis"));
		System.out.println("The age of William is " + m.get("William"));
		System.out.println("Is Smith in the m? " + m.containsKey("Smith"));
		System.out.println("Is Jay in the m? " + m.containsKey("Jay"));
		System.out.println("Is age 33 in the m? " + m.containsValue(33));
		System.out.println("Is age 31 in the m? " + m.containsValue(31));
		System.out.print("Keys in m: ");
		for (String key : m.keySet()) {
			System.out.print(key + " ");
		}
		System.out.println();
		System.out.print("Values in m: ");
		for (int value : m.values()) {
			System.out.print(value + " ");
		}
		System.out.println();
		m.remove("Smith");
		System.out.println("Entries in m " + m);
		m.clear();
		System.out.println("Entries in m " + m);
	}

}