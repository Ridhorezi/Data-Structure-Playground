package com.datastructures.datastructures;

import java.util.*;

public class DataStructure {

	/**
	 * Implementasi Array Dinamis: - Array yang dapat berubah ukuran secara otomatis
	 * saat penuh. - Akses elemen cepat (O(1)). - Penyisipan/penghapusan butuh O(n)
	 * karena perlu geser elemen. - Penyisipan di akhir bersifat amortized O(1)
	 * karena resize hanya terjadi sesekali.
	 */
	public static class ArrayDS {
		private int[] array; // Array utama untuk menyimpan data
		private int size; // Jumlah elemen saat ini dalam array

		/**
		 * Konstruktor untuk inisialisasi array dengan kapasitas awal.
		 * 
		 * @param capacity Kapasitas awal array
		 */
		public ArrayDS(int capacity) {
			array = new int[capacity];
			size = 0;
		}

		/**
		 * Menyisipkan nilai pada indeks tertentu.
		 * 
		 * @param index Indeks tujuan (0-based)
		 * @param value Nilai yang akan disisipkan
		 * @throws IndexOutOfBoundsException jika indeks tidak valid
		 */
		public void insert(int index, int value) {
			if (index < 0 || index > size) {
				throw new IndexOutOfBoundsException();
			}
			if (size == array.length) {
				resize(); // Jika penuh, gandakan kapasitas
			}
			// Geser elemen ke kanan untuk memberi ruang
			for (int i = size; i > index; i--) {
				array[i] = array[i - 1];
			}
			array[index] = value;
			size++;
		}

		/**
		 * Mengambil nilai dari indeks tertentu.
		 * 
		 * @param index Indeks elemen yang ingin diambil
		 * @return Nilai pada indeks tersebut
		 * @throws IndexOutOfBoundsException jika indeks tidak valid
		 */
		public int get(int index) {
			if (index < 0 || index >= size) {
				throw new IndexOutOfBoundsException();
			}
			return array[index];
		}

		/**
		 * Menghapus nilai pada indeks tertentu.
		 * 
		 * @param index Indeks elemen yang ingin dihapus
		 * @throws IndexOutOfBoundsException jika indeks tidak valid
		 */
		public void delete(int index) {
			if (index < 0 || index >= size) {
				throw new IndexOutOfBoundsException();
			}
			// Geser elemen ke kiri untuk mengisi celah
			for (int i = index; i < size - 1; i++) {
				array[i] = array[i + 1];
			}
			size--;
		}

		/**
		 * Menggandakan kapasitas array saat penuh.
		 */
		private void resize() {
			int[] newArray = new int[array.length * 2];
			System.arraycopy(array, 0, newArray, 0, array.length);
			array = newArray;
		}

		/**
		 * Mengembalikan jumlah elemen saat ini dalam array.
		 * 
		 * @return Ukuran array
		 */
		public int size() {
			return size;
		}
	}

	/**
	 * Implementasi Single Linked List: - Setiap node menyimpan data dan referensi
	 * ke node berikutnya. - Penyisipan/penghapusan di awal (head) cepat → O(1). -
	 * Penyisipan di akhir (tail) dan pencarian elemen → O(n).
	 */
	public static class SingleLinkedList {
		private Node head; // Node pertama (head) dari linked list
		private int size; // Jumlah elemen saat ini dalam list

		// Kelas Node (inner class) yang menyimpan data dan referensi ke node berikutnya
		private static class Node {
			int data;
			Node next;

			Node(int data) {
				this.data = data;
				this.next = null;
			}
		}

		// Konstruktor inisialisasi list kosong
		public SingleLinkedList() {
			head = null;
			size = 0;
		}

		/**
		 * Menambahkan elemen di awal linked list (sebagai head baru)
		 * 
		 * @param value Nilai yang akan ditambahkan
		 */
		public void insertFirst(int value) {
			Node newNode = new Node(value);
			newNode.next = head; // Node baru menunjuk ke head lama
			head = newNode; // Jadikan node baru sebagai head
			size++;
		}

