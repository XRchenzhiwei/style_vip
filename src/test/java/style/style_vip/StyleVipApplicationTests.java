package style.style_vip;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class StyleVipApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void bigdecimalTest(){
        BigDecimal b1 = new BigDecimal("12.56");
        BigDecimal b2 = new BigDecimal("10.23");
        if (b1.compareTo(b2) > 1) {
            System.out.println("b1");
        }else {
            System.out.println("b2");
        }
    }
}
