package practice2;

import java.math.BigDecimal;
import java.util.List;

public class Receipt {

    public Receipt() {
        tax = new BigDecimal(0.1);
        tax = tax.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private BigDecimal tax;

    public double CalculateGrandTotal(List<Product> products, List<OrderItem> items) {
        BigDecimal subTotal = calculateSubtotal(products, items);
        BigDecimal taxTotal = subTotal.multiply(tax);
        BigDecimal grandTotal = subTotal.add(taxTotal);
        return grandTotal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    // 应该在products中查找item
    private Product findProductByItem(OrderItem item, List<Product> products) {
        for (Product product : products)
            if (item.getCode() == product.getCode())
                return product;
        return null;
    }

    private BigDecimal calculateSubtotal(List<Product> products, List<OrderItem> items) {
        BigDecimal subTotal = new BigDecimal(0);
        for (OrderItem item : items) {
            Product product = findProductByItem(item, products);
            BigDecimal itemTotal = product.getPrice().multiply(new BigDecimal(item.getCount())).multiply((new BigDecimal(1)).subtract(product.getDiscountRate()));
            subTotal = subTotal.add(itemTotal);
        }
        return subTotal;
    }
}
