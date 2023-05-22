package desafio10;

import java.time.Duration;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class Pesquisa {
	String url;
	WebDriver driver;
	
	public Pesquisa() {
		driver = new ChromeDriver();
	}
	
	@Before
	public void iniciar() {
		url = "https://www.google.com.br";
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\mpastorp\\Desafio10 ECLIPSE\\desafio10\\drivers\\chrome\\chromedriver.exe");
		driver.manage().window().maximize();
	}
	
	@After
	public void finalizar() {
		driver.close();
		driver.quit();
	}
	
	@Dado("^que estou no site do google$")
	public void que_estou_no_site_do_google() {
		driver.navigate().to("https://www.google.com.br");
	}

	@Quando("^realizo uma busca do site da nttdata$")
	public void realizo_uma_busca_do_site_da_nttdata() throws InterruptedException {
		WebElement pesquisarGoogle = driver.findElement(By.xpath("//textarea[@title='Pesquisar']"));
		pesquisarGoogle.sendKeys("nttdata");
		pesquisarGoogle.sendKeys(Keys.ENTER);
	}

	@Quando("^clico no primeiro link apresentado$")
	public void clico_no_primeiro_link_apresentado() {
		driver.findElement(By.xpath("//a[@href='https://nttdata-solutions.com/br/sobre-nos/quem-somos/']")).click();
		driver.findElement(By.id("all")).click();
	    
	}

	@Quando("^clico na opção carreira$")
	public void clico_na_opção_carreira() {
	    driver.findElement(By.id("menu-item-32094")).click();
	}

	@Quando("^clico em junte-se a nossa equipe$")
	public void clico_em_junte_se_a_nossa_equipe() {
	    driver.findElement(By.cssSelector("a.button")).click();
	}

	@Entao("^pesquiso por arquiteto$")
	public void pesquiso_por_arquiteto() throws InterruptedException {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    driver.findElement(By.id("pesquisar-vaga-localidade-etc")).sendKeys(Keys.chord("Arquiteto"));
	    Thread.sleep(3000);
	}
}
