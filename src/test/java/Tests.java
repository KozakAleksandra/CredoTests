import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Tests {

    private static WindowsDriver CTest = null;

    @BeforeClass
    public static void setup() {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("app", "http://localhost:3000/");
            CTest = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
            CTest.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void Logowanie() {
        CTest.findElementByName("Sign in").click();
        CTest.findElementByName("User ID").click();
        CTest.findElementByName("User ID").sendKeys("157");
        CTest.findElementByName("Password").click();
        CTest.findElementByName("Password").sendKeys("gg");
        CTest.findElementByName("Submit").click();
        Assert.assertNotNull("Logged in as: 157");

    }
    @Test
    public void NiepoprawneHaslo() {
        CTest.findElementByName("Sign in").click();
        CTest.findElementByName("User ID").click();
        CTest.findElementByName("User ID").sendKeys("157");
        CTest.findElementByName("Password").click();
        CTest.findElementByName("Password").sendKeys("ww");
        CTest.findElementByName("Submit").click();
        Assert.assertNotNull("wrong password");

    }

    @Test
    public void NiepoprawnyLogin() {
        CTest.findElementByName("Sign in").click();
        CTest.findElementByName("User ID").click();
        CTest.findElementByName("User ID").sendKeys("123");
        CTest.findElementByName("Password").click();
        CTest.findElementByName("Password").sendKeys("gg");
        CTest.findElementByName("Submit").click();
        Assert.assertNotNull("user not found");

    }



    @Test
    public void Wylogowywanie() {
        CTest.findElementByName("Sign in").click();
        CTest.findElementByName("User ID").click();
        CTest.findElementByName("User ID").sendKeys("157");
        CTest.findElementByName("Password").click();
        CTest.findElementByName("Password").sendKeys("gg");
        CTest.findElementByName("Submit").click();
        Assert.assertNotNull("Logged in as: 157");
        CTest.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        CTest.findElementByName("Sign out").click();
        Assert.assertNotNull("Successfully logged out!");


    }

    @Test
    public void Gallery() {

        CTest.findElementByName("GALLERY").click();
        CTest.findElementByName
                ("Aby uzyskać brakujące opisy obrazów, otwórz menu kontekstowe.")
                .click();
        Assert.assertNotNull("anti-artefact");
        Assert.assertNotNull("CNN_small_w0");
        Assert.assertNotNull("rf");
        Assert.assertNotNull("Classifier Name");
        Assert.assertNotNull("Class name");
        CTest.findElementByName("Close").click();
        Assert.assertNotNull("Legend");
        Assert.assertNotNull("Class filters");

    }

    @Test
    public void Filtrowanie() {
        CTest.findElementByName("GALLERY").click();
        CTest.findElementByName("Class Filters").click();
        CTest.findElementByName("dot").click();
        Assert.assertNotNull("dot");
        CTest.findElementByName("dot").click();
        CTest.findElementByName("track").click();
        Assert.assertNotNull("Not found");
    }
}