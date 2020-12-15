package Framework.service;

import Framework.model.Item;

public class ItemCreator {
    private final static String TEST_ITEM_URL_TEMPLATE = 
        "Framework.test.generic.item.%d.url";
    private final static String TEST_ITEM_PRICE_TEMPLATE = 
        "Framework.test.generic.item.%d.price";
    private final static String TEST_ITEM_GIFT_MODAL_POPUP_EXPECTED_TEMPLATE = 
        "Framework.test.generic.item.%d.giftModalPopupExpected";

    public static Item constructFromProperties(int itemNumber) {
        String url = 
            TestDataReader.getTestData(String.format(TEST_ITEM_URL_TEMPLATE, itemNumber));
        String price = 
            TestDataReader.getTestData(String.format(TEST_ITEM_PRICE_TEMPLATE, itemNumber));
        String giftModalPopupExpected = 
            TestDataReader.getTestData(
                String.format(TEST_ITEM_GIFT_MODAL_POPUP_EXPECTED_TEMPLATE, itemNumber)
            );

        return new Item(
            url,
            Integer.parseInt(price),
            Boolean.parseBoolean(giftModalPopupExpected)
        );
    }
}
