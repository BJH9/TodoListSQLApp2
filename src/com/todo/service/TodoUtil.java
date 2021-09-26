package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.io.Writer;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	
	public static void createItem(TodoList list) {
		
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("\n"
				+ "========== (Add)아이템을 선택하세요. \n"
				+ "$추가할 아이템의 이름을 입력하세요.\n");
		
		
		title = sc.nextLine();
		if (list.isDuplicate(title)) {
			System.out.printf("**중복된 이름을 사용할 수 없습니다. ");
			return;
		}
		System.out.println("-아이템의 이름이 등록되었습니다. ");
		System.out.println("");
		
		System.out.println("$카테고리를 입력하세요.");
		category = sc.next();
		System.out.println("-카테고리가 추가되었습니다.");
		
		System.out.println("");
		System.out.println("$discription을 추가하세요.");
		
		System.out.println("");
		sc.nextLine();
		desc = sc.nextLine();
		System.out.println("-아이템의 discription이 등록되었습니다.");
		
		System.out.println("due date를 입력하세요.");
		due_date = sc.nextLine();
		System.out.println("due date가 추가되었습니다");
		
		TodoItem t = new TodoItem(title, desc, category, due_date);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		System.out.println("\n"
				+ "========== (Delete)아이템을 선택하세요. \n");
		
		System.out.println("삭제할 아이템의 번호를 입력하세요. ");
		Scanner sc = new Scanner(System.in);
		int order = sc.nextInt();
		
		for (TodoItem item : l.getList()) {
				if (order == item.getOrder()) {
					l.deleteItem(item);
					System.out.println("-"+ item.getTitle() + " 아이템이 삭제되었습니다.  ");
					break;
				}
			}
		}
	


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "========== (Edit) 아이템을 선택하세요. \n"
				+ "$업데이트할 아이템의 번호를 입력하세요. \n"
				+ "\n");
		
		int order = sc.nextInt();
		
		System.out.println("-번호를 입력받았습니다. ");

		System.out.println("$새로운 아이템의 이름을 입력하세요. ");
		
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("**중복된 이름은 사용할 수 없습니다. ");
			return;
		}
		System.out.println("-새로운 이름이 입력되었습니다. ");
		
		System.out.println("$새로운 카테고리를 입력하세요.");
		String new_category = sc.next().trim();
		
		System.out.println("$새로운 description을 입력하세요. ");
		System.out.println("");
		sc.nextLine();
		String new_description = sc.nextLine().trim();
		
		System.out.println("$새로운 due_date를 입력하세요.");
		String new_due_date = sc.nextLine();
		
		for (TodoItem item : l.getList()) {
			if (item.getOrder() == order) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description, new_category, new_due_date);
				l.addItem(t);
				System.out.println("-아이템이 업데이트 되었습니다.");
			}
		}

	}

	public static void listAll(TodoList l) {
		int count = 0;
		for(int i = 0; i < 100; i++) {
		
		}
		for (TodoItem item : l.getList()) {
			count++;
			item.setOrder(count);
			System.out.println(count + ". [" + item.getCategory() + "]" + "  " + item.getTitle() + " - " + item.getDesc() + " - " + item.getDue_date() + " 까지 ");
		}
		System.out.println("전체목록, 총" + count + "개");
	}
	
	public static void findKeyword(TodoList l) {
		Scanner sc = new Scanner(System.in);
		int count = 0;
		
		System.out.print("찾고자 하는 keyword를 입력하세요: ");
		String keyword = sc.next();
		
		for(TodoItem item : l.getList()) {
			if((item.getTitle().contains(keyword)) || (item.getDesc().contains(keyword))) {
				TodoItem t = new TodoItem(item.getTitle(), item.getDesc(), item.getCategory(), item.getDue_date());
				l.addItem2(t);
				count++;
				item.setOrder(count);
				System.out.println(count + ". [" + item.getCategory() + "]" + "  " + item.getTitle() + " - " + item.getDesc() + " - " + item.getDue_date() + " 까지 ");
			}
		}
		
		
	}
	
	public static void saveList(TodoList i, String filename) {
		System.out.println("정보를 저장합니다.");
		try {
			Writer w = new FileWriter(filename);
			for(TodoItem item : i.getList()) {
			w.write(item.toSaveString());
			}
			w.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
		
		
	
	public static void loadList(TodoList i, String filename) {
		String title;
		String desc;
		String category;
		String due_date;
		String current_date;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			LineNumberReader lr = new LineNumberReader(br);
			String line;
			int count = 0;
			while((line = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "##");
				category = st.nextToken();
				title = st.nextToken();
				desc = st.nextToken();
				due_date = st.nextToken();
				current_date = st.nextToken();
				TodoItem item = new TodoItem(title, desc, category, due_date);
				item.setCurrent_date(current_date);
				item.setOrder(lr.getLineNumber());
				i.addItem(item);
				count++;
				
			}
			System.out.println(count + "개의 항목을 읽었습니다.");
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