		/**
		 * Menambahkan elemen di akhir linked list
		 * 
		 * @param value Nilai yang akan ditambahkan
		 */
		public void insertLast(int value) {
			Node newNode = new Node(value);
			if (head == null) {
				head = newNode; // Jika list kosong, node menjadi head
			} else {
				Node current = head;
				while (current.next != null) {
					current = current.next; // Iterasi ke node terakhir
				}
				current.next = newNode; // Tambahkan node baru di akhir
			}
			size++;
		}

		/**
		 * Menghapus node pertama yang memiliki nilai tertentu
		 * 
		 * @param value Nilai yang ingin dihapus
		 * @return true jika berhasil dihapus, false jika tidak ditemukan
		 */
		public boolean delete(int value) {
			if (head == null)
				return false;

			// Kasus khusus: nilai yang dihapus ada di head
			if (head.data == value) {
				head = head.next;
				size--;
				return true;
			}

			Node current = head;
			// Cari node sebelum node yang ingin dihapus
			while (current.next != null && current.next.data != value) {
				current = current.next;
			}

			if (current.next != null) {
				current.next = current.next.next; // Lewati node yang ingin dihapus
				size--;
				return true;
			}

			return false; // Nilai tidak ditemukan
		}

		/**
		 * Mengecek apakah nilai tertentu ada dalam linked list
		 * 
		 * @param value Nilai yang ingin dicari
		 * @return true jika ditemukan, false jika tidak
		 */
		public boolean contains(int value) {
			Node current = head;
			while (current != null) {
				if (current.data == value) {
					return true;
				}
				current = current.next;
			}
			return false;
		}

		/**
		 * Mengembalikan jumlah elemen saat ini dalam list
		 * 
		 * @return Ukuran linked list
		 */
		public int size() {
			return size;
		}
	}

	/**
	 * Implementasi Doubly Linked List - Setiap node memiliki data dan referensi ke
	 * node sebelumnya (prev) dan berikutnya (next) - Waktu akses: O(1) untuk
	 * penyisipan/penghapusan di awal dan akhir - Waktu pencarian: O(n)
	 */
	public static class DoubleLinkedList {
		private Node head; // Menunjuk ke elemen pertama dalam list
		private Node tail; // Menunjuk ke elemen terakhir dalam list
		private int size; // Ukuran list (jumlah elemen)

		// Kelas Node sebagai elemen dari linked list
		private static class Node {
			int data; // Menyimpan nilai
			Node prev; // Menunjuk ke node sebelumnya
			Node next; // Menunjuk ke node selanjutnya

			Node(int data) {
				this.data = data;
				this.prev = null;
				this.next = null;
			}
		}

		// Konstruktor untuk membuat linked list kosong
		public DoubleLinkedList() {
			head = null;
			tail = null;
			size = 0;
		}

		/**
		 * Menambahkan nilai di awal list (head)
		 */
		public void insertFirst(int value) {
			Node newNode = new Node(value);
			if (head == null) {
				// Jika list kosong, head dan tail sama-sama menunjuk ke newNode
				head = tail = newNode;
			} else {
				// Sisipkan di depan dan update head
				newNode.next = head;
				head.prev = newNode;
				head = newNode;
			}
			size++;
		}

		/**
		 * Menambahkan nilai di akhir list (tail)
		 */
		public void insertLast(int value) {
			Node newNode = new Node(value);
			if (tail == null) {
				// Jika list kosong
				head = tail = newNode;
			} else {
				// Tambahkan di belakang dan update tail
				tail.next = newNode;
				newNode.prev = tail;
				tail = newNode;
			}
			size++;
		}

		/**
		 * Menghapus node pertama yang memiliki nilai tertentu
		 */
		public boolean delete(int value) {
			if (head == null)
				return false; // List kosong

			// Kasus khusus: jika node pertama (head) yang dihapus
			if (head.data == value) {
				if (head == tail) {
					// Hanya satu elemen
					head = tail = null;
				} else {
					head = head.next;
					head.prev = null;
				}
				size--;
				return true;
			}

			// Kasus khusus: jika node terakhir (tail) yang dihapus
			if (tail.data == value) {
				tail = tail.prev;
				tail.next = null;
				size--;
				return true;
			}

			// Cari node yang akan dihapus
			Node current = head.next;
			while (current != null && current.data != value) {
				current = current.next;
			}

			if (current != null) {
				// Hubungkan node sebelum dan sesudah node yang dihapus
				current.prev.next = current.next;
				current.next.prev = current.prev;
				size--;
				return true;
			}

			return false; // Data tidak ditemukan
		}

