package com.centro.encuestas;

public class Qr {
    public String generarQr(int idEncuesta) {
        // Devuelve una URL fake con el QR de la encuesta
        return "https://centro.edu/encuesta/" + idEncuesta + "/qr";
    }
}
