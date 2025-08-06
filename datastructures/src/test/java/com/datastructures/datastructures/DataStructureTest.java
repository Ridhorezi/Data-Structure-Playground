package com.datastructures.datastructures;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

public class DataStructureTest {

	// ==================== Pengujian ArrayDS ====================
	@Test
	public void testArrayDSBasicOperations() {

		System.out.println("\n=== Pengujian Array Dinamis ===");

		// Inisialisasi array dengan kapasitas awal 5
		DataStructure.ArrayDS array = new DataStructure.ArrayDS(5);

		// Pengujian penyisipan dan pengambilan data
		array.insert(0, 10);
		array.insert(1, 20);
		array.insert(2, 30);
		System.out.println(
				"Isi array setelah penyisipan: [" + array.get(0) + ", " + array.get(1) + ", " + array.get(2) + "]");
		assertEquals(10, array.get(0));
		assertEquals(20, array.get(1));
		assertEquals(30, array.get(2));
		assertEquals(3, array.size());

		// Pengujian fungsi resize (penambahan lebih dari kapasitas awal)
		for (int i = 3; i < 10; i++) {
			array.insert(i, i * 10);
		}
		System.out.println("Isi array setelah resize: size=" + array.size() + ", kapasitas bertambah");
		System.out.println("Elemen pada indeks 8: " + array.get(8));
		assertEquals(10, array.size());
		assertEquals(80, array.get(8));

		// Pengujian penghapusan elemen
		array.delete(1); // Hapus nilai 20 di indeks 1
		System.out.println("Isi array setelah hapus indeks 1: [" + array.get(0) + ", " + array.get(1) + ", ...]");
		assertEquals(30, array.get(1));
		assertEquals(9, array.size());

		// Pengujian pengecualian untuk indeks tidak valid
		try {
			array.insert(-1, 100);
			fail("Seharusnya melempar IndexOutOfBoundsException");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Berhasil menangkap exception untuk indeks tidak valid: " + e.getMessage());
		}
	}

	// ==================== Pengujian SingleLinkedList ====================
	@Test
	public void testSingleLinkedListOperations() {

		System.out.println("\n=== Pengujian Single Linked List ===");

		// Inisialisasi linked list kosong
		DataStructure.SingleLinkedList list = new DataStructure.SingleLinkedList();

		// Pengujian insertFirst (menambahkan di depan/head)
		list.insertFirst(30);
		list.insertFirst(20);
		list.insertFirst(10);

		System.out.println("List setelah insertFirst: size=" + list.size() + ", contains 20=" + list.contains(20)
				+ ", contains 40=" + list.contains(40));

		// Pengujian insertLast (menambahkan di akhir/tail)
		list.insertLast(40);
		list.insertLast(50);

		System.out.println("List setelah insertLast: size=" + list.size() + ", contains 50=" + list.contains(50));

		// Pengujian delete
		System.out.println("Menghapus elemen:");
		System.out.println("Delete 30: " + list.delete(30)); // tengah
		System.out.println("Delete 10: " + list.delete(10)); // head
		System.out.println("Delete 50: " + list.delete(50)); // tail
		System.out.println("Delete 99 (tidak ada): " + list.delete(99));

		System.out.println("List setelah delete: size=" + list.size());

		// Validasi dengan assertion
		assertEquals(2, list.size());
		assertTrue(list.contains(20));
		assertTrue(list.contains(40));
	}

	@Test
	public void testDoubleLinkedListOperations() {

		System.out.println("\n=== Testing Double Linked List ===");

		DataStructure.DoubleLinkedList list = new DataStructure.DoubleLinkedList();

		// Menyisipkan elemen di awal list: 10 -> 20 -> 30
		list.insertFirst(30);
		list.insertFirst(20);
		list.insertFirst(10);
		System.out.println("List after insertFirst: size=" + list.size()); // size = 3

		// Menambahkan elemen di akhir list: 10 -> 20 -> 30 -> 40 -> 50
		list.insertLast(40);
		list.insertLast(50);
		System.out.println("List after insertLast: size=" + list.size()); // size = 5

		// Menghapus beberapa elemen
		System.out.println("Deleting elements:");
		System.out.println("Delete 30 (middle): " + list.delete(30)); // true
		System.out.println("Delete 10 (head): " + list.delete(10)); // true
		System.out.println("Delete 50 (tail): " + list.delete(50)); // true
		System.out.println("Final size: " + list.size()); // size = 2

		// Mengecek apakah elemen tertentu ada di dalam list
		System.out.println("Contains check:");
		System.out.println("Contains 20: " + list.contains(20)); // true
		System.out.println("Contains 40: " + list.contains(40)); // true
		System.out.println("Contains 99: " + list.contains(99)); // false

		// Validasi akhir dengan assertion (JUnit)
		assertEquals(2, list.size());
		assertTrue(list.contains(20));
		assertTrue(list.contains(40));
		assertFalse(list.contains(99));
	}

