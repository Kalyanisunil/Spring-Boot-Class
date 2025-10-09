


package com.example.demo.DTO;

public class BookmarkDisplayDTO 
{
	private int id;
	
    private String title;
    private String url;
    
    public BookmarkDisplayDTO(String title, String url) {
        this.title = title;
        this.url = url;
    }
    public BookmarkDisplayDTO(int id, String title, String url) {
		super();
		this.id = id;
		this.title = title;
		this.url = url;
	}

    
	public BookmarkDisplayDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getTitle() 
    {
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    
}