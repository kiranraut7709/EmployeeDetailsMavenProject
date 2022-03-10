package com.kr.empdtl;

import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {
	public static void main(String args[]) {
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		SessionFactory factory = configuration.buildSessionFactory();
		
		
		int ch, t, t1;
		Scanner scanner = new Scanner(System.in);
		
		do {
			System.out.println("****** Employee Details ******\n1. Add\n2. Update\n3. Delete\n4. Display\n5. Sort data by ID\n6. Get the data with range\n7. Exit\nEnter Your Choice: ");
			ch = scanner.nextInt();
			
			switch(ch) {
				case 1:
					Session session = factory.openSession();
					Transaction transaction = session.beginTransaction();
					EmployeeDetails emp = new EmployeeDetails();
					System.out.println("Enter Employee ID: ");
					emp.setId(scanner.nextInt());
					System.out.println("Enter Employee Name: ");
					emp.setName(scanner.next());
					System.out.println("Enter Employee Age: ");
					emp.setAge(scanner.nextInt());
					
					session.save(emp);
					transaction.commit();
					break;
				case 2:
					Session session1 = factory.openSession();
					Transaction transaction1 = session1.beginTransaction();
					System.out.println("Enter Employee ID to update record: ");
					t = scanner.nextInt();
					EmployeeDetails emp1 = session1.load(EmployeeDetails.class, t);
					System.out.println("Enter Employee Name: ");
					emp1.setName(scanner.next());
					System.out.println("Enter Employee Age: ");
					emp1.setAge(scanner.nextInt());
					
					session1.save(emp1);
					transaction1.commit();
					break;
				case 3:
					Session session2 = factory.openSession();
					Transaction transaction2 = session2.beginTransaction();
					System.out.println("Enter Employee ID to delete record: ");
					t = scanner.nextInt();
					EmployeeDetails emp2 = session2.load(EmployeeDetails.class, t);
					session2.delete(emp2);
					transaction2.commit();
					break;
				case 4:
					Session session3 = factory.openSession();
					
					List<EmployeeDetails> data = session3.createQuery("SELECT a FROM EmployeeDetails a", EmployeeDetails.class).getResultList();
					for(int i = 0; i < data.size(); i++) {
						System.out.println("ID: " + data.get(i).getId());
						System.out.println("Name: " + data.get(i).getName());
						System.out.println("Age: " + data.get(i).getAge());
						System.out.println("**********************");
					}
					break;
				case 5:
					Session session4 = factory.openSession();
					System.out.println("Enter Employee ID to display record: ");
					t = scanner.nextInt();
					EmployeeDetails emp3 = session4.load(EmployeeDetails.class, t);
					System.out.println("ID: " + emp3.getId());
					System.out.println("Name: " + emp3.getName());
					System.out.println("Age: " + emp3.getAge());
					break;
				case 6:
					Session session5 = factory.openSession();
					System.out.println("Enter Employee ID range to display record: ");
					t = scanner.nextInt();
					t1 = scanner.nextInt();
					
					List<EmployeeDetails> data1 = session5.createQuery("SELECT a FROM EmployeeDetails a", EmployeeDetails.class).getResultList();
					for(int i = t-1; i < t1; i++) {
						
						System.out.println("ID: " + data1.get(i).getId());
						System.out.println("Name: " + data1.get(i).getName());
						System.out.println("Age: " + data1.get(i).getAge());
						System.out.println("**********************");
						
					}
			}
		}while(ch < 7);
		
		System.out.println("Done");
	}
}