		/**
		 * Mengecek apakah nilai tertentu ada di dalam list
		 */
		public boolean contains(int value) {
			Node current = head;
			while (current != null) {
				if (current.data == value) {
					return true;
				}
				current = current.next;
			}
			return false;
		}

		/**
		 * Mengembalikan jumlah elemen dalam list
		 */
		public int size() {
			return size;
		}
	}

	/**
	 * Implementasi Circular Linked List (Linked List Melingkar) - Node terakhir
	 * menunjuk kembali ke node pertama - Operasi insert/delete di head atau tail:
	 * O(1) - Operasi pencarian: O(n)
	 */
	public static class CircularLinkedList {
		private Node tail; // Menyimpan node terakhir; tail.next menunjuk ke head
		private int size;

		// Kelas Node mewakili elemen di dalam list
		private static class Node {
			int data; // Nilai data yang disimpan
			Node next; // Referensi ke node berikutnya

			Node(int data) {
				this.data = data;
			}
		}

		// Konstruktor: membuat list kosong
		public CircularLinkedList() {
			tail = null;
			size = 0;
		}

		/**
		 * Menambahkan nilai di akhir list (sekaligus jadi tail baru)
		 */
		public void insert(int value) {
			Node newNode = new Node(value);
			if (tail == null) {
				// Jika list kosong, node menunjuk ke dirinya sendiri (membentuk lingkaran)
				tail = newNode;
				tail.next = tail;
			} else {
				// Sisipkan setelah tail, dan update tail ke node baru
				newNode.next = tail.next; // newNode.next menunjuk ke head
				tail.next = newNode; // tail lama menunjuk ke newNode
				tail = newNode; // tail sekarang adalah newNode
			}
			size++;
		}

		/**
		 * Menghapus node pertama yang ditemukan dengan nilai tertentu
		 */
		public boolean delete(int value) {
			if (tail == null)
				return false; // List kosong

			Node current = tail.next; // Mulai dari head
			Node prev = tail; // Mulai dari tail

			do {
				if (current.data == value) {
					if (current == current.next) {
						// Hanya satu node di dalam list
						tail = null;
					} else {
						// Menghapus node selain satu-satunya
						prev.next = current.next;
						if (current == tail) {
							// Jika node yang dihapus adalah tail
							tail = prev;
						}
					}
					size--;
					return true;
				}
				// Geser ke node berikutnya
				prev = current;
				current = current.next;
			} while (current != tail.next); // Sampai kembali ke head

			return false; // Tidak ditemukan
		}

		/**
		 * Mengecek apakah nilai tertentu ada di dalam list
		 */
		public boolean contains(int value) {
			if (tail == null)
				return false; // List kosong

			Node current = tail.next; // Mulai dari head
			do {
				if (current.data == value) {
					return true;
				}
				current = current.next;
			} while (current != tail.next); // Periksa sampai kembali ke head

			return false;
		}

		/**
		 * Mengembalikan jumlah elemen dalam list
		 */
		public int size() {
			return size;
		}
	}

	/**
	 * Implementasi Struktur Data Stack (LIFO - Last In First Out) - Menggunakan
	 * array sebagai penyimpanan - Operasi push, pop, dan peek memiliki kompleksitas
	 * O(1) - Kapasitas tetap (akan melempar exception jika penuh)
	 */
	public static class ArrayStack {
		private int[] stack; // Array untuk menyimpan elemen-elemen stack
		private int top; // Menunjuk indeks elemen paling atas
		private int capacity; // Kapasitas maksimum stack

		/**
		 * Konstruktor: menginisialisasi stack dengan kapasitas tertentu
		 *
		 * @param capacity jumlah maksimum elemen yang dapat disimpan
		 */
		public ArrayStack(int capacity) {
			this.capacity = capacity;
			stack = new int[capacity]; // Membuat array dengan ukuran tetap
			top = -1; // Stack kosong ditandai dengan top = -1
		}

