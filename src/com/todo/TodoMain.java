package com.todo;

import java.util.Scanner;
import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		l.importData("TodoList.txt");
		boolean isList = false;
		boolean quit = false;
		TodoUtil.loadList(l, "TodoList.txt");
		Menu.displaymenu();
		do {
			Menu.prompt();
			isList = false;
			String choice = sc.next();
			switch (choice) {

			case "add":
				System.out.println("-add를 입력했습니다. ");
				TodoUtil.createItem(l);
				break;
			
			case "del":
				System.out.println("-del 입력했습니다. ");
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				System.out.println("-edit을 입력했습니다. ");
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				System.out.println("-ls를 입력했습니다. ");
				TodoUtil.listAll(l);
				break;
				
			case "find":
				
				sc.nextLine();
				String keyword = sc.nextLine().trim();
				TodoUtil.findList(l, keyword);
				break;

			case "ls_name_asc":
				System.out.println("-ls_name_asc를 입력했습니다. ");
				l.sortByName();
				isList = true;
				break;

			case "ls_name_desc":
				System.out.println("제목 역순으로 정렬하였습니다. ");
				TodoUtil.listAll(l, "title", 0);
				break;
				
			case "ls_date":
				System.out.println("날짜순으로 정렬하였습니다. ");
				TodoUtil.listAll(l, "due_date", 1);
				break;
				
			case "ls_date_desc":
				System.out.println("날짜 역순으로 정렬하였습니다.");
				TodoUtil.listAll(l, "due_date", 0);
				break;
				
			case "ls_cate":
				TodoUtil.listCateAll(l);
				break;
				
			case "find_cate":
				sc.nextLine();
				String cate = sc.nextLine().trim();
				TodoUtil.findCateList(l, cate);
				break;
				
			case "ls_name":
				System.out.println("제목순으로 정렬했습니다.");
				TodoUtil.listAll(l, "title", 1);
				break;

			case "exit":
				System.out.println("-exit를 입력했습니다. ");
				TodoUtil.saveList(l, "//Users//a21700328//TodoList.txt");
				quit = true;
				break;
			
			case "help":
				Menu.displaymenu();

			default:
				System.out.println("-please enter one of the above mentioned command");
				break;
			}
			
			if(isList) TodoUtil.listAll(l);
		} while (!quit);
	}
}
