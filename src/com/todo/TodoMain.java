package com.todo;

import java.util.Scanner;
import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean isList = false;
		boolean quit = false;
		TodoUtil.loadList(l, "//Users//a21700328//TodoList.txt");
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

			case "ls_name_asc":
				System.out.println("-ls_name_asc를 입력했습니다. ");
				l.sortByName();
				isList = true;
				break;

			case "ls_name_desc":
				System.out.println("-ls_name_desc를 입력했습니다. ");
				l.sortByName();
				l.reverseList();
				isList = true;
				break;
				
			case "ls_date":
				System.out.println("-ls_date를 입력했습니다. ");
				l.sortByDate();
				isList = true;
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
			
			if(isList) l.listAll();
		} while (!quit);
	}
}