		/**
		 * Menambahkan elemen ke atas stack
		 *
		 * @param value nilai yang ingin dimasukkan
		 * @throws StackOverflowError jika stack sudah penuh
		 */
		public void push(int value) {
			if (isFull()) {
				throw new StackOverflowError(); // Stack tidak bisa menampung lebih banyak elemen
			}
			stack[++top] = value; // Naikkan top, lalu simpan nilai
		}

		/**
		 * Menghapus dan mengembalikan elemen paling atas dari stack
		 *
		 * @return elemen paling atas
		 * @throws EmptyStackException jika stack kosong
		 */
		public int pop() {
			if (isEmpty()) {
				throw new EmptyStackException(); // Tidak bisa menghapus dari stack kosong
			}
			return stack[top--]; // Ambil nilai, lalu turunkan top
		}

		/**
		 * Mengembalikan elemen paling atas tanpa menghapusnya
		 *
		 * @return elemen paling atas
		 * @throws EmptyStackException jika stack kosong
		 */
		public int peek() {
			if (isEmpty()) {
				throw new EmptyStackException();
			}
			return stack[top]; // Kembalikan nilai tanpa mengubah top
		}

		/**
		 * Mengecek apakah stack kosong
		 *
		 * @return true jika kosong
		 */
		public boolean isEmpty() {
			return top == -1;
		}

		/**
		 * Mengecek apakah stack sudah penuh
		 *
		 * @return true jika penuh
		 */
		public boolean isFull() {
			return top == capacity - 1;
		}

		/**
		 * Mengembalikan jumlah elemen saat ini dalam stack
		 */
		public int size() {
			return top + 1;
		}
	}

	/**
	 * Implementasi Struktur Data Queue (FIFO - First In First Out) - Menggunakan
	 * array melingkar (circular array) - Operasi enqueue dan dequeue memiliki
	 * kompleksitas O(1) - Memiliki kapasitas tetap (melempar exception jika penuh)
	 */
	public static class ArrayQueue {
		private int[] queue; // Array untuk menyimpan elemen queue
		private int front; // Indeks elemen paling depan (untuk dequeue/peek)
		private int rear; // Indeks elemen paling belakang (tempat enqueue)
		private int size; // Jumlah elemen saat ini
		private int capacity; // Kapasitas maksimum queue

		/**
		 * Konstruktor: inisialisasi queue dengan kapasitas tertentu
		 *
		 * @param capacity Jumlah maksimum elemen dalam queue
		 */
		public ArrayQueue(int capacity) {
			this.capacity = capacity;
			queue = new int[capacity];
			front = 0;
			rear = -1; // Menandakan bahwa queue masih kosong
			size = 0;
		}

		/**
		 * Menambahkan elemen ke bagian belakang queue (enqueue)
		 *
		 * @param value Nilai yang ingin dimasukkan
		 * @throws IllegalStateException jika queue sudah penuh
		 */
		public void enqueue(int value) {
			if (isFull()) {
				throw new IllegalStateException(); // Tidak bisa enqueue jika penuh
			}
			rear = (rear + 1) % capacity; // Pergerakan rear secara melingkar
			queue[rear] = value;
			size++;
		}

		/**
		 * Menghapus dan mengembalikan elemen dari bagian depan queue (dequeue)
		 *
		 * @return Elemen paling depan
		 * @throws NoSuchElementException jika queue kosong
		 */
		public int dequeue() {
			if (isEmpty()) {
				throw new NoSuchElementException(); // Tidak bisa dequeue jika kosong
			}
			int value = queue[front];
			front = (front + 1) % capacity; // Pergerakan front secara melingkar
			size--;
			return value;
		}

		/**
		 * Mengembalikan elemen paling depan tanpa menghapusnya (peek)
		 *
		 * @return Elemen paling depan
		 * @throws NoSuchElementException jika queue kosong
		 */
		public int peek() {
			if (isEmpty()) {
				throw new NoSuchElementException();
			}
			return queue[front];
		}

		/**
		 * Mengecek apakah queue kosong
		 *
		 * @return true jika kosong
		 */
		public boolean isEmpty() {
			return size == 0;
		}

		/**
		 * Mengecek apakah queue sudah penuh
		 *
		 * @return true jika penuh
		 */
		public boolean isFull() {
			return size == capacity;
		}