	@Test
	public void testCircularLinkedListOperations() {

		System.out.println("\n=== Testing Circular Linked List ===");

		// Membuat objek CircularLinkedList
		DataStructure.CircularLinkedList list = new DataStructure.CircularLinkedList();

		// Menambahkan elemen: [10] -> [20] -> [30] -> kembali ke [10]
		list.insert(10);
		list.insert(20);
		list.insert(30);

		System.out.println("List after inserts: size=" + list.size()); // Output: size=3

		// Mengecek keberadaan nilai dalam list
		System.out.println("Contains check:");
		System.out.println("Contains 20: " + list.contains(20)); // true
		System.out.println("Contains 40: " + list.contains(40)); // false

		// Menghapus elemen satu per satu
		System.out.println("Deleting elements:");
		System.out.println("Delete 20 (middle): " + list.delete(20)); // true
		System.out.println("Delete 10 (head): " + list.delete(10)); // true
		System.out.println("Delete 30 (tail): " + list.delete(30)); // true

		System.out.println("List after deletes: size=" + list.size()); // Output: size=0

		// Validasi akhir menggunakan assertion
		assertEquals(0, list.size()); // Harus kosong
		assertFalse(list.contains(20)); // Tidak boleh ditemukan
	}

	@Test
	public void testArrayStackOperations() {

		System.out.println("\n=== Testing Array Stack ===");

		// Membuat stack baru dengan kapasitas 5 elemen
		DataStructure.ArrayStack stack = new DataStructure.ArrayStack(5);

		// Menambahkan elemen ke dalam stack
		stack.push(10);
		stack.push(20);
		stack.push(30);

		// Menampilkan informasi stack saat ini
		System.out.println(
				"Stack after pushes: top=" + stack.peek() + ", size=" + stack.size() + ", isFull=" + stack.isFull());

		// Menghapus (pop) elemen dari stack
		System.out.println("Popping elements:");
		System.out.println("Pop: " + stack.pop()); // Harusnya 30
		System.out.println("Next pop: " + stack.pop()); // Harusnya 20

		// Periksa ukuran dan status kosong
		System.out.println("Stack after pops: size=" + stack.size() + ", isEmpty=" + stack.isEmpty());

		// Menambahkan lebih banyak elemen untuk mencapai batas kapasitas
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4); // Kapasitas penuh di sini

		System.out.println("Stack before final push: isFull=" + stack.isFull());

		try {
			// Mencoba menambahkan elemen saat stack sudah penuh
			stack.push(5);
			fail("Should throw StackOverflowError"); // Harusnya error
		} catch (StackOverflowError e) {
			System.out.println("Caught expected StackOverflowError when stack is full");
		}

		// Mengosongkan stack sepenuhnya
		while (!stack.isEmpty()) {
			stack.pop();
		}

