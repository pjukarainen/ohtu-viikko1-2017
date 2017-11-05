package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void varastoonLaitetaanLiikaa() {
        varasto.lisaaVarastoon(12);

        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastonArvoVirheellinen() {
        Varasto varasto = new Varasto(0);

        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void varastonAlkusaldoJaTilavuusOikein() {
        Varasto varasto = new Varasto(10, 2);

        assertEquals(2, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void varastonAlkusaldoVirheellinen() {

        Varasto varasto = new Varasto(10, -5);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastostaOtetaanLiikaa() {

        assertEquals(0, varasto.otaVarastosta(12), vertailuTarkkuus);
    }
    
    @Test
    public void toStringPalauttaaOikein() {
        
        assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    }
    
    @Test
    public void varastoonLisataanVirheellinenMaara() {
        varasto.lisaaVarastoon(2);
        varasto.lisaaVarastoon(-5);
        assertEquals(2, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastostaOtetaanVirheellinenMaara() {
        varasto.lisaaVarastoon(2);
        varasto.otaVarastosta(1);
        varasto.otaVarastosta(-5);
        assertEquals(1, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void alkusaldoEnemmänKuinTilavuus() {
        Varasto varasto = new Varasto(5, 10);
        
        assertEquals(5, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void varastonTilavuusNegatiivinen() {
        
        Varasto varasto = new Varasto(-5, 2);
        
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }
}
