package main;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import dto.BbsDto;
import dto.BbsParam;
import dto.MemberDto;

public class MainClass {

	public static void main(String[] args) throws Exception {
		// config(설정)파일을 load
		InputStream is = Resources.getResourceAsStream("mybatis/config.xml");

		// sqlSessionFactory를 생성
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);

		// sqlSession을 생성
		SqlSession session = factory.openSession();

		// insert
//		MemberDto dto = new MemberDto("aabbcc", "112233", "이승철", "aabbcc@daum.net", 0);
//		int count = session.insert("addmember", dto); 
//		if(count > 0) {
//			session.commit();	//DB에 적용
//			System.out.println("추가 성공");
//		} else {
//			session.rollback(); //취소
//			System.out.println("추가 실패3");
//		}

		// select(1개의 row) selectOne - object
//		String id = "aabbcc";
//		MemberDto dto = session.selectOne("getmember", id);
//		System.out.println(dto.toString());

		// select(다수의 row) - list
//		List<MemberDto> list = session.selectList("allmember");
//		for (MemberDto m : list) {
//			System.out.println(m.toString());
//		}

		// delete
//		String id = "aabbcc";
//		int count = session.delete("delmember", id);
//		if (count > 0) {
//			session.commit(); // DB에 적용
//			System.out.println("삭제 성공");
//		} else {
//			session.rollback(); // 취소
//			System.out.println("삭제 실패");
//		}

		// update

		String id = "aaa";
		String name = "성춘";
		
		MemberDto dto = new MemberDto(id, null, name, null, 0);
		
		int count = session.update("updatemember", dto);
		if (count > 0) {
			session.commit(); // DB에 적용
			System.out.println("수정 성공");
		} else {
			session.rollback(); // 취소
			System.out.println("수정 실패");
		}
		
		BbsParam param = new BbsParam("writer", "abc");
		
		List<BbsDto> list = session.selectList("bbssearch", param);
		
		for (BbsDto bbs : list) {
			System.out.println(bbs.toString());
		}
	}
}





