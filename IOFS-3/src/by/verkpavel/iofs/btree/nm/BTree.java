package by.verkpavel.iofs.btree.nm;

public class BTree {

	public int count;
	Node root;
	final int order;

	public BTree(int order) {
		this.order = order;
		root = new Node();
		count = 0;
	}

	class Node {
		String[] values;
		Node[]links;

		public Node() {
			values = new String[order-1];
			links = new Node[order];
		}


		boolean isFull() {
			return values[order-2] != null;
		}


		boolean contains(String s) {
			for (int i = 0; i < order-1; i++)
				if (values[i] != null && values[i].compareTo(s) == 0) return true;
			return false;
		}


		Node lvalues() {
			Node lvalues = new Node();
			for (int i = 0; i < order/2-1; i++) {
				lvalues.values[i] = values[i];
				lvalues.links[i] = links[i];
			}
			lvalues.links[order/2-1] = links[order/2-1];
			return lvalues;
		}


		Node rvalues() {
			Node rvalues = new Node();
			for (int i = 0; i < order/2-1; i++) {
				rvalues.values[i] = values[i+order/2];
				rvalues.links[i] = links[i+order/2];
			}
			rvalues.links[order/2-1] = links[order-1];
			return rvalues;
		}


		boolean insert(String s) {
			if (contains(s)) return false;
			for (int i = 0; i < order-1; i++) {
				if (values[i] == null || s.compareTo(values[i]) < 0) {
					System.arraycopy(values, i, values, i + 1, order - 2 - i);
					values[i] = s;
					count++;
					return true;
				}
			}
			return false;
		}

		boolean rid(String s) {
			if (!contains(s)) return false;
			for (int i = 0; i < order-1; i++)
				if (s.equals(values[i])) {
					System.arraycopy(values, i + 1, values, i, order - 2 - i);
					values[order-2] = null;
					break;
				}
			count--;
			return true;
		}

		void merge(Node n, Node parent) {
			for (int i = 0; i < order; i++)
				if (parent.links[i] == this) {
					send(parent.values[i]);
					for (int j = i; j < order-1; j++) {
						if (j > i) parent.links[j] = parent.links[j+1];
						if (j < order-2) parent.values[j] = parent.values[j+1];
					}
					break;
				}
			parent.values[order-2] = null;
			parent.links[order-1] = null;
			send(n.values);
			link(n.links);
		}

		void send(String s) {
			if (contains(s)) return;
			for (int i = 0; i < order-1; i++) {
				if (values[i] == null || s.compareTo(values[i]) < 0) {
					System.arraycopy(values, i, values, i + 1, order - 2 - i);
					values[i] = s;
					return;
				}
			}
		}

		void send(String[] s) {
			for (int i = 0; i < order - 1; i++)
				if (s[i] != null) send(s[i]);
		}


		void link(Node n) {
			for (int i = 0; i < order-1; i++)
				if (values[i] == null || n.values[0].compareTo(values[i]) < 0) {
					if (links[i] == null || links[i].contains(n.values[0])) {
						links[i] = n;
						return;
					}
					System.arraycopy(links, i, links, i + 1, order - 1 - i);
					links[i] = n;
					return;
				} else if (values[order-2] != null && n.values[0].compareTo(values[order-2]) > 0) {
					links[order-1] = n;
					return;
				}
		}

		void link (Node[] n) {
			for (int i = 0; i < order; i++)
				if (n[i] != null) link(n[i]);
		}

		void splitRoot() {
			Node temp = new Node();
			temp.values[0] = root.values[order/2-1];
			temp.links[0] = root.lvalues();
			temp.links[1] = root.rvalues();
			root = temp;
		}


		void split(Node parent) {
			if (parent.insert(values[order/2-1])) count--;
			parent.link(lvalues());
			parent.link(rvalues());
		}
	}

	Node parent(Node h, String s, Node parent) {
		if (h.contains(s)) return parent;
		for (int i = 0; i < order-1; i++) {
			if (h.values[i] == null || s.compareTo(h.values[i]) < 0) {
				if (h.links[i] != null) return parent(h.links[i],s,h);
				else return null;
			} else if (h.values[order-2] != null && s.compareTo(h.values[order-2]) > 0)
				return parent(h.links[order-1],s,h);
		}
		return null;
	}

	Node parent(Node h) {
		if (h == root) return null;
		else return parent(root,h.values[0],null);
	}

	boolean add(Node h, String s, Node parent) {
		if (h.isFull())
			if (h == root) {
				h.splitRoot();
				return add(s);
			} else {
				h.split(parent);
				return add(s);
			}
		if (h.contains(s)) return false;
		for (int i = 0; i  <  order-1; i++)
			if (h.values[i] == null || s.compareTo(h.values[i]) < 0)
				if (h.links[0] != null) return add(h.links[i],s,h);
				else return h.insert(s);
		return false;
	}

	public boolean add(String s) {
		if (root.values[0] == null) return root.insert(s);
		else return add(root,s, null);
	}


	String successor(Node h) {
		if (h.links[0] != null) return successor(h.links[0]);
		else return h.values[0];
	}

