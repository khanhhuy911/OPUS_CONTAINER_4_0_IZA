<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.io.*" %>
<%@ page import="org.apache.poi.ss.usermodel.Workbook" %>
<%@ page import="com.ibleaders.ibsheet7.ibsheet.excel.Down2Excel" %>
<%
	
	try {
		String contextRoot = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
	
		Down2Excel ibExcel = new Down2Excel();
		ibExcel.setService(request, response);
	
		System.out.println(request.getHeader("referer"));
		//System.out.println(com.ibleaders.ibsheet7.util.Version.getVersion());
	
		//====================================================================================================
		// [ 사용자 환경 설정 #1 ]
		//====================================================================================================
		// Html 페이지의 엔코딩이 utf-8 로 구성되어 있으면 "ibExcel.setPageEncoding("utf-8");" 로 설정하십시오.
		// 엑셀 문서의 한글이 깨지면 이 값을 공백("")으로 바꿔 보십시오. (설정하지 않거나 공백으로 설정 시 EUC-KR로 처리됩니다.)
		// LoadExcel.jsp 도 동일한 값으로 바꿔 주십시오.
		//====================================================================================================
		ibExcel.setPageEncoding("utf-8");
	
	
		//====================================================================================================
		// [ 사용자 환경 설정 #2 ]
		//====================================================================================================
		// 엑셀에 포함될 이미지의 URL 에 가상폴더를 사용할 경우가 조금이라도 있다면 웹루트를 아래 변수에 직접 지정해 주십시오.
		// 엑셀에 포함될 이미지에 가상폴더를 사용하지 않으면 설정하지 마세요.
		//====================================================================================================
		String webRootPath = "";
		ibExcel.setWebRoot(webRootPath);
	
		//====================================================================================================
		// [ 사용자 환경 설정 #3 ]
		//====================================================================================================
		// 트리 컬럼에서 레벨별로 … 를 덧붙여서 레벨별로 보기 좋게 만듭니다.
		// 만약 … 대신 다른 문자를 사용하기를 원하시면 아래 유니코드 \u2026 (16진수형태) 대신 다른 문자를 입력하십시오.
		// 트리 컬럼이 없으면 설정하지 마세요.
		//====================================================================================================
		ibExcel.setTreeChar("\u2026");
	
		//====================================================================================================
		// [ 사용자 환경 설정 #4 ]
		//====================================================================================================
		// 기본 폰트 이름과 폰트 크기를 설정합니다.
		// SheetDesign : 0 을 사용하는 경우에만 적용됩니다.
		//====================================================================================================
		ibExcel.setDefaultFontName("맑은고딕");
		ibExcel.setDefaultFontSize((short)10);
	
		//====================================================================================================
		// [ 사용자 환경 설정 #5 ]
		//====================================================================================================
		// IBSheet의 폰트 이름, 폰트 크기를 사용하지 않고 다음에서 설정한 값으로 강제적으로 적용합니다.
		// SheetDesign : 1, 2 를 사용하는 경우에만 적용됩니다.
		// 사용하지 않으시려면 주석처리 하세요.
		// 
		//ibExcel.setFontName("궁서");
		//ibExcel.setFontSize((short)15);
	
		//====================================================================================================
		// [ 사용자 환경 설정 #6 ]
		//====================================================================================================
		// 줄바꿈 설정을 다음에서 설정한 값으로 강제적으로 적용합니다.
		// 사용하지 않으시려면 주석처리 하세요.
		// 
		//ibExcel.setWordWrap(false);
	
		//====================================================================================================
		// [ 사용자 환경 설정 #7 ]
		//====================================================================================================
		// 엑셀에 포함될 이미지의 URL 이 다른 도메인에 있고 함께 다운로드 받으려면 다음을 설정합니다.
		// 기본값은 false 이며 다른 도메인에 존재하는 이미지는 다운로드 받지 않습니다.
		//ibExcel.setAllowDownloadRemoteImg(true);
	
		//====================================================================================================
		// [ 사용자 환경 설정 #8 ]
		//====================================================================================================
		// 엑셀에 포함될 이미지의 URL 이 같은 도메인에 있지만 "/image/imgDown.jsp?idx=365" 등과 같은 
		// 이미지 로딩 방식을 사용한다면 웹서버 도메인을 설정하세요.
		ibExcel.setWebServerDomain(contextRoot);
		
		//====================================================================================================
		// [ 사용자 환경 설정 #9 ]
		//====================================================================================================
		// 엑셀 다운로드 시 서버에 위치한 디자인 파일을 사용하는 경우 디자인 파일이 있는 폴더 위치를 설정하세요.
		// 디자인 파일을 사용하지 않는 경우 주석처리하세요.
		//====================================================================================================
		String tempRoot = "i:/tomcat7/webapps/ROOT";
		ibExcel.setTempRoot(tempRoot);
		
		//====================================================================================================
		// [ 사용자 환경 설정 #10 ]
		//====================================================================================================
		// 엑셀 다운로드 시 헤더행의 글자색을 설정하고 싶은 경우에 설정하세요.
		// #3366FF 형태의 웹 컬러로 설정해주세요.
		// 설정을 원하지 않는 경우 주석처리해주세요.
		//====================================================================================================
		
		out.clear();
		out = pageContext.pushBody();
		
		response.reset(); 

		// 파라메터 정보를 얻음
		String data = ibExcel.getData();
		
		//Down2Excel에서 SheetDesign 옵션에 관한 parameter를 얻음
		int txtIndex = data.indexOf("SheetDesign");
		
		//SheetDesign이 1이면 하얀색, SheetDesign이 0이면 검은색으로 header font 설정
		if(data.substring(txtIndex+12,txtIndex+13).equals("0")){
			ibExcel.setHeaderFontColor("#000000");
		}else{
			ibExcel.setHeaderFontColor("#FFFFFE");
		}
		// 파라메터 정보를 다시 설정함 (예, 암호화된 파라메터를 복호화 처리를 하여 다시 설정)
		ibExcel.setData(data);

		// 엑셀 워크북을 생성
		Workbook workbook = ibExcel.makeExcel();

		// 다운로드 1. 생성된 엑셀 문서를 바로 다운로드 받음
		ServletOutputStream out2 = response.getOutputStream();
		workbook.write(out2);
		out2.flush();



		// 다운로드 2. 생성된 엑셀 문서를 서버에 저장
		/*
		// 다운로드 받을 파일 이름을 얻음
		String fileName = ibExcel.getDownloadFileName();
		FileOutputStream out2 = new FileOutputStream (webRootPath + "/" + fileName);
		workbook.write(out2);
		out2.close();

		// 생성된 엑셀 문서를 다운로드 받음 (예, 엑셀문서를 DRM 처리함)
		File file = new File( webRootPath + "/" + fileName ); 
		int fileLength = (int)file.length();

		response.setContentLength(fileLength);

		try {
			if (file.isFile()) {
				FileInputStream fileIn = new FileInputStream(file);
				ServletOutputStream out3 = response.getOutputStream();

				byte[] outputByte = new byte[fileLength];

				while (fileIn.read(outputByte, 0, fileLength) != -1) {
					out3.write(outputByte, 0, fileLength);
				}

				fileIn.close();
				out3.flush();
				out3.close();
			} 
		} finally {
			file.delete();
		}
		*/
	} catch (Exception e) {
		out.println("<script>alert('엑셀 다운로드중 에러가 발생하였습니다.'); history.back();</script>");
		
		e.printStackTrace();
	} catch (Error e) {
		out.println("<script>alert('엑셀 다운로드중 에러가 발생하였습니다.'); history.back();</script>");

		e.printStackTrace();
	}

	
%>