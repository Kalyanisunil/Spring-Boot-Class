


package com.example.demo.DTO;

public class BookmarkDisplayDTO 
{
    private String title;
    
    public String getTitle() 
    {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	private String url;

    public BookmarkDisplayDTO(String title, String url) {
        this.title = title;
        this.setUrl(url);
    }

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

    
}
