package 주소록db연결;

import java.util.ArrayList;
import java.util.Scanner;

public class AddrService {
	AddrDao dao;

	public AddrService() {
		dao = new AddrDao();
	}

	// 1명의 이름,전화,주소 입력받아서 DB에 저장
	public void addAddr(Scanner sc) {
		System.out.println("===등록===");
		System.out.print("name:");
		String name = sc.next();
		System.out.print("tel:");
		String tel = sc.next();
		System.out.print("addr:");
		sc.nextLine(); //버퍼에 있는 enter 를 지우는 용도로 추가
		String addr = sc.nextLine();
		Addr a = new Addr(0, name, tel, addr); //번호는 자동할당임으로 아무거나 넣어도 적용 x
		if (dao.insertAddr(a) > 0) {
			System.out.println("등록 완료");
		} else {
			System.out.println("등록 되지 않음");
		}
	}

	// 수정, 수정할사람 번호와 새 전화, 주소를 입력받아 DB에서 수정
	public void editAddr(Scanner sc) {
		System.out.println("===수정===");
		System.out.print("수정할 사람 번호:");
		int num = sc.nextInt();
		System.out.print("새 전화번호:");
		String newTel = sc.next();
		System.out.print("새 주소:");
		String newAddr = sc.nextLine();
		Addr a = new Addr(num, "", newTel, newAddr);
		if (dao.updateAddr(a) > 0) {
			System.out.println("수정 완료");
		} else {
			System.out.println("수정 취소");
		}
	}

	// 삭제, 삭제할사람 번호 입력받아서 DB에서 삭제
	public void deleteAddr(Scanner sc) {
		System.out.println("===삭제===");
		System.out.print("삭제할 사람 번호:");
		int num = sc.nextInt();
		if (dao.deleteAddr(num) > 0) {
			System.out.println("삭제 완료");
		} else {
			System.out.println("삭제할 사람 없음");
		}
	}

	// 검색, 검색할사람 번호 입력받아서 DB에서 검색 , NULL이 아니면 출력
	public void printAddr(Scanner sc) {
		System.out.println("===번호로 검색===");
		System.out.print("검색할 사람 번호:");
		int num = sc.nextInt();
		Addr a = dao.selectAddr(num);
		if (a == null) {
			System.out.println("없는 번호");
		} else {
			System.out.println(a);
		}

	}

	// 전체 주소 목록 출력
	public void printAll() {
		System.out.println("===전체목록===");
		ArrayList<Addr> list = dao.selectAll();
		for (Addr a : list) {
			System.out.println(a);
		}
	}

	// 이름으로 검색, 검색할사람 이름 입력받아서 DB에서 이름으로 검색한 결과를 출력
	public void printByName(Scanner sc) {
		System.out.println("===이름으로 검색===");
		System.out.print("검색할 이름:");
		String name = sc.next();
		ArrayList<Addr> list = dao.selectByName(name);
		if (list == null) {
			System.out.println("이름으로 검색된 사람 없음");
		} else {
			for (Addr a : list) {
				System.out.println(a);
			}
		}
	}

	// 전화로 검색, 검색할사람 전화 입력받아서 DB에서 전화로 검색한 결과를 출력
	public void printByTel(Scanner sc) {
		System.out.println("===전화로 검색===");
		System.out.print("검색할 전화번호:");
		String tel = sc.next();
		ArrayList<Addr> list = dao.selectByTel(tel);
		if (list == null) {
			System.out.println("전화번호로 검색된 사람 없음");
		} else {
			for (Addr a : list) {
				System.out.println(a);
			}
		}
	}

}
