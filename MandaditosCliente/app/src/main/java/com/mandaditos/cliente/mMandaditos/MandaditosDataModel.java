package com.mandaditos.cliente.mMandaditos;
import com.google.android.gms.maps.model.*;


public class MandaditosDataModel
{
	private String Usuario;
	private String Partida;
    private String Destino;
	private String Distancia;
    private String Fecha;
	private String ETA;
    private String RecogerDineroEn;
	private String Costo;
	private String EstadoDeOrden;
	private LatLng LatLngA;
	private LatLng LatLngB;
	private String NumeroDeOrden;
	
	public MandaditosDataModel(){
		
	}

    public MandaditosDataModel(
	String Usuario,String Partida, String Destino, String Distancia, String Fecha, 
	String ETA, String RecogerDineroEn, String Costo, String EstadoDeOrden,LatLng LatLngA,
	LatLng LatLngB
		) {
        this.Partida = Partida;
        this.Destino = Destino;
		this.Distancia = Distancia;
        this.Fecha = Fecha;
		this.ETA = ETA;
        this.RecogerDineroEn = RecogerDineroEn;
		this.Costo = Costo;
		this.EstadoDeOrden=EstadoDeOrden;
		this.LatLngA=LatLngA;
		this.Usuario=Usuario;
		this.LatLngB=LatLngB;
		}

		public void setNumeroDeOrden(String numeroDeOrden)
		{
			NumeroDeOrden = numeroDeOrden;
		}

		public String getNumeroDeOrden()
		{
			return NumeroDeOrden;
		}

		public void setLatLngB(LatLng latLngB)
		{
			LatLngB = latLngB;
		}

		public LatLng getLatLngB()
		{
			return LatLngB;
		}


public String getUsuario()
{
return Usuario;
}

    public String getPartida() {
        return Partida;
    }

    public String getDestino() {
        return Destino;
    }
	public String getDistancia() {
        return Distancia;
    }

    public String getFecha() {
        return Fecha;
    }
	public String getEta() {
        return ETA;
    }

    public String getRecogerDineroEn() {
        return RecogerDineroEn;
    }
	public String getCosto() {
        return Costo;
    }
	public String getEstadoDeOrden(){
		return EstadoDeOrden;
	}
	public LatLng getLatLngA(){
		return LatLngA;
	}
	
	
	
	
	
	public void setPartida(String t){
		Partida=t;
	}
	
	public void setDestino(String t){
		Destino=t;
	}
	
	public void setDistancia(String t){
		Distancia=t;
	}
	public void setFecha(String t){
		Fecha=t;
	}
	public void setETA(String t){
		ETA=t;
	}
	public void setRecogerDineroEn(String t){
		RecogerDineroEn=t;
	}
	public void setCosto(String t){
		Costo=t;
	}
	
	public void setEstadoDeOrden(String t){
		EstadoDeOrden=t;
	}
	public void setLatLngA(LatLng t){
		LatLngA=t;
	}
	
	public void setUsuario(String t)
	{
		this.Usuario = t;
	}
	
	
	
	
	
	
	
	

}