	String predecessor(Node h) {
		if (h.links[0] == null) {
			for (int i = order-2; i >= 0; i--)
				if (h.values[i] != null) return h.values[i];
		} else
			for (int i = order-1; i >= 0; i--)
				if (h.links[i] != null) return predecessor(h.links[i]);
		return null;
	}


	boolean remove(Node h, String s, Node parent) {
		if (!h.contains(s)) {
			for (int i = 0; i < order-1; i++)
				if (h.values[i] == null || s.compareTo(h.values[i]) < 0)
					if (h.links[i] != null) return remove(h.links[i],s,h);
					else return false;
				else if (h.values[order-2] != null && s.compareTo(h.values[order-2]) > 0)
					if (h.links[order-1] != null) return remove(h.links[order-1],s,h);
					else return false;
			return false;
		} else if (h.links[0] != null) {
			for (int i = 0; i < order-1; i++)
				if (s.equals(h.values[i]))
					if (h.links[i] != null && h.links[i].values[order/2-1] == null &&
							h.links[i+1] != null && h.links[i+1].values[order/2-1] == null) {
						h.links[i].merge(h.links[i+1],h);
						if (h != root && h.values[order/2-2] == null) rebalanceAfterDelete(h,parent);
						return remove(s);
					} else {
						h.values[i] = predecessor(h.links[i]);
						return remove(h.links[i],h.values[i],h);
					}
		} else {
			h.rid(s);
			if (h.values[order/2 - 2] != null || h == root) return true;
			else
				for (int i = 0; i < order; i++)
					if (parent.links[i] == h) {
						if (i > 0 && parent.links[i-1].values[order/2-1] != null) {
							h.send(parent.values[i-1]);
							parent.values[i-1] = predecessor(parent.links[i-1]);
							count++;
							return remove(parent.links[i-1],parent.values[i-1],parent);
						} else if (i < order-1 && parent.links[i+1] != null && parent.links[i+1].values[order/2-1] != null) {
							h.send(parent.values[i]);
							parent.values[i] = successor(parent.links[i+1]);
							count++;
							return remove(parent.links[i+1],parent.values[i],parent);
						} else if (i > 0) {
							parent.links[i-1].merge(h,parent);
							break;
						} else if (i < order-1 && parent.links[i+1] != null) {
							h.merge(parent.links[i+1],parent);
							break;
						}
					}
			if (parent.values[order/2-2] == null)
				return rebalanceAfterDelete(parent,parent(parent));
			else return true;
		}
		return false;
	}

	public boolean remove(String s) {
		if (root.links[0] != null) return remove(root,s,null);
		else return (root.rid(s));
	}


	boolean rebalanceAfterDelete(Node h, Node parent) {
		if (h == root) {
			if (h.values[0] == null)
				root = h.links[0];
			else if (h.links[1] == null) {
				add(h.links[0],h.values[0],null);
				count--;
				root = h.links[0];
			}
			return true;
		} else
			for (int i = 0; i < order; i++)
				if (parent.links[i] == h)
					if (i > 0 && parent.links[i-1] != null && parent.links[i-1].values[order/2-1] != null && parent.links[i-1].links[0] != null) {
						h.send(parent.values[i-1]);
						for (int j = order-2; j >= 0; j--)
							if (parent.links[i-1].values[j] != null) {
								parent.values[i-1] = parent.links[i-1].values[j];
								parent.links[i-1].values[j] = null;
								h.link(parent.links[i-1].links[j+1]);
								parent.links[i-1].links[j+1] = null;
								break;
							}
						return true;
					} else if (i < order-1 && parent.links[i+1] != null && parent.links[i+1].values[order/2-1] != null && parent.links[i+1].links[0] != null) {
						h.send(parent.values[i]);
						parent.values[i] = parent.links[i+1].values[0];
						h.link(parent.links[i+1].links[0]);
						for (int j = 0; j < order-1; j++) {
							parent.links[i+1].links[j] = parent.links[i+1].links[j+1];
							if (j < order-2) parent.links[i+1].values[j] = parent.links[i+1].values[j+1];
						}
						parent.links[i+1].links[order-1] = null;
						parent.links[i+1].values[order-2] = null;
						return true;
					} else if (i > 0) {
						parent.links[i-1].merge(h,parent);
						break;
					} else if (i < order-1) {
						h.merge(parent.links[i+1],parent);
						break;
					}
		if (parent.values[order/2-2] == null) return rebalanceAfterDelete(parent,parent(parent));
		else return true;
	}

	boolean search(Node h, String s, Node parent) {
		if (h== null) return false;
		if (h.isFull())
			if (h == root) {
				h.splitRoot();
				search(root,s,null);
			} else {
				h.split(parent);
				search(s);
			}
		if (h.contains(s)) return true;
		for (int i = 0; i < h.values.length; i++)
			if (h.values[i] == null || s.compareTo(h.values[i]) < 0) {
				if (h.links[i] != null) return search(h.links[i],s,h);
			} else if (i == order-2 && s.compareTo(h.values[order-2]) > 0)
				if (h.links[order-1] != null) return search(h.links[order-1],s,h);
		return false;
	}

	public boolean search(String s) {
		return search(root,s,null);
	}
}