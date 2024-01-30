package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import ssg.com.a.service.PdsService;

// 다운로드 되는 뷰
public class DownloadView extends AbstractView {

//	@Autowired
//	PdsService service;
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("DownloadView renderMergedOutputModel");
		
		// 짐풀러!
		File downloadFile = (File)model.get("downloadFile");
		String filename = (String)model.get("filename");
		int seq = (Integer)model.get("seq");
		
		System.out.println(seq);

		response.setContentType(this.getContentType());
		response.setContentLength((int)downloadFile.length());
		
		//한글파일 문제 인코딩
//		filename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
		filename = URLEncoder.encode(filename, "utf-8");
		
		// 다운로드 받는 윈도우
		response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
		response.setHeader("Content-Transfer-Encoding", "binary;");
		response.setHeader("Content-Length", "" + downloadFile.length());
		response.setHeader("Pragma", "no-cache;"); 
		response.setHeader("Expires", "-1;");
		
		OutputStream os = response.getOutputStream();
		FileInputStream fis = new FileInputStream(downloadFile);
		
		// 실제로 파일에 기입하는 처리
		FileCopyUtils.copy(fis, os);

		// AbstractView(가상뷰) extends를 해서 service주입 받으려고하니
		// NullPointerException 뜸..
		// 알아보니 spring의 빈 주입 순서와 관련이 있는 듯함.
		// 직접 ApplicationContext를 통해 관련 서비스 빈을 가져오려고 했으나 그것도 실패함.
		// 그래서 결국 파일 만들어 질때 아닌 파일 만들어 지기전에 카운트를 올리는 것으로 수정함.
		
		if(fis != null) {
			fis.close();
		}
	}

}