		try {
			// Mencoba menghapus elemen dari stack kosong
			stack.pop();
			fail("Should throw EmptyStackException"); // Harusnya error
		} catch (EmptyStackException e) {
			System.out.println("Caught expected EmptyStackException when stack is empty");
		}
	}

	@Test
	public void testArrayQueueOperations() {

		System.out.println("\n=== Testing Array Queue ===");

		// Membuat queue dengan kapasitas maksimum 5 elemen
		DataStructure.ArrayQueue queue = new DataStructure.ArrayQueue(5);

		// Enqueue beberapa elemen
		queue.enqueue(10);
		queue.enqueue(20);
		queue.enqueue(30);

		// Cek nilai di depan, ukuran, dan apakah penuh
		System.out.println("Queue after enqueues: front=" + queue.peek() + ", size=" + queue.size() + ", isFull="
				+ queue.isFull());

		// Dequeue beberapa elemen
		System.out.println("Dequeuing elements:");
		System.out.println("Dequeue: " + queue.dequeue()); // Harus mengeluarkan 10
		System.out.println("Next dequeue: " + queue.dequeue()); // Harus mengeluarkan 20

		// Cek ukuran dan apakah queue kosong
		System.out.println("Queue after dequeues: size=" + queue.size() + ", isEmpty=" + queue.isEmpty());

		// Uji perilaku circular (rear akan membungkus ke depan array)
		queue.enqueue(40);
		queue.enqueue(50);
		queue.enqueue(60);
		queue.enqueue(70); // Sekarang queue penuh

		System.out.println("Queue after filling: size=" + queue.size());
		System.out.println("Dequeue after wrap: " + queue.dequeue()); // Harus mengeluarkan 30

		// Coba enqueue saat penuh
		try {
			queue.enqueue(80); // Seharusnya melempar exception
			fail("Should throw IllegalStateException");
		} catch (IllegalStateException e) {
			System.out.println("Caught expected IllegalStateException when queue is full");
		}

		// Kosongkan queue sepenuhnya
		while (!queue.isEmpty()) {
			queue.dequeue();
		}

		// Coba dequeue saat kosong
		try {
			queue.dequeue(); // Seharusnya melempar exception
			fail("Should throw NoSuchElementException");
		} catch (NoSuchElementException e) {
			System.out.println("Caught expected NoSuchElementException when queue is empty");
		}
	}

	@Test
	public void testBinaryTreeOperations() {
		System.out.println("\n=== Testing Binary Tree ===");

		// Membuat tree baru
		DataStructure.BinaryTree tree = new DataStructure.BinaryTree();

		// Menyisipkan nilai ke dalam tree
		tree.insert(50);
		tree.insert(30);
		tree.insert(70);
		tree.insert(20);
		tree.insert(40);
		tree.insert(60);
		tree.insert(80);

		// Mengecek keberadaan elemen
		System.out.println("Tree contents check:");
		System.out.println("Contains 50: " + tree.contains(50)); // true
		System.out.println("Contains 20: " + tree.contains(20)); // true
		System.out.println("Contains 99: " + tree.contains(99)); // false

		// Menampilkan traversal in-order (hasil harus urut)
		List<Integer> traversal = tree.inOrderTraversal();
		System.out.println("In-order traversal: " + traversal);
		assertEquals(Arrays.asList(20, 30, 40, 50, 60, 70, 80), traversal);

		// Menghapus node satu per satu dan cek hasil traversal
		System.out.println("\nDeleting nodes:");

		// Hapus leaf node (tanpa anak)
		System.out.println("Deleting leaf node 20");
		tree.delete(20);
		System.out.println("In-order after delete 20: " + tree.inOrderTraversal());

		// Hapus node dengan satu anak
		System.out.println("Deleting node with one child 30");
		tree.delete(30);
		System.out.println("In-order after delete 30: " + tree.inOrderTraversal());

		// Hapus node dengan dua anak
		System.out.println("Deleting node with two children 70");
		tree.delete(70);
		System.out.println("In-order after delete 70: " + tree.inOrderTraversal());

		// Pastikan node yang dihapus tidak ada lagi
		assertFalse(tree.contains(20));
		assertFalse(tree.contains(30));
		assertFalse(tree.contains(70));
	}

	@Test
	public void testBinarySearchTreeOperations() {
		System.out.println("\n=== Testing Binary Search Tree ===");

		DataStructure.BinarySearchTree bst = new DataStructure.BinarySearchTree();

		// Menyisipkan nilai
		bst.insert(50);
		bst.insert(30);
		bst.insert(70);
		bst.insert(20);
		bst.insert(40);
		bst.insert(60);
		bst.insert(80);

		// Traversal in-order untuk memastikan urutan
		List<Integer> traversal = bst.inOrderTraversal();
		System.out.println("BST in-order traversal (should be sorted): " + traversal);
		assertEquals(Arrays.asList(20, 30, 40, 50, 60, 70, 80), traversal);

		// Pengecekan pencarian
		System.out.println("BST contains check:");
		System.out.println("Contains 60: " + bst.contains(60)); // true
		System.out.println("Contains 99: " + bst.contains(99)); // false
	}

	// ==================== MinHeap Tests ====================
	@Test
	public void testMinHeapOperations() {

		System.out.println("\n=== Testing Min Heap ===");

		DataStructure.MinHeap heap = new DataStructure.MinHeap(10);

		// Insert values
		heap.insert(30);
		heap.insert(10);
		heap.insert(50);
		heap.insert(5);
		heap.insert(20);

		System.out.println("Heap state:");
		System.out.println("Current min: " + heap.getMin());
		System.out.println("Heap size: " + heap.size());

		// Extract min
		System.out.println("\nExtracting minimum elements:");
		System.out.println("Extracted min: " + heap.extractMin());
		System.out.println("Next min: " + heap.extractMin());
		System.out.println("Heap size after extraction: " + heap.size());

		// Test heap full
		while (heap.size() < heap.size()) {
			heap.insert(heap.size() * 10);
		}
		try {
			heap.insert(100);
			fail("Should throw IllegalStateException");
		} catch (IllegalStateException e) {
			System.out.println("Caught expected IllegalStateException when heap is full");
		}

		// Test heap empty
		while (heap.size() > 0) {
			heap.extractMin();
		}
		try {
			heap.extractMin();
			fail("Should throw NoSuchElementException");
		} catch (NoSuchElementException e) {
			System.out.println("Caught expected NoSuchElementException when heap is empty");
		}
	}

	// ==================== Graph Tests ====================
	@Test
	public void testGraphOperations() {

		System.out.println("\n=== Testing Graph ===");

		DataStructure.Graph graph = new DataStructure.Graph(5);

		// Add edges
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 3);
		graph.addEdge(2, 4);

		System.out.println("Graph edges added: 0-1, 0-2, 1-3, 2-4");

		// Test BFS
		System.out.print("BFS traversal from 0: ");
		graph.bfs(0);
		System.out.println();

		// Test DFS
		System.out.print("DFS traversal from 0: ");
		graph.dfs(0);
		System.out.println();
	}

	// ==================== HashTable Tests ====================
	@Test
	public void testHashTableOperations() {

		System.out.println("\n=== Testing Hash Table ===");

		DataStructure.HashTable table = new DataStructure.HashTable(10);

		// Test put and get
		table.put("one", 1);
		table.put("two", 2);
		table.put("three", 3);

		System.out.println("Hash table contents:");
		System.out.println("Get 'one': " + table.get("one"));
		System.out.println("Get 'two': " + table.get("two"));
		System.out.println("Get 'three': " + table.get("three"));
		System.out.println("Size: " + table.size());

		// Test update
		table.put("two", 22);
		System.out.println("\nAfter updating 'two' to 22:");
		System.out.println("Get 'two': " + table.get("two"));

		// Test remove
		System.out.println("\nRemoving elements:");
		System.out.println("Remove 'two': " + table.remove("two"));
		System.out.println("Get 'two' after remove: " + table.get("two"));
		System.out.println("New size: " + table.size());

		// Test collision handling
		System.out.println("\nTesting collision handling:");
		table.put("a", 1);
		table.put("b", 2);
		table.put("c", 3);
		System.out.println("Get 'a': " + table.get("a"));
		System.out.println("Get 'b': " + table.get("b"));
		System.out.println("Get 'c': " + table.get("c"));
	}

	// ==================== BloomFilter Tests ====================
	@Test
	public void testBloomFilterOperations() {

		System.out.println("\n=== Testing Bloom Filter ===");

		DataStructure.BloomFilter filter = new DataStructure.BloomFilter(100, 3);

		// Add items
		filter.add("hello");
		filter.add("world");
		filter.add("bloom");
		filter.add("filter");

		System.out.println("Bloom filter membership test:");
		System.out.println("Check 'hello': " + filter.mightContain("hello"));
		System.out.println("Check 'world': " + filter.mightContain("world"));
		System.out.println("Check 'bloom': " + filter.mightContain("bloom"));
		System.out.println("Check 'filter': " + filter.mightContain("filter"));

		// Test non-existent items (possible false positive)
		System.out.println("\nTesting non-existent items:");
		System.out.println("Check 'missing': " + filter.mightContain("missing"));
		System.out.println("Check 'absent': " + filter.mightContain("absent"));

		// Verify no false negatives
		assertTrue(filter.mightContain("hello"));
		assertTrue(filter.mightContain("world"));
	}
}