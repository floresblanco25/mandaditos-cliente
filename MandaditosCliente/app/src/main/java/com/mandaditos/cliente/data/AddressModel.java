package com.mandaditos.cliente.data;
import com.google.android.gms.maps.model.*;

public class AddressModel
{
	private String address_partida;
    private String address_destino;
    private LatLng latlngPartida;
	private LatLng latlng_destino;

    public AddressModel(String address_partida, String address_destino, LatLng latlng_partida, LatLng latlng_destino) {
        this.address_partida = address_partida;
        this.address_destino = address_destino;
        this.latlngPartida = latlng_partida;
		this.latlng_destino = latlng_destino;
    }

    public String getAddressPartida() {
        return address_partida;
    }

    public String getAddressDestino() {
        return address_destino;
    }

    public LatLng getLatltnPartida() {
        return latlngPartida;
    }
	
	public LatLng getLatltnDestino() {
        return latlng_destino;
    }
}
