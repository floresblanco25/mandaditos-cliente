package com.mandaditos.cliente.Mandaditos;
import com.google.android.gms.maps.model.*;


public class MandaditosModel
{
	private String partida;
    private String destino;
	private String distancia;
    private String fecha;
	private String eta;
    private String wheretogetmoney;
	private String costo;
	private String orderName;

    public MandaditosModel( String orderName,
		String partida, String destino, String distancia, String fecha
		, String eta, String wheregetmoney, String costo) {
        this.partida = partida;
        this.destino = destino;
		this.distancia = distancia;
        this.fecha = fecha;
		this.eta = eta;
        this.wheretogetmoney = wheregetmoney;
		this.costo = costo;
		this.orderName=orderName;
    }

    public String getPartida() {
        return partida;
    }

    public String getDestino() {
        return destino;
    }
	public String getDistancia() {
        return distancia;
    }

    public String getFecha() {
        return fecha;
    }
	public String getEta() {
        return eta;
    }

    public String getWheregetMoney() {
        return wheretogetmoney;
    }
	public String getCost() {
        return costo;
    }
	public String getOrderName(){
		return orderName;
	}

}



