package bachelorSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class CourseMgr{
	
	
	// regex ǥ�ؽ� �߰��ϱ�
	public CourseMgr() {
		Professor admin = new Professor();
		admin.setId("admin");
		admin.setPassword("admin");
	}
	
	// ������ ���� ��� ���� ���� �� ��ü �ֱ�
	public void fileCheck() throws Exception{
		File studentFile = new File("student.sav");
		File professorFile = new File("professor.sav");
		File courseFile = new File("course.sav");
		if(!studentFile.isFile()) {
			ObjectOutputStream oisStu= new ObjectOutputStream(new FileOutputStream("student.sav"));
			oisStu.writeObject(studentList);
			oisStu.close();
		}
		if(!professorFile.isFile()) {
			ObjectOutputStream oisPro= new ObjectOutputStream(new FileOutputStream("professor.sav"));
			oisPro.writeObject(professorList);
			oisPro.close();
		}
		if(!courseFile.isFile()) {
			ObjectOutputStream oisCourse= new ObjectOutputStream(new FileOutputStream("course.sav"));
			oisCourse.writeObject(courseList);
			oisCourse.close();
			return;
		}
	}
	
	// ���� �о����
	public void read(){
		try{
			ObjectInputStream oisStu = new ObjectInputStream(new FileInputStream("student.sav"));
			studentList = (ArrayList<Student>) oisStu.readObject();
			oisStu.close();
			
			ObjectInputStream oisPro = new ObjectInputStream(new FileInputStream("professor.sav"));
			professorList = (ArrayList<Professor>) oisPro.readObject();
			oisPro.close();
			
			ObjectInputStream oisCourse = new ObjectInputStream(new FileInputStream("course.sav"));
			courseList = (ArrayList<Course>) oisCourse.readObject();
			oisCourse.close();
			
		} catch(Exception e){
			System.out.println("������ �߻��Ͽ����ϴ�.");
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}
	
	// ���� ���
	public void save() throws Exception{
		ObjectOutputStream oosStu = new ObjectOutputStream(new FileOutputStream("student.sav"));
		oosStu.writeObject(studentList);
		oosStu.flush();
		oosStu.close();
		
		ObjectOutputStream oosPro = new ObjectOutputStream(new FileOutputStream("professor.sav"));
		oosPro.writeObject(professorList);
		oosPro.flush();
		oosPro.close();
		
		ObjectOutputStream oosCourse = new ObjectOutputStream(new FileOutputStream("course.sav"));
		oosCourse.writeObject(courseList);
		oosCourse.flush();
		oosCourse.close();
	}
	
	ArrayList<Student> studentList = new ArrayList<>();
	ArrayList<Professor> professorList = new ArrayList<>();
	ArrayList<Course> courseList = new ArrayList<>();
	
	Professor loginedProfessor = null;
	Student loginedStudent = null;
	
	Scanner sc = new Scanner(System.in);
	
	// �ΰ����
	Calendar now = Calendar.getInstance();
	int year = now.get(Calendar.YEAR);
	String yearNum1 = String.valueOf(year).substring(2, 4);
	int yearNum2 = Integer.parseInt(yearNum1);
	

	
	// �ߺ� ���̵� ��ȸ
	private boolean isExistId(String id) {
		boolean isExist = false;
		for(Professor prof : professorList) {
			if(prof.getId().equals(id)) {
				isExist = true;
				break;
			}
		}
		for(Student stu : studentList) {
			if(stu.getId().equals(id)) {
				isExist = true;
				break;
			}
		}
		return isExist;
	}
	
	
	// �ߺ� ��ȣ ��ȸ
	private boolean isExistNum(String num) {
		boolean isExist = false;
		for(Professor pro : professorList) {
			for(Student stu : studentList) {
				if(num.equals(String.valueOf(pro.getDeptNum()))||(num.equals(String.valueOf(stu.getDeptNum())))) {
					isExist = true;
					break;
				}
			}
		}
		return isExist;
	}
	
	// �����ڵ� �ߺ� ��ȸ
	private boolean isExistCode(String num) {
		boolean isExist = false;
		for(Course course : courseList ) {
			if(num.equals(course.getCode())) {
				isExist = true;
				break;
			}
		}
		return isExist;
	}
	
	//1 �α���
	public void login() {
		if((loginedProfessor!=null)||(loginedStudent!=null)) {
			System.out.println("�α��� �� �Դϴ�.");
			return;
		}
		System.out.print("���̵� �Է��ϼ���>");
		String loginId = sc.nextLine();
		System.out.print("��й�ȣ�� �Է��ϼ���>");
		String loginPass = sc.nextLine();
		
		for(Professor pro : professorList) {
			if((pro.getId().equals(loginId))){
				if(pro.getPassword().equals(loginPass)) {
					loginedProfessor = pro;
					System.out.println(pro.getName()+" ������ �α��� �Ǿ����ϴ�.");
					return;
				}
			}
		}
		for(Student stu : studentList) {				
			if(stu.getId().equals(loginId)) {
				if(stu.getPassword().equals(loginPass)) {
					loginedStudent = stu;
					System.out.println("�α��� �Ǿ����ϴ�.");
					return;
				}
			}
		}		
		if((loginedProfessor == null) && (loginedStudent == null)) {
			System.out.println("���̵� Ȥ�� ��й�ȣ�� �ùٸ��� �ʽ��ϴ�.");
			return;
		}
	}
	
	//2 ȸ������
	public void register(){
		if((loginedProfessor!=null)||(loginedStudent!=null)) {
			System.out.println("�α��� �� �Դϴ�.");
			return;
		}
		System.out.print("���̵� �Է��ϼ���>");
		String id = sc.nextLine();
		while(isExistId(id)) {
			System.out.println("�̹� ������� ���̵� �Դϴ�.");
			if(!isExistId(id)) {
				break;
			}
			return;
		}
		System.out.print("����� �л� �Դϱ�?(Y/N)>");
		String choice = sc.nextLine();
		choice = choice.toLowerCase();
		if(choice.equals("n")){
			Professor professor = new Professor();
			professor.setId(id);
			System.out.print("��й�ȣ�� �Է��ϼ���>");
			professor.setPassword(sc.nextLine());
			System.out.print("�̸��� �Է��ϼ���>");
			professor.setName(sc.nextLine());
			System.out.print("�а��� �Է��ϼ���>");
			professor.setDept(sc.nextLine());
			System.out.print("��å�� �Է��ϼ���>");
			professor.setLevel(sc.nextLine());
			System.out.print("��ȭ��ȣ�� �Է��ϼ���>");
			professor.setTelNum(sc.nextLine());
			System.out.print("�̸����� �Է��ϼ���>");
			professor.setEmail(sc.nextLine());
			int num = (int) (Math.random()*999)+1;
			while(!isExistNum(String.valueOf(num))) {
				if(isExistNum(String.valueOf(num)) == false) {
					String num2 = String.valueOf(num);
					String proNum = yearNum2 + num2;
					professor.setDeptNum(Integer.parseInt(proNum));
					break;
				}
			}
			System.out.println("������ȣ�� " + professor.getDeptNum() + "�Դϴ�.");
			professorList.add(professor);
			return;
		}
		else if (choice.equals("y")){
			Student student = new Student();
			student.setId(id);
			System.out.print("��й�ȣ�� �Է��ϼ���>");
			student.setPassword(sc.nextLine());
			System.out.print("�̸��� �Է��ϼ���>");
			student.setName(sc.nextLine());
			System.out.print("�а��� �Է��ϼ���>");
			student.setDept(sc.nextLine());
			System.out.print("��ȭ��ȣ�� �Է��ϼ���>");
			student.setTelNum(sc.nextLine());
			System.out.print("�̸����� �Է��ϼ���>");
			student.setEmail(sc.nextLine());
			int num = (int) (Math.random()*99999)+1;
			while(!isExistNum(String.valueOf(num))) {
				if(isExistNum(String.valueOf(num)) == false) {
					String num2 = String.valueOf(num);
					String stuNum = yearNum2 + num2;
					student.setDeptNum(Integer.parseInt(stuNum));
					break;
				}
			}
			System.out.println("����� �й��� " + student.getDeptNum() + "�Դϴ�.");
			studentList.add(student);
			return;
		}
		
	}
	
	
	
	
	//3 ���� ��ȸ
	public void enrollCheck() {
		if((loginedProfessor == null) && (loginedStudent==null)) {
			System.out.println("�α����� �ʿ��մϴ�.");
			return;
		}
		if(loginedProfessor != null || loginedStudent != null) {
			System.out.println("�а�\t���� �ڵ�\t�����\t\t����\t(��û�ο�\\�����ο�)");
			System.out.println("------------------------------------------------");
			if(courseList.isEmpty()){
				System.out.println("���� ��ϵ� ������ �����ϴ�.");
				return;
			}
			for(Course courses : courseList) { 
				System.out.println(courses.getProfessors().getDept() + "\t" +courses.getCode() +"\t" + courses.getName() 
				+ "\t\t" + courses.getProf() +	"\t    (" +courses.getStudentsList().size() + "\\" + courses.getLimit()+")");
			}
			System.out.println("------------------------------------------------");
		}		
	}
	
	
	//4 ���� ���
	// �ѹ� �Է��� �ߺ� �Է� ����
	public void enroll(){
		if((loginedProfessor == null) && (loginedStudent==null)) {
			System.out.println("�α����� �ʿ��մϴ�.");
			return;
		}
		// ������ ���� ���
		if(loginedProfessor!=null) {
			Course course = new Course();
			System.out.print("���� �̸��� �Է��ϼ���>");
			String courseName = sc.nextLine();
			course.setName(courseName);
			
			//�����ο� �߸� �Է��Ͽ��� ��� �߰�
			System.out.print("���� �ο��� �Է��ϼ���>");
			String courseNumStr = sc.nextLine();
			int courseNumInt = Integer.parseInt(courseNumStr);
			if(courseNumInt>=100) {
				System.out.println("�ִ��ο��� 100���Դϴ�.");
				return;
			}
			course.setLimit(courseNumInt);
			
			// �ΰ���� ���� �ڵ� ���� �߰�
			int secondNum = (int)(Math.random()*99)+1;
			int firstCode1 = (int)(Math.random()*26)+65;
			char firstCode2 = (char)firstCode1;
			String characterToString = Character.toString(firstCode2);
			String nameCode = (characterToString + characterToString) + yearNum1 + secondNum ;
			while(!isExistCode(nameCode)) {
				if(isExistCode(nameCode) == false) {					
					course.setCode(nameCode);
					break;
				}
			}
			System.out.println("���� �ڵ�� " + course.getCode() +"�Դϴ�.");
			course.setProfessors(loginedProfessor);
			course.setProf(loginedProfessor.getName());
			courseList.add(course);
			return;
		}
		
		
		// �л� ���� ��� ��û
		if(loginedStudent!=null) {
			enrollCheck();
			if(courseList.isEmpty()) {
				return;
			}
			System.out.print("����� ������ ���� �ڵ带 �Է��ϼ���>");
			String inputCourse = sc.nextLine();
			boolean isExist = false;
			// �ߺ� ��û ��ȸ�� ���� �ο�Ȯ��
			for(Course course : courseList) {
				if(course.getCode().equals(inputCourse)) {
					if(course.getLimit()<=course.getStudentsList().size()) {
						System.out.println("�����ο��� �ʰ��Ͽ� ��û�� �� �����ϴ�.");
						return;
					}
					if(course.getStudentsList().contains(loginedStudent)) {
						System.out.println("�̹� ��û�� �����Դϴ�.");
						return;
					}
					isExist = true;
					course.getStudentsList().add(loginedStudent);
					System.out.println(course.getName() + "���� ������ �Ϸ� �Ǿ����ϴ�.");
					return;
				}
			}
			if(!isExist) {
				System.out.println("�ùٸ� �����ڵ带 �Է��ϼ���.");
			}
		}
	}
	
	//5 ����� ���� ��ȸ
	public void enrollInfo() {
		if((loginedProfessor == null) && (loginedStudent==null)) {
			System.out.println("�α����� �ʿ��մϴ�.");
			return;
		}
		// ������ ����� ���� ��ȸ
		if(loginedProfessor!=null) {
			boolean isExist = false;
			System.out.println("�����ڵ�\t�����\t\t(��û�ο�\\�����ο�)");
			System.out.println("------------------------------------------------");
			for(Course course : courseList) {
				if(loginedProfessor.getId() == course.getProfessors().getId()) {
					isExist = true;
					System.out.println(course.getCode()+"\t"+course.getName() + "\t\t(" + 
							course.getStudentsList().size()+course.getLimit() +")");
				}
			}if(!isExist) {
				System.out.println("��û�� ������ �����ϴ�.");
				return;
			}
		}
		
		// �л��� ����� ���� ��ȸ
		if(loginedStudent!=null) {
			boolean isExist = false;
			System.out.println("�а�\t�����ڵ�\t�����\t\t�����̸�\t(��û�ο�\\�����ο�)");
			System.out.println("------------------------------------------------");
			for(Course course : courseList) {
				for(Student stu : course.getStudentsList()) {
					if(loginedStudent.getId() == stu.getId()) {
						isExist = true;
						System.out.println(course.getProfessors().getDept() + "\t" + course.getCode() + "\t" + course.getName() + "\t\t"
								+ course.getProf() + "\t(" + course.getStudentsList().size() 
								+ "\\" + course.getLimit()+")");
					}
				}
			}
			if(!isExist) {
				System.out.println("��û�� ������ �����ϴ�.");
				return;
			}
		}
	}
	//6.���� Ȯ��
		public void info() {
			if((loginedProfessor == null) && (loginedStudent==null)) {
				System.out.println("�α����� �ʿ��մϴ�.");
				return;
			}
			System.out.print("��й�ȣ�� �Է��ϼ���>");
			String psCheck = sc.nextLine();
			if(loginedStudent != null) {
				if(loginedStudent.getPassword().equals(psCheck)) {
					System.out.println("----------------------------");
					System.out.println("�й�\t�а�\t�̸�\t\t��ȭ��ȣ\tEmail");
					System.out.println(loginedStudent.getDeptNum() + "\t" 
							+ loginedStudent.getDept() + "\t" + loginedStudent.getName()
							+ "\t" + loginedStudent.getTelNum() + "\t" + loginedStudent.getEmail());
					return;
				} else {
					System.out.println("��й�ȣ�� �߸� �Ǿ����ϴ�.");
					return;
				}
			}
			if(loginedProfessor != null){
				if(loginedProfessor.getPassword().equals(psCheck)) {
					System.out.println("----------------------------");
					System.out.println("���� ��ȣ\t�а�\t�̸�\t��å\t��ȭ��ȣ\t\tEmail");
					System.out.println(loginedProfessor.getDeptNum() + "\t" 
							+ loginedProfessor.getDept() + "\t" +
							loginedProfessor.getName() + "\t" +
							loginedProfessor.getLevel() + "\t" +
							loginedProfessor.getTelNum() + "\t" +
							loginedProfessor.getEmail());
					return;
				}				
			} else {
				System.out.println("��й�ȣ�� �߸� �Ǿ����ϴ�.");
				return;
			}
		}

	
	//7.�α׾ƿ�
	public void logout(){
		if((loginedProfessor == null) && (loginedStudent==null)) {
			System.out.println("�α����� �ʿ��մϴ�.");
			return;
		}
		loginedProfessor = null;
		loginedStudent = null;
		System.out.println("�α׾ƿ� �Ǿ����ϴ�.");
		return;
	}
	
	// ��� �������� ���� ��ȸ / ���� �ʱ�ȭ / ���� ����
	public void admin() throws Exception{
		System.out.print("��� ID>");
		String adminId = sc.nextLine();
		System.out.print("��� Password>");
		String adminPs = sc.nextLine();
		if(adminId.equals("admin")) {
			if(adminPs.equals("admin")) {
				System.out.println("-------------------------------");
				System.out.println("1.���� ��ȸ       2.���� �ʱ�ȭ       3.���� ����");
				System.out.println("-------------------------------");
				System.out.print(">");
				String num = sc.nextLine();
				if(num.equals("1")) {
					if(studentList.size()==0) {
						System.out.println("------------------");
						System.out.println("��ϵ� �л� ������ �����ϴ�.");
					}
					if(professorList.size()==0) {
						System.out.println("��ϵ� ���� ������ �����ϴ�.");
					}
					if(courseList.size()==0) {
						System.out.println("��ϵ� ���� ������ �����ϴ�.");
						System.out.println("-------------------");
					}
					for(Student stuinfo : studentList) {
						System.out.println(stuinfo);
					}
					for(Professor proInfo : professorList) {
						System.out.println(proInfo);
					}
					for(Course courInfo : courseList) {
						System.out.println(courInfo);
					}
					return;					
				} else if(num.equals("2")) {
					System.out.print("������ �ʱ�ȭ �Ͻðڽ��ϱ�?(Y/N)>");
					String deleteInfo = sc.nextLine();
					deleteInfo = deleteInfo.toLowerCase();
					if(deleteInfo.equals("y")){
						studentList.clear();
						professorList.clear();
						courseList.clear();
						System.out.println("�ʱ�ȭ�� �Ϸ�Ǿ����ϴ�.");
						return;
					}
					
				} else if(num.equals("3")) {
						System.out.println("��ȣ     �а�\t���� �ڵ�\t�����\t\t����\t(��û�ο�\\�����ο�)");
						System.out.println("------------------------------------------------");
						if(courseList.isEmpty()){
							System.out.println("���� ��ϵ� ������ �����ϴ�.");
							return;
						}
						
						int count = 0;
						for(Course course : courseList) {
							System.out.println(count+"  "+course.getProfessors().getDept() + "\t" +course.getCode() 
							+ "\t" + course.getName() + "\t\t" + course.getProf() + "\t" + course.getStudentsList().size() + 
							"\\"+ course.getLimit() + ")");
							count++;
						}
						System.out.println("------------------------------------------------");
						System.out.print("������ ������ ���� ��ȣ�� �Է��ϼ���(������� n�Է�)>");
						String deleteCourse = sc.nextLine();
						deleteCourse = deleteCourse.toLowerCase();
						if(deleteCourse.equals("n")) {
							return;
						}
						courseList.remove(Integer.parseInt(deleteCourse));
						System.out.println("������ �Ϸ�Ǿ����ϴ�.");							
						return;
				}
			} else {
				System.out.println("��ڸ� ������ �� �ֽ��ϴ�.");
			}
		}
	}
}