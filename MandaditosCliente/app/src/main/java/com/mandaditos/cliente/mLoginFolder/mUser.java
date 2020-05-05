package com.mandaditos.cliente.mLoginFolder;

public class mUser {

    private String Nombre;
	
	private String mUserId;

    public mUser() {
    }

	public void setMUserId(String mUserId)
	{
		this.mUserId = mUserId;
	}

	public String getMUserId()
	{
		return mUserId;
	}

	public void setNombre(String n)
	{
		Nombre = n;
	}

	public String getNombre()
	{
		return Nombre;
	}

}
