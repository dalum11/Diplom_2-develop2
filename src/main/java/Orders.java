public class Orders {

    private boolean success;
    private Order[] orders;
    private int total;
    private int totalToday;

    public Orders(boolean success, Order[] orders, int total, int totalToday) {
        this.success = success;
        this.orders = orders;
        this.total = total;
        this.totalToday = totalToday;
    }

    public Orders() {
    }

    public boolean success() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Order[] orders() {
        return orders;
    }

    public void setOrders(Order[] orders) {
        this.orders = orders;
    }

    public int total() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int totalToday() {
        return totalToday;
    }

    public void setTotalToday(int totalToday) {
        this.totalToday = totalToday;
    }
}
