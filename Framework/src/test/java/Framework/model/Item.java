package Framework.model;

public class Item {
    private String url;
    private int price;
    private boolean giftModalPopupExpected;
    private String id;

    public Item(String url, int price, boolean giftModalPopupExpected) {
        this.url = url;
        this.price = price;
        this.giftModalPopupExpected = giftModalPopupExpected;
    }

    public Item(String url, int price, boolean giftModalPopupExpected, String id) {
        this.url = url;
        this.price = price;
        this.giftModalPopupExpected = giftModalPopupExpected;
        this.id = id;
    }

    public Item(String url, int price) {
        this.url = url;
        this.price = price;
        this.giftModalPopupExpected = false;
    }

    public Item(String url, int price, String id) {
        this.url = url;
        this.price = price;
        this.giftModalPopupExpected = false;
        this.id = id;
    }

    public String getUrl() {
        return url;
    }
    public int getPrice() {
        return price;
    }
    public boolean getGiftModalPopupExpected() {
        return giftModalPopupExpected;
    }
    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object other) {
        if(!(other instanceof Item) ) {
            return false;
        }
        Item otherItem = (Item)other;
        return 
            (this.url.equals(otherItem.getUrl()) && this.price == otherItem.getPrice());
    }

    @Override
    public int hashCode() {
        return url.hashCode() + price;
    }
}
