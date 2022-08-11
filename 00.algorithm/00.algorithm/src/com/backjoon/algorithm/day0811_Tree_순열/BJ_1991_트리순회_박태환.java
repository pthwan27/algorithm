package com.backjoon.algorithm.day0811_Tree_순열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 트리의 노드 정보를 저장할 클래스
class Node {
	char data; // 노드 값
	Node left; // 왼쪽 자식 노드 참조 값
	Node right;// 오른쪽 자식 노드 참조 값

	// 추가되는 데이터 값을 기반으로 Node객체 생성
	Node(char data) {
		this.data = data;
	}
}

class TreeClass {
	public Node root;// 초기 root는 null

	// 값을 추가하기 위한 메서드
	public void createNode(char data, char leftData, char rightData) {
		if (root == null) {
			root = new Node(data);

			// 좌우 값이 있는지 없는지 확인
			if (leftData != '.') {
				root.left = new Node(leftData);
			}

			if (rightData != '.') {
				root.right = new Node(rightData);
			}
		} else { // 초기생성이 아니면 createNode를 할 필요없음.
			addNode(root, data, leftData, rightData);
		}
	}

	// 값을 어디에 추가할지 찾는 메서드
	public void addNode(Node node, char data, char leftData, char rightData) {
		if (node == null) { // 노드가 null이면 종료
			return;
		}
		// 들어갈 위치가 있는 경우
		else if (node.data == data) {
			if (leftData != '.') {
				node.left = new Node(leftData);
			}

			if (rightData != '.') {
				node.right = new Node(rightData);
			}

		}
		// 노드가 null도 아니고, 좌우에 추가할 곳도 찾지 못한다면 (아직 찾지 못했을 때)
		else {
			addNode(node.left, data, leftData, rightData);
			addNode(node.right, data, leftData, rightData);
		}
	}

	// 전위 순회 root -> left -> right
	public void preOrder(Node node) {
		if (node != null) {
			System.out.print(node.data);
			if (node.left != null) {
				preOrder(node.left);
			}
			if (node.right != null) {
				preOrder(node.right);
			}
		}
	}
	// 중위 순회 left -> root -> right
	public void inOrder(Node node) {
		if (node != null) {
			if (node.left != null) {
				inOrder(node.left);
			}
			System.out.print(node.data);
			
			if (node.right != null) {
				inOrder(node.right);
			}
		}
	}
	// 후위 순회 left -> right -> root
	public void postOrder(Node node) {
		if (node != null) {
			if (node.left != null) {
				postOrder(node.left);
			}

			if (node.right != null) {
				postOrder(node.right);
			}	
			System.out.print(node.data);
		}
	}
}

public class BJ_1991_트리순회_박태환 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		TreeClass Tree = new TreeClass();
		
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			char inputNode = st.nextToken().charAt(0);
			char inputleft = st.nextToken().charAt(0);
			char inputright= st.nextToken().charAt(0);
			
			Tree.createNode(inputNode, inputleft, inputright);
		}
	
		Tree.preOrder(Tree.root);
		System.out.println();
		Tree.inOrder(Tree.root);
		System.out.println();
		Tree.postOrder(Tree.root);
	}

}
