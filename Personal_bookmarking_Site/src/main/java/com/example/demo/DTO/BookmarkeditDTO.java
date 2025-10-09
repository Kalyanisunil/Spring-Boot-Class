package com.example.demo.DTO;



public class BookmarkeditDTO {
	 private String title;
	    private String url;
	    
	    
	    
	    
		public BookmarkeditDTO() {
			super();
			// TODO Auto-generated constructor stub
		}
		public BookmarkeditDTO(String title, String url) {
			super();
			this.title = title;
			this.url = url;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
}
