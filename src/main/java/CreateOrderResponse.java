public class CreateOrderResponse {

    private String name;
    private Number order;
    private boolean success;

    public CreateOrderResponse(String name, Number order, boolean success) {
        this.name = name;
        this.order = order;
        this.success = success;
    }

    public CreateOrderResponse() {
    }

    public String name() {
        return name;
    }

    public Number order() {
        return order;
    }

    public boolean success() {
        return success;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrder(Number order) {
        this.order = order;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
