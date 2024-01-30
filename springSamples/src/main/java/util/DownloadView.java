package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
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
		
		service.downloadCount(seq);
		
		// download 회수를 증가
//		boolean b = service.downloadCount(seq);
//		if(b) {
//			System.out.println("다운로드 카운트 증가");
//		} else {
//			System.out.println("다운로드 카운트 증가 실패");
//		}
		
		if(fis != null) {
			fis.close();
		}
	}

}





