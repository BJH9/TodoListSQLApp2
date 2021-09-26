package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("괄호 안에 있는 명령어를 입력하여 해당 기능을 사용할 수 있습니다.");
        System.out.println("1. Add a new item ( add )");
        System.out.println("2. Delete an existing item ( del )");
        System.out.println("3. Update an item  ( edit )");
        System.out.println("4. List all items ( ls )");
        System.out.println("5. sort the list by name ( ls_name_asc )");
        System.out.println("6. sort the list by name ( ls_name_desc )");
        System.out.println("7. sort the list by date ( ls_date )");
        System.out.println("8. exit (Or press escape key to exit)");
        System.out.println("9. find items with keword that you enter( find )");
        System.out.println("Enter your choice >");
    }
    
    public static void prompt() {
    	System.out.println("\n command >");
    }
}
