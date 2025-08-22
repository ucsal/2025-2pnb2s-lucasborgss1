package br.com.mariojp.solid.srp;

import br.com.mariojp.solid.srp.model.Item;
import br.com.mariojp.solid.srp.model.Order;
import br.com.mariojp.solid.srp.service.ReceiptService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReceiptServiceTest {
    @Test
    void uses_configured_tax_rate_of_8_percent() {
        System.setProperty("tax.rate", "0.08");
        Order order = new Order();
        order.add(new Item("Café", 8.0, 2)); // 16
        order.add(new Item("Bolo", 12.5, 1)); // 12.5 -> subtotal 28.5

        String receipt = new ReceiptService().generate(order);
        assertTrue(receipt.contains("Subtotal: 28.5"));
        // Esperado com 8%: tax=2.28 total=30.78 (inicialmente virá 2.85 e 31.35)
        assertTrue(receipt.contains("Tax: 2.28"), "Deve respeitar tax.rate=0.08");
        assertTrue(receipt.contains("Total: 30.78"));
    }
}
