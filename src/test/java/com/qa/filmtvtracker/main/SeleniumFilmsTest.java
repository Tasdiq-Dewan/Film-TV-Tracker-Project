package com.qa.filmtvtracker.main;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

@SpringBootTest
public class SeleniumFilmsTest {

	private static WebDriver driver;
	
	@BeforeAll
	public static void setup() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();
	}

	@AfterAll
	public static void tearDown() {
		driver.quit();
	}
	
	@Test
	  public void testLoadFilmsPage() {
	    driver.get("http://localhost:8090/index.html");
	    driver.manage().window().setSize(new Dimension(824, 586));
	    driver.findElement(By.linkText("Films")).click();
	    {
	      List<WebElement> elements = driver.findElements(By.cssSelector("main > h2"));
	      assert(elements.size() > 0);
	    }
	    {
	      List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"film-table\"]"));
	      assert(elements.size() > 0);
	    }
	    assertEquals(driver.findElement(By.xpath("//*[@id=\"film-table-head\"]/tr/th[1]")).getText(), "ID");
	    assertEquals(driver.findElement(By.xpath("//*[@id=\"film-table-head\"]/tr/th[2]")).getText(), "Name");
	    assertEquals(driver.findElement(By.xpath("//*[@id=\"film-table-head\"]/tr/th[3]")).getText(), "Director");
	    assertEquals(driver.findElement(By.xpath("//*[@id=\"film-table-head\"]/tr/th[4]")).getText(), "Year of Release");
	    assertEquals(driver.findElement(By.xpath("//*[@id=\"film-table-head\"]/tr/th[5]")).getText(), "Genre");
	    assertEquals(driver.findElement(By.xpath("//*[@id=\"film-table-head\"]/tr/th[6]")).getText(), "Runtime (mins)");
	    {
	      List<WebElement> elements = driver.findElements(By.xpath("/html/body/main/button[1]"));
	      assert(elements.size() > 0);
	    }
	    {
	      List<WebElement> elements = driver.findElements(By.xpath("/html/body/main/button[2]"));
	      assert(elements.size() > 0);
	    }
	    {
	      List<WebElement> elements = driver.findElements(By.xpath("/html/body/main/button[3]"));
	      assert(elements.size() > 0);
	    }
	    {
	      List<WebElement> elements = driver.findElements(By.xpath("/html/body/main/button[4]"));
	      assert(elements.size() > 0);
	    }
	    {
	      List<WebElement> elements = driver.findElements(By.xpath("/html/body/main/button[5]"));
	      assert(elements.size() > 0);
	    }
	    {
	      List<WebElement> elements = driver.findElements(By.xpath("/html/body/main/button[6]"));
	      assert(elements.size() > 0);
	    }
	  }
	
	@Test
	  public void testDisplayAllFilms() {
	    driver.get("http://localhost:8090/index.html");
	    driver.findElement(By.linkText("Films")).click();
	    {
	      List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"film-table\"]"));
	      assert(elements.size() > 0);
	    }
	    {
	      List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"film-table-body\"]"));
	      assert(elements.size() > 0);
	    }
	    {
	      List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"film-table-body\"]/tr[1]"));
	      assert(elements.size() > 0);
	    }
	    assertEquals(driver.findElement(By.xpath("//*[@id=\"film-table-body\"]/tr[1]/th")).getText(), "1");
	    assertEquals(driver.findElement(By.xpath("//*[@id=\"film-table-body\"]/tr[1]/td[1]")).getText(), "The Lord of the Rings: The Fellowship of the Ring");
	    assertEquals(driver.findElement(By.xpath("//*[@id=\"film-table-body\"]/tr[1]/td[2]")).getText(), "Peter Jackson");
	    assertEquals(driver.findElement(By.xpath("//*[@id=\"film-table-body\"]/tr[1]/td[3]")).getText(), "2001");
	    assertEquals(driver.findElement(By.xpath("//*[@id=\"film-table-body\"]/tr[1]/td[4]")).getText(), "Fantasy");
	    assertEquals(driver.findElement(By.xpath("//*[@id=\"film-table-body\"]/tr[1]/td[5]")).getText(), "208");
	    assertEquals(driver.findElement(By.xpath("//*[@id=\"film-table-body\"]/tr[2]/th")).getText(), "2");
	    assertEquals(driver.findElement(By.xpath("//*[@id=\"film-table-body\"]/tr[2]/td[1]")).getText(), "The Godfather");
	    assertEquals(driver.findElement(By.xpath("//*[@id=\"film-table-body\"]/tr[2]/td[2]")).getText(), "Francis Ford Coppola");
	    assertEquals(driver.findElement(By.xpath("//*[@id=\"film-table-body\"]/tr[2]/td[3]")).getText(), "1972");
	    assertEquals(driver.findElement(By.xpath("//*[@id=\"film-table-body\"]/tr[2]/td[4]")).getText(), "Mafia");
	    assertEquals(driver.findElement(By.xpath("//*[@id=\"film-table-body\"]/tr[2]/td[5]")).getText(), "175");
	  }
	
	@Test
	  public void testAddFilmButton() {
	    driver.get("http://localhost:8090/films.html");
	    driver.manage().window().setSize(new Dimension(1552, 840));
	    driver.findElement(By.cssSelector("button:nth-child(2)")).click();
	    driver.findElement(By.id("name")).click();
	    {
	      WebElement element = driver.findElement(By.id("name"));
	      Boolean isEditable = element.isEnabled() && element.getAttribute("readonly") == null;
	      assertTrue(isEditable);
	    }
	    driver.findElement(By.id("name")).sendKeys("Batman");
	    {
	      String value = driver.findElement(By.id("name")).getAttribute("value");
	      assertEquals(value, "Batman");
	    }
	    driver.findElement(By.id("director")).click();
	    {
	      WebElement element = driver.findElement(By.id("director"));
	      Boolean isEditable = element.isEnabled() && element.getAttribute("readonly") == null;
	      assertTrue(isEditable);
	    }
	    driver.findElement(By.id("director")).sendKeys("Tim Burton");
	    {
	      String value = driver.findElement(By.id("director")).getAttribute("value");
	      assertEquals(value, "Tim Burton");
	    }
	    driver.findElement(By.id("year")).click();
	    {
	      WebElement element = driver.findElement(By.id("year"));
	      Boolean isEditable = element.isEnabled() && element.getAttribute("readonly") == null;
	      assertTrue(isEditable);
	    }
	    driver.findElement(By.id("year")).sendKeys("1989");
	    {
	      String value = driver.findElement(By.id("year")).getAttribute("value");
	      assertEquals(value, "1989");
	    }
	    driver.findElement(By.id("genre")).click();
	    {
	      WebElement element = driver.findElement(By.id("genre"));
	      Boolean isEditable = element.isEnabled() && element.getAttribute("readonly") == null;
	      assertTrue(isEditable);
	    }
	    driver.findElement(By.id("genre")).sendKeys("Superhero");
	    {
	      String value = driver.findElement(By.id("genre")).getAttribute("value");
	      assertEquals(value, "Superhero");
	    }
	    driver.findElement(By.id("runtime")).click();
	    {
	      WebElement element = driver.findElement(By.id("runtime"));
	      Boolean isEditable = element.isEnabled() && element.getAttribute("readonly") == null;
	      assertTrue(isEditable);
	    }
	    driver.findElement(By.id("runtime")).sendKeys("123");
	    {
	      String value = driver.findElement(By.id("runtime")).getAttribute("value");
	      assertEquals(value, "123");
	    }
	    {
	      List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"addFilmForm\"]/form/button[1]"));
	      assert(elements.size() > 0);
	    }
	    driver.findElement(By.xpath("//*[@id=\"addFilmForm\"]/form/button[1]")).click();
	  }
}
