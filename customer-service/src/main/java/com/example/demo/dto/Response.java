package com.example.demo.dto;


public class Response {

    private Object data; //
    private String message;
    private boolean ok; //true, object is found, false -> there is some issue

    public Response(Object data, String message, boolean ok) {
        this.data = data;
        this.message = message;
        this.ok = ok;
    }

    public Response(Object data, boolean ok) {
        this.data = data;
        this.ok = ok;
    }

    public Response(String message, boolean ok) {
        this.message = message;
        this.ok = ok;
    }

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public Response() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Response [data=" + data + ", message=" + message + ", ok=" + ok + "]";
	}
    
}
