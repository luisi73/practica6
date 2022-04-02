package com.icai.practicas;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import com.icai.practicas.model.Telefono;

public class TelefonoTest {


    @Test
    public void testTelefono(){
        Telefono telefono_español = new Telefono("619321455");
        Telefono telefono_internacional = new Telefono("+30619321455"); 
        Telefono telefono_falso = new Telefono("649643965693456932563659"); 

        boolean valid_telf_true = telefono_español.validar();

        assertEquals(true, valid_telf_true);

        valid_telf_true = telefono_internacional.validar();

        assertEquals(true, valid_telf_true);

        boolean valid_telf_false = telefono_falso.validar();

        assertEquals(false, valid_telf_false);
    }
    
}