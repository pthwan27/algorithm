package com.ssafy.algorithm.BFSDFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class CompleteBinaryTree {
	private char[] nodes;
	private int lastIndex; // 마지막 노드 인덱스
	private final int SIZE;

	public CompleteBinaryTree(int size) {
		SIZE = size;
		nodes = new char[size + 1];
	}

	// 완전이진트리에 맞게 추가
	public boolean add(char e) {
		if (lastIndex == SIZE) {
			return false;
		}
		nodes[++lastIndex] = e;
		return true;
	}

	public void bfs() {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(1); // 루트도느 인덱스부터

		while (!queue.isEmpty()) { // 방문대상이 있을 떄까지 반복
			int current = queue.poll(); // 방문차례인 대상 정보 꺼내기
			System.out.print(nodes[current] + " "); // 방문해서 해야할 일 처리

			// 현재 방문노드의 자식노드들을 대기열에 넣기
			if (current * 2 <= lastIndex) {
				queue.offer(current * 2);
			}
			if (current * 2 + 1 <= lastIndex) {
				queue.offer(current * 2 + 1);
			}
		}
		System.out.println();
	}

	public void bfs2() {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(1); // 루트도느 인덱스부터

		while (!queue.isEmpty()) { // 방문대상이 있을 떄까지 반복
			int size = queue.size();

			while (--size >= 0) { // 반복 진입 전 구한 큐 크기 만큼만 반복
				int current = queue.poll(); // 방문차례인 대상 정보 꺼내기
				System.out.println(nodes[current] + " "); // 방문해서 해야할 일 처리

				// 현재 방문노드의 자식노드들을 대기열에 넣기
				if (current * 2 <= lastIndex) {
					queue.offer(current * 2);
				}
				if (current * 2 + 1 <= lastIndex) {
					queue.offer(current * 2 + 1);
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	public void dfs() {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(1); // 루트도느 인덱스부터

		while (!stack.isEmpty()) { // 방문대상이 있을 떄까지 반복
			int current = stack.pop(); // 방문차례인 대상 정보 꺼내기
			System.out.print(nodes[current] + " "); // 방문해서 해야할 일 처리

			// 현재 방문노드의 자식노드들을 대기열에 넣기
			if (current * 2 <= lastIndex) { // 왼쪽자식
				stack.push(current * 2);
			}
			if (current * 2 + 1 <= lastIndex) { // 오른쪽자식
				stack.push(current * 2 + 1);
			}
		}
		System.out.println();
	}

	public void dfsByPreOrder(int current) {
		System.out.print(nodes[current] + " "); // 방문해서 해야할 일 처리
		if (current * 2 <= lastIndex) {
			dfsByPreOrder(current * 2);
		}
		if (current * 2 + 1 <= lastIndex) {
			dfsByPreOrder(current * 2 + 1); // 오른쪽자식
		}

	}

	public void dfsByInOrder(int current) {
		if (current > lastIndex)
			return;

		dfsByInOrder(current * 2);

		System.out.print(nodes[current] + " "); // 방문해서 해야할 일 처리
		dfsByInOrder(current * 2 + 1); // 오른쪽자식
	}
	
	public void dfsByPostOrder(int current) {
		if (current > lastIndex)
			return;

		dfsByPostOrder(current * 2);
		dfsByPostOrder(current * 2 + 1);

		System.out.print(nodes[current] + " "); // 방문해서 해야할 일 처리
	}
}