		/**
		 * Mengembalikan jumlah elemen saat ini dalam queue
		 *
		 * @return ukuran queue
		 */
		public int size() {
			return size;
		}
	}

	/**
	 * Implementasi Binary Tree - Setiap node memiliki maksimal dua anak (left dan
	 * right) - Operasi dasar: insert (sisip), search (cari), delete (hapus),
	 * traversal (penelusuran)
	 */
	public static class BinaryTree {
		private TreeNode root;

		// Kelas untuk node dalam tree
		public static class TreeNode {
			int data;
			TreeNode left;
			TreeNode right;

			TreeNode(int data) {
				this.data = data;
				this.left = null;
				this.right = null;
			}
		}

		// Konstruktor: membuat tree kosong
		public BinaryTree() {
			root = null;
		}

		/**
		 * Menyisipkan nilai ke dalam binary tree
		 *
		 * @param value Nilai yang akan dimasukkan
		 */
		public void insert(int value) {
			root = insertRec(root, value);
		}

		// Fungsi rekursif untuk menyisipkan node
		private TreeNode insertRec(TreeNode root, int value) {
			if (root == null) {
				return new TreeNode(value); // Sisipkan sebagai node baru
			}

			if (value < root.data) {
				root.left = insertRec(root.left, value); // Sisip ke kiri jika lebih kecil
			} else if (value > root.data) {
				root.right = insertRec(root.right, value); // Sisip ke kanan jika lebih besar
			}

			return root;
		}

		/**
		 * Mengecek apakah sebuah nilai ada di dalam tree
		 *
		 * @param value Nilai yang dicari
		 * @return true jika ditemukan, false jika tidak
		 */
		public boolean contains(int value) {
			return containsRec(root, value);
		}

		// Fungsi rekursif untuk pencarian nilai
		private boolean containsRec(TreeNode root, int value) {
			if (root == null)
				return false;
			if (value == root.data)
				return true;
			return value < root.data ? containsRec(root.left, value) : containsRec(root.right, value);
		}

		/**
		 * Menghapus node dengan nilai tertentu dari tree
		 *
		 * @param value Nilai yang ingin dihapus
		 */
		public void delete(int value) {
			root = deleteRec(root, value);
		}

		// Fungsi rekursif untuk menghapus node
		private TreeNode deleteRec(TreeNode root, int value) {
			if (root == null)
				return null;

			if (value < root.data) {
				root.left = deleteRec(root.left, value); // Cari di sisi kiri
			} else if (value > root.data) {
				root.right = deleteRec(root.right, value); // Cari di sisi kanan
			} else {
				// Node ditemukan

				// Kasus 1: tidak punya anak / hanya 1 anak
				if (root.left == null)
					return root.right;
				else if (root.right == null)
					return root.left;

				// Kasus 2: punya dua anak
				root.data = minValue(root.right); // Gantikan dengan nilai terkecil dari subtree kanan
				root.right = deleteRec(root.right, root.data); // Hapus nilai pengganti dari subtree kanan
			}
			return root;
		}

		// Mendapatkan nilai minimum dari subtree (paling kiri)
		private int minValue(TreeNode root) {
			int min = root.data;
			while (root.left != null) {
				min = root.left.data;
				root = root.left;
			}
			return min;
		}

		/**
		 * Traversal in-order (kiri - akar - kanan)
		 *
		 * @return List berisi elemen yang ditelusuri secara in-order
		 */
		public List<Integer> inOrderTraversal() {
			List<Integer> result = new ArrayList<>();
			inOrderRec(root, result);
			return result;
		}

		// Fungsi rekursif untuk in-order traversal
		private void inOrderRec(TreeNode root, List<Integer> result) {
			if (root != null) {
				inOrderRec(root.left, result);
				result.add(root.data);
				inOrderRec(root.right, result);
			}
		}
	}

	/**
	 * Binary Search Tree Implementation (extends BinaryTree) - Specialized binary
	 * tree with ordering property: left child < parent < right child - More
	 * efficient search operations than regular binary tree
	 */
	public static class BinarySearchTree extends BinaryTree {
		// Inherits all methods from BinaryTree
		// BST is a specialized BinaryTree with insertion rules
	}

