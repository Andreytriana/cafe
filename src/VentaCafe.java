public class VentaCafe {

    private int hora;
    private String tipoPago;
    private double dinero;
    private String cafe;
    private String momentoDia;
    private String diaSemana;
    private String mes;

    public VentaCafe(int hora, String tipoPago, double dinero,
                     String cafe, String momentoDia,
                     String diaSemana, String mes) {

        this.hora = hora;
        this.tipoPago = tipoPago;
        this.dinero = dinero;
        this.cafe = cafe;
        this.momentoDia = momentoDia;
        this.diaSemana = diaSemana;
        this.mes = mes;
    }

    public int getHora() {
        return hora;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public double getDinero() {
        return dinero;
    }

    public String getCafe() {
        return cafe;
    }

    public String getMomentoDia() {
        return momentoDia;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public String getMes() {
        return mes;
    }

    @Override
    public String toString() {
        return String.format(
                "Hora=%d | Cafe=%s | Venta=%.2f | Pago=%s | Momento=%s",
                hora, cafe, dinero, tipoPago, momentoDia
        );
    }
}