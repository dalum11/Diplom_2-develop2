public class Order {

    String[] ingredients;
    String _id;
    String status;
    int number;
    String cheatedAt;
    String updatedAt;

    public Order(String[] ingredients, String _id, String status, int number, String cheatedAt, String updatedAt) {
        this.ingredients = ingredients;
        this._id = _id;
        this.status = status;
        this.number = number;
        this.cheatedAt = cheatedAt;
        this.updatedAt = updatedAt;
    }

    public Order() {
    }

    public String[] ingredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public String _id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String status() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int number() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String cheatedAt() {
        return cheatedAt;
    }

    public void setCheatedAt(String cheatedAt) {
        this.cheatedAt = cheatedAt;
    }

    public String updatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
