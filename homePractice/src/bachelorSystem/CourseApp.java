package bachelorSystem;

import java.util.*;

public class CourseApp {
	public static void main(String[] args) throws Exception{
		CourseMgr util = new CourseMgr();
		util.fileCheck();
		util.read();
		
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("---------------------------------");
			System.out.println("1.�α���   2.ȸ�� ����   3.������ȸ   4.������� ");
			System.out.println("5.��� ������ȸ   6.������ȸ  7.�α׾ƿ�  0.����");
			System.out.println("---------------------------------");
			System.out.print(">");
			
			String num = sc.nextLine();
			if("1".equals(num)) {
				util.login();
			} else if("2".equals(num)){
				util.register();
			} else if("3".equals(num)) {
				util.enrollCheck();
			} else if("4".equals(num)) {
				util.enroll();
			} else if("5".equals(num)) {
				util.enrollInfo();
			} else if("6".equals(num)) {
				util.info();
			} else if("7".equals(num)) {
				util.logout();
			} else if("0".equals(num)) {
				util.save();
				System.out.println("���α׷��� �����մϴ�.");
				break;
			} else if("admin".equals(num)){
				util.admin();
			} else {
				System.out.println("�ùٸ� ��ȣ�� �Է��ϼ���.");
			}
			// ���� ���� ��� ��ȸ (ID:admin/Password:admin)
		} sc.close();
	}
}