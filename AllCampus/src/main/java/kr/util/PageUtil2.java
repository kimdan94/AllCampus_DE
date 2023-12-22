package kr.util;

public class PageUtil2 {
	private int startRow;	 
	private int endRow;	
	
	private int startRow2;	 
	private int endRow2;	
	
	private int startRow3;	 
	private int endRow3;	
	
	private StringBuffer page;// 페이지 표시 문자열
	private StringBuffer page2;// 페이지 표시 문자열
	private StringBuffer page3;// 페이지 표시 문자열

	/**
	 * currentPage : 현재페이지
	 * count : 전체 게시물 수
	 * rowCount : 한 페이지의  게시물의 수
	 * pageCount : 한 화면에 보여줄 페이지 수
	 * pageUrl : 호출 페이지 url
	 * addKey : 부가적인 key 없을 때는 null 처리 (&num=23형식으로 전달할 것)
	 * */
	public PageUtil2(int currentPage,int currentPage2,int currentPage3, int count,int count2, int count3, int rowCount,
			int pageCount,String pageUrl) {
		
		if(count >= 0) {
			// 전체 페이지 수
			int totalPage = (int) Math.ceil((double) count / rowCount);
			if (totalPage == 0) {
				totalPage = 1;
			}
			// 현재 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정
			if (currentPage > totalPage) {
				currentPage = totalPage;
			}
			// 현재 페이지의 처음과 마지막 글의 번호 가져오기.
			startRow = (currentPage - 1) * rowCount + 1;
			endRow = currentPage * rowCount;
			
			// 이전 block 페이지
			page = new StringBuffer();
			if(pageCount > 0) {
				// 시작 페이지와 마지막 페이지 값 구하기.
				int startPage = (int) ((currentPage - 1) / pageCount) * pageCount + 1;
				int endPage = startPage + pageCount - 1;
				// 마지막 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정
				if (endPage > totalPage) {
					endPage = totalPage;
				}
				
				if (currentPage > pageCount) {
					page.append("<a href="+pageUrl+"?pageNum="+ (startPage - 1) +"&pageNum2="+ currentPage2 +"&pageNum3="+ currentPage3 +">");
					page.append("[이전]");
					page.append("</a>");
				}
				//페이지 번호.현재 페이지는 빨간색으로 강조하고 링크를 제거.
				for (int i = startPage; i <= endPage; i++) {
					if (i > totalPage) {
						break;
					}
					if (i == currentPage) {
						page.append("&nbsp;<b><span style='color:red;'>");
						page.append(i);
						page.append("</span></b>");
					} else {
						page.append("&nbsp;<a href='"+pageUrl+"?pageNum=");
						page.append(i);
						page.append("&pageNum2=");
						page.append(currentPage2);
						page.append("&pageNum3=");
						page.append(currentPage3);
						page.append("'>");
						page.append(i);
						page.append("</a>");
					}
					page.append("&nbsp;");
				}
				// 다음 block 페이지
				if (totalPage - startPage >= pageCount) {
					page.append("<a href="+pageUrl+"?pageNum="+ (endPage + 1) + "&pageNum2="+ currentPage2 + "&pageNum3="+ currentPage2 + ">");
					page.append("[다음]");
					page.append("</a>");
				}
			}else {
				page.append("<b>[warning]</b>pageCount는 1이상 지정해야 페이지수가 표시됩니다.");
			}
		}
		
		if(count2 >= 0) {
			// 전체 페이지 수
			int totalPage = (int) Math.ceil((double) count2 / rowCount);
			if (totalPage == 0) {
				totalPage = 1;
			}
			// 현재 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정
			if (currentPage2 > totalPage) {
				currentPage2 = totalPage;
			}
			// 현재 페이지의 처음과 마지막 글의 번호 가져오기.
			startRow2 = (currentPage2 - 1) * rowCount + 1;
			endRow2 = currentPage2 * rowCount;
			
			// 이전 block 페이지
			page2 = new StringBuffer();
			if(pageCount > 0) {
				// 시작 페이지와 마지막 페이지 값 구하기.
				int startPage = (int) ((currentPage2 - 1) / pageCount) * pageCount + 1;
				int endPage = startPage + pageCount - 1;
				// 마지막 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정
				if (endPage > totalPage) {
					endPage = totalPage;
				}
				
				if (currentPage2 > pageCount) {
					page2.append("<a href="+pageUrl+"?pageNum="+ currentPage + "&pageNum2="+ (startPage - 1) + "&pageNum3="+ currentPage3 +">");
					page2.append("[이전]");
					page2.append("</a>");
				}
				//페이지 번호.현재 페이지는 빨간색으로 강조하고 링크를 제거.
				for (int i = startPage; i <= endPage; i++) {
					if (i > totalPage) {
						break;
					}
					if (i == currentPage2) {
						page2.append("&nbsp;<b><span style='color:red;'>");
						page2.append(i);
						page2.append("</span></b>");
					} else {
						page2.append("&nbsp;<a href='"+pageUrl+"?pageNum=");
						page2.append(currentPage);
						page2.append("&pageNum2=");
						page2.append(i);
						page2.append("&pageNum3=");
						page2.append(currentPage3);
						page2.append("'>");
						page2.append(i);
						page2.append("</a>");
					}
					page2.append("&nbsp;");
				}
				// 다음 block 페이지
				if (totalPage - startPage >= pageCount) {
					page2.append("<a href="+pageUrl+"?pageNum="+ currentPage + "&pageNum2="+ (endPage + 1) + "&pageNum3="+ currentPage3 + ">");
					page2.append("[다음]");
					page2.append("</a>");
				}
			}else {
				page2.append("<b>[warning]</b>pageCount는 1이상 지정해야 페이지수가 표시됩니다.");
			}
		}
		
		if(count3 >= 0) {
			// 전체 페이지 수
			int totalPage = (int) Math.ceil((double) count3 / rowCount);
			if (totalPage == 0) {
				totalPage = 1;
			}
			// 현재 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정
			if (currentPage3 > totalPage) {
				currentPage3 = totalPage;
			}
			// 현재 페이지의 처음과 마지막 글의 번호 가져오기.
			startRow3 = (currentPage3 - 1) * rowCount + 1;
			endRow3 = currentPage3 * rowCount;
			
			// 이전 block 페이지
			page3 = new StringBuffer();
			if(pageCount > 0) {
				// 시작 페이지와 마지막 페이지 값 구하기.
				int startPage = (int) ((currentPage3 - 1) / pageCount) * pageCount + 1;
				int endPage = startPage + pageCount - 1;
				// 마지막 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정
				if (endPage > totalPage) {
					endPage = totalPage;
				}
				
				if (currentPage3 > pageCount) {
					page3.append("<a href="+pageUrl+"?pageNum="+ currentPage + "&pageNum2="+ currentPage2 + "&pageNum3="+ (startPage - 1) + ">");
					page3.append("[이전]");
					page3.append("</a>");
				}
				//페이지 번호.현재 페이지는 빨간색으로 강조하고 링크를 제거.
				for (int i = startPage; i <= endPage; i++) {
					if (i > totalPage) {
						break;
					}
					if (i == currentPage3) {
						page3.append("&nbsp;<b><span style='color:red;'>");
						page3.append(i);
						page3.append("</span></b>");
					} else {
						page3.append("&nbsp;<a href='"+pageUrl+"?pageNum=");
						page3.append(currentPage);
						page3.append("&pageNum2=");
						page3.append(currentPage2);
						page3.append("&pageNum3=");
						page3.append(i);
						page3.append("'>");
						page3.append(i);
						page3.append("</a>");
					}
					page3.append("&nbsp;");
				}
				// 다음 block 페이지
				if (totalPage - startPage >= pageCount) {
					page3.append("<a href="+pageUrl+"?pageNum="+ currentPage + "&pageNum2="+ currentPage + "&pageNum3="+ (endPage + 1) + ">");
					page3.append("[다음]");
					page3.append("</a>");
				}
			}else {
				page3.append("<b>[warning]</b>pageCount는 1이상 지정해야 페이지수가 표시됩니다.");
			}
		}
	}
	public StringBuffer getPage() {
		return page;
	}
	public StringBuffer getPage2() {
		return page2;
	}
	public StringBuffer getPage3() {
		return page3;
	}
	public int getStartRow() {
		return startRow;
	}
	public int getEndRow() {
		return endRow;
	}
	public int getStartRow2() {
		return startRow2;
	}
	public int getEndRow2() {
		return endRow2;
	}
	public int getStartRow3() {
		return startRow3;
	}
	public int getEndRow3() {
		return endRow3;
	}
}
