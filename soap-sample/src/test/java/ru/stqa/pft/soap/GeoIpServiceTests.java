package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.nio.charset.StandardCharsets;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

    @Test
    public void testGeoIpService() {
        String ipLocation20 = new GeoIPService().getGeoIPServiceSoap12().getCountryISO2ByName("USA");
        assertEquals(ipLocation20, "<GeoIP><Country>US</Country></GeoIP>");
    }
}