	/**
	 * Implementasi Min-Heap (menggunakan array) - Tree lengkap di mana parent ≤
	 * anak - Operasi insert dan extractMin efisien
	 */
	public static class MinHeap {
		private int[] heap;
		private int size;
		private int capacity;

		// Inisialisasi kapasitas heap
		public MinHeap(int capacity) {
			this.capacity = capacity;
			this.size = 0;
			heap = new int[capacity];
		}

		// Indeks parent dan anak
		private int parent(int i) {
			return (i - 1) / 2;
		}

		private int leftChild(int i) {
			return 2 * i + 1;
		}

		private int rightChild(int i) {
			return 2 * i + 2;
		}

		/**
		 * Menyisipkan nilai ke dalam heap
		 * 
		 * @throws IllegalStateException jika penuh
		 */
		public void insert(int value) {
			if (size == capacity)
				throw new IllegalStateException("Heap penuh");

			heap[size] = value;
			int current = size;
			size++;

			// Perbaiki ke atas (heapify up)
			while (current != 0 && heap[current] < heap[parent(current)]) {
				swap(current, parent(current));
				current = parent(current);
			}
		}

		/**
		 * Menghapus dan mengembalikan elemen minimum
		 */
		public int extractMin() {
			if (size <= 0)
				throw new NoSuchElementException("Heap kosong");

			if (size == 1)
				return heap[--size];

			int root = heap[0];
			heap[0] = heap[size - 1];
			size--;
			heapify(0); // Perbaiki ke bawah

			return root;
		}

		// Perbaiki ke bawah (heapify down)
		private void heapify(int i) {
			int left = leftChild(i);
			int right = rightChild(i);
			int smallest = i;

			if (left < size && heap[left] < heap[smallest])
				smallest = left;
			if (right < size && heap[right] < heap[smallest])
				smallest = right;

			if (smallest != i) {
				swap(i, smallest);
				heapify(smallest);
			}
		}

		private void swap(int i, int j) {
			int temp = heap[i];
			heap[i] = heap[j];
			heap[j] = temp;
		}

		// Ambil nilai minimum tanpa menghapus
		public int getMin() {
			if (size == 0)
				throw new NoSuchElementException("Heap kosong");
			return heap[0];
		}

		public int size() {
			return size;
		}
	}

	/**
	 * Graph Implementation (Adjacency List) - Vertices stored as array of linked
	 * lists - Supports both BFS and DFS traversals - O(V+E) space complexity
	 */
	public static class Graph {
		private int vertices;
		private LinkedList<Integer>[] adj;

		/**
		 * Initialize graph with specified number of vertices
		 * 
		 * @param vertices Number of vertices in graph
		 */
		@SuppressWarnings("unchecked")
		public Graph(int vertices) {
			this.vertices = vertices;
			adj = new LinkedList[vertices];
			for (int i = 0; i < vertices; i++) {
				adj[i] = new LinkedList<>();
			}
		}

		/**
		 * Add undirected edge between two vertices
		 * 
		 * @param src  Source vertex
		 * @param dest Destination vertex
		 */
		public void addEdge(int src, int dest) {
			adj[src].add(dest);
			adj[dest].add(src); // For undirected graph
		}

		/**
		 * Perform BFS traversal starting from given vertex
		 * 
		 * @param startVertex Vertex to start traversal from
		 */
		public void bfs(int startVertex) {
			boolean[] visited = new boolean[vertices];
			Queue<Integer> queue = new LinkedList<>();

			visited[startVertex] = true;
			queue.add(startVertex);

			while (!queue.isEmpty()) {
				int vertex = queue.poll();
				System.out.print(vertex + " ");

				for (int neighbor : adj[vertex]) {
					if (!visited[neighbor]) {
						visited[neighbor] = true;
						queue.add(neighbor);
					}
				}
			}
		}

		/**
		 * Perform DFS traversal starting from given vertex
		 * 
		 * @param startVertex Vertex to start traversal from
		 */
		public void dfs(int startVertex) {
			boolean[] visited = new boolean[vertices];
			dfsRecursive(startVertex, visited);
		}

