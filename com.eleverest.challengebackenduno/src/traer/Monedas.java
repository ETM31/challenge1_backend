package traer;

public record Monedas(String base_code, String target_code, double conversion_result) {
    @Override
    public String toString() {
        return "el valor de la converion es: "+conversion_result;
    }
}
