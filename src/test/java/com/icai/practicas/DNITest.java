package com.icai.practicas;

import org.junit.jupiter.api.Test;
import com.icai.practicas.model.DNI;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DNITest {

    @Test
    public void testDNI(){
				//Meto un DNI con un valor verdadero, y uno con uno falso 
        DNI dni_test_true = new DNI("06679111A");
        DNI dni_test_false = new DNI("1234567891");

        boolean valid_DNI_false= dni_test_false.validar();
        boolean valid_DNI_true = dni_test_true.validar();
        assertEquals(true,valid_DNI_true);
        assertEquals(false, valid_DNI_false);
    }
}