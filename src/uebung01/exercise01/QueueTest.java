package uebung01.exercise01;

import java.util.ArrayDeque;

public class QueueTest {
	public static void main(String[] args) {
		ArrayDeque<String> q = new ArrayDeque<String>();
		q.add("A");
		q.add("B");
		q.add("C");
		for (String string : q) {
			print(string);
		}
		
		print("poll:" + q.poll());
		print("pop:" + q.pop());
	}
	
	static void print(String s){
		System.out.println(s);
	}
}