		private void dfsRecursive(int vertex, boolean[] visited) {
			visited[vertex] = true;
			System.out.print(vertex + " ");

			for (int neighbor : adj[vertex]) {
				if (!visited[neighbor]) {
					dfsRecursive(neighbor, visited);
				}
			}
		}
	}

	/**
	 * Hash Table Implementation (Separate Chaining) - Array of linked lists to
	 * handle collisions - Basic operations: put, get, remove - O(1) average case,
	 * O(n) worst case for operations
	 */
	public static class HashTable {
		private static class Entry {
			String key;
			int value;

			Entry(String key, int value) {
				this.key = key;
				this.value = value;
			}
		}

		private LinkedList<Entry>[] table;
		private int capacity;
		private int size;

		/**
		 * Initialize hash table with specified capacity
		 * 
		 * @param capacity Initial capacity of hash table
		 */
		@SuppressWarnings("unchecked")
		public HashTable(int capacity) {
			this.capacity = capacity;
			this.size = 0;
			table = new LinkedList[capacity];
			for (int i = 0; i < capacity; i++) {
				table[i] = new LinkedList<>();
			}
		}

		/**
		 * Compute hash value for key
		 * 
		 * @param key Key to hash
		 * @return Hash value (array index)
		 */
		private int hash(String key) {
			return Math.abs(key.hashCode()) % capacity;
		}

		/**
		 * Insert key-value pair into hash table
		 * 
		 * @param key   Key to insert
		 * @param value Value to associate with key
		 */
		public void put(String key, int value) {
			int index = hash(key);
			for (Entry entry : table[index]) {
				if (entry.key.equals(key)) {
					entry.value = value; // Update existing
					return;
				}
			}
			table[index].add(new Entry(key, value));
			size++;
		}

		/**
		 * Get value associated with key
		 * 
		 * @param key Key to search for
		 * @return Associated value or null if not found
		 */
		public Integer get(String key) {
			int index = hash(key);
			for (Entry entry : table[index]) {
				if (entry.key.equals(key)) {
					return entry.value;
				}
			}
			return null;
		}

		/**
		 * Remove key-value pair from hash table
		 * 
		 * @param key Key to remove
		 * @return true if key found and removed, false otherwise
		 */
		public boolean remove(String key) {
			int index = hash(key);
			Iterator<Entry> iterator = table[index].iterator();
			while (iterator.hasNext()) {
				Entry entry = iterator.next();
				if (entry.key.equals(key)) {
					iterator.remove();
					size--;
					return true;
				}
			}
			return false;
		}

		/**
		 * @return Current number of key-value pairs in hash table
		 */
		public int size() {
			return size;
		}
	}

	/**
	 * Bloom Filter Implementation (Probabilistic Data Structure) - Space-efficient
	 * membership test with possible false positives - No false negatives - Cannot
	 * delete items
	 */
	public static class BloomFilter {
		private boolean[] bitArray;
		private int size;
		private int numHashFunctions;

		/**
		 * Initialize bloom filter
		 * 
		 * @param size             Size of bit array
		 * @param numHashFunctions Number of hash functions to use
		 */
		public BloomFilter(int size, int numHashFunctions) {
			this.size = size;
			this.numHashFunctions = numHashFunctions;
			this.bitArray = new boolean[size];
		}

		/**
		 * Add item to bloom filter
		 * 
		 * @param item Item to add
		 */
		public void add(String item) {
			for (int i = 0; i < numHashFunctions; i++) {
				int hash = hash(item, i);
				bitArray[hash % size] = true;
			}
		}

		/**
		 * Check if item might be in bloom filter
		 * 
		 * @param item Item to check
		 * @return true if item might be present (possible false positive), false if
		 *         item definitely not present
		 */
		public boolean mightContain(String item) {
			for (int i = 0; i < numHashFunctions; i++) {
				int hash = hash(item, i);
				if (!bitArray[hash % size]) {
					return false;
				}
			}
			return true;
		}

		/**
		 * Compute hash value for item using specified index
		 * 
		 * @param item  Item to hash
		 * @param index Hash function index
		 * @return Hash value
		 */
		private int hash(String item, int index) {
			return Math.abs((item + index).hashCode());
		}
	}
}